package com.example.parking;

import com.example.resource.Spot;
import com.example.resource.Ticket;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class Parking {

    protected int ticketNr = 1;
    protected int receiptNr = 1;

    protected Map<String, Spot> availableSpots = new HashMap<>();
    protected Map<Integer, Ticket> tickets = new HashMap<>();

    public abstract boolean isVehicleAllowed(String vehicleType);

    public abstract long calculateFee(String vehicleType, long time);

    public Ticket parkVehicle(String vehicleType) {
        if (!isVehicleAllowed(vehicleType)) {
            System.out.println(vehicleType + " is not supported in this parking");
            return null;
        }
        int nextSpot = reserveSpot(vehicleType);
        Ticket ticket = null;
        if (nextSpot != -1) {
            Date entryTime = new Date();
            ticket = new Ticket(ticketNr, nextSpot, entryTime, vehicleType);
            tickets.put(ticketNr, ticket);
            ticketNr++;
            System.out.println(ticket.printTicket());
        }
        return ticket;
    }

    public Ticket exitVehicle(int ticketNr) {
        Ticket ticket = null;
        if (tickets.containsKey(ticketNr)) {

            ticket = tickets.get(ticketNr);
            String vehicleType = ticket.getVehicleType();
            Date entryTime = ticket.getEntryTime();
            Date exitTime = ticket.getExitTime();

            long time = TimeUnit.MILLISECONDS.convert(Math.abs(entryTime.getTime() - exitTime.getTime()), TimeUnit.MILLISECONDS);
            long fee = calculateFee(vehicleType, time);

            ticket.setFee(fee);
            ticket.setReceiptNr(receiptNr);
            System.out.println(ticket.printReceipt());
            receiptNr++;

            freeSpot(vehicleType);
        }
        return ticket;
    }

    private int reserveSpot(String vehicleType) {
        Spot spot = availableSpots.get(vehicleType);
        int nextSpot = spot.getNextSpotNr();
        spot.setCurrentSpotNr(nextSpot);
        availableSpots.put(vehicleType, spot);
        return nextSpot;
    }

    private void freeSpot(String vehicleType) {
        Spot spot = availableSpots.get(vehicleType);
        int spotNr = spot.freeSpotNr();
        spot.setCurrentSpotNr(spotNr);
        availableSpots.put(vehicleType, spot);
    }
}

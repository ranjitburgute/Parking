package com.example.parking;

import com.example.resource.Spot;
import com.example.resource.Ticket;

import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class Parking {

    protected int ticketNr = 1;
    protected int receiptNr = 1;

    protected Map<String, Spot> availableSpots = new HashMap<>();
    protected Map<Integer, Ticket> tickets = new HashMap<>();
    protected List<String> supportedVehicle = new ArrayList<>();

    public boolean isVehicleAllowed(String vehicleType) {
        if (supportedVehicle != null && supportedVehicle.size() > 0) {
            return supportedVehicle.contains(vehicleType);
        }
        return false;
    }

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

            removeSpot(vehicleType);
        }
        return ticket;
    }

    private int reserveSpot(String vehicleType) {
        Spot spot = availableSpots.get(vehicleType);
        int nextSpot = spot.getNextSpot();
        if (nextSpot != -1) {
            spot.setCurrentSpotNr(nextSpot);
            availableSpots.put(vehicleType, spot);
        }
        return nextSpot;
    }

    private void removeSpot(String vehicleType) {
        Spot spot = availableSpots.get(vehicleType);
        int spotNr = spot.removeSpot();
        spot.setCurrentSpotNr(spotNr);
        availableSpots.put(vehicleType, spot);
    }
}

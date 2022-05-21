package com.example.parking;

import com.example.ticket.Ticket;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class Parking {

    protected int ticketNr = 1;
    protected int receiptNr = 1;
    protected int nextSpotBike;
    protected int nextSpotCar;
    protected int nextSpotBus;
    protected int totalSpotsBike;
    protected int totalSpotsCar;
    protected int totalSpotsBus;
    protected Map<Integer, Ticket> parkingTickets = new HashMap<>();

    public abstract boolean isVehicleAllowed(String vehicleType);

    public abstract long getFee(String vehicleType, long time);

    public Ticket parkVehicle(String vehicleType) {
        System.out.println();
        if (!isVehicleAllowed(vehicleType)) {
            System.out.println(vehicleType + " is not supported in this parking");
            return null;
        }
        if (nextSpotBike < totalSpotsBike) {
            nextSpotBike++;
            System.out.print("ENTRY | SpotNr:" + nextSpotBike);
        } else if (nextSpotCar < totalSpotsCar) {
            nextSpotCar++;
            System.out.print("ENTRY | SpotNr:" + nextSpotCar);
        } else if (nextSpotBus < totalSpotsBus) {
            nextSpotBus++;
            System.out.print("ENTRY | SpotNr:" + nextSpotBus);
        } else {
            System.out.print("No Parking spot available for " + vehicleType);
            return null;
        }

        Date entryTime = new Date();
        Ticket parkingTicket = new Ticket(ticketNr, nextSpotBike, entryTime, vehicleType);
        parkingTickets.put(ticketNr, parkingTicket);

        System.out.print(" | ticketNr:" + ticketNr
                + " | entry time:" + entryTime);

        ticketNr++;

        return parkingTicket;
    }

    public Ticket exitVehicle(int ticketNr) {
        Ticket ticket = null;
        if (parkingTickets.containsKey(ticketNr)) {
            ticket = parkingTickets.get(ticketNr);
            String vehicleType = ticket.getVehicleType();
            Date entryTime = ticket.getEntryTime();
            Date exitTime = ticket.getExitTime();

            long time = TimeUnit.MILLISECONDS.convert(Math.abs(entryTime.getTime() - exitTime.getTime()), TimeUnit.MILLISECONDS);

            long fee = getFee(vehicleType, time);
            ticket.setFee(fee);
            System.out.println();
            System.out.print("EXIT | ticketNr:" + "R-" + receiptNr
                    + " | spotNr:" + ticket.getSpotNr()
                    + " | fee:" + fee);

            receiptNr++;
            if (Constants.BIKE.equalsIgnoreCase(vehicleType)) {
                nextSpotBike--;
            } else if (Constants.CAR.equalsIgnoreCase(vehicleType)) {
                nextSpotCar--;
            } else if (Constants.BUS.equalsIgnoreCase(vehicleType)) {
                nextSpotBus--;
            }

            System.out.print(" | entry time:" + entryTime
                    + " | exit time:" + exitTime);
        }
        return ticket;
    }

    public static class Constants {

        public static final String CAR = "car";
        public static final String BIKE = "bike";
        public static final String BUS = "bus";

        public static final long MIN = 60 * 1000L;
        public static final long HOUR = 60 * MIN;
        public static final long DAY = 24 * HOUR;
    }
}

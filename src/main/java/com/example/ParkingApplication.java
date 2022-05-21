package com.example;

import com.example.parking.Mall;
import com.example.parking.Parking;
import com.example.ticket.Ticket;

import java.util.Date;

public class ParkingApplication {

    public static void main(String[] args) {

        Mall mall = new Mall(2, 0, 0);

        Ticket ticket1 = mall.parkVehicle(Parking.Constants.BUS);
        Date ticket1Entry = ticket1.getEntryTime();

        Ticket ticket2 = mall.parkVehicle(Parking.Constants.BUS);
        Date ticket2Entry = ticket2.getEntryTime();

        Ticket ticket3 = mall.parkVehicle(Parking.Constants.BUS);

        ticket2.setExitTime(new Date(ticket2Entry.getTime() + Parking.Constants.HOUR));
        mall.exitVehicle(ticket2.getTickerNr());

        Ticket ticket4 = mall.parkVehicle(Parking.Constants.BUS);
        Date ticket4Entry = ticket4.getEntryTime();

        ticket1.setExitTime(new Date(ticket1Entry.getTime() + 4 * Parking.Constants.HOUR));
        mall.exitVehicle(ticket1.getTickerNr());
    }
}
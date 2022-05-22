package com.example;

import com.example.parking.Mall;
import com.example.resource.Ticket;
import com.example.utils.Constants;

import java.util.Date;

public class ParkingApplication {

    public static void main(String[] args) {

        Mall mall = new Mall(2, 0, 0);

        Ticket ticket1 = mall.parkVehicle(Constants.BIKE);
        Date ticket1Entry = ticket1.getEntryTime();

        Ticket ticket2 = mall.parkVehicle(Constants.BIKE);
        Date ticket2Entry = ticket2.getEntryTime();

        mall.parkVehicle(Constants.BIKE);

        ticket2.setExitTime(new Date(ticket2Entry.getTime() + Constants.HOUR));
        mall.exitVehicle(ticket2.getTickerNr());

        mall.parkVehicle(Constants.BIKE);

        ticket1.setExitTime(new Date(ticket1Entry.getTime() + 4 * Constants.HOUR));
        mall.exitVehicle(ticket1.getTickerNr());
    }
}
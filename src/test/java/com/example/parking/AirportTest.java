package com.example.parking;

import com.example.ticket.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportTest {

//    Motorcycle parked for 55 mins. Fees: 0
//    Motorcycle parked for 14 hours and 59 mins. Fees: 60
//    Motorcycle parked for 1 day and 12 hours. Fees: 160
//    Car parked for 50 mins. Fees: 60
//    SUV parked for 23 hours and 59 mins. Fees: 80
//    Car parked for 3 days and 1 hour. Fees: 400

    @Test
    public void airportCase1() {

        Airport airport = new Airport(200, 500, 100);

        Ticket ticket1 = airport.parkVehicle(Parking.Constants.BIKE);
        Date ticket1Entry = ticket1.getEntryTime();
        ticket1.setExitTime(new Date(ticket1Entry.getTime() + 55 * Parking.Constants.MIN));
        airport.exitVehicle(ticket1.getTickerNr());
        assertEquals(0, ticket1.getFee());

        Ticket ticket2 = airport.parkVehicle(Parking.Constants.BIKE);
        Date ticket2Entry = ticket2.getEntryTime();
        ticket2.setExitTime(new Date(ticket2Entry.getTime() + 14 * Parking.Constants.HOUR + 59 * Parking.Constants.MIN));
        airport.exitVehicle(ticket2.getTickerNr());
        assertEquals(60, ticket2.getFee());

        Ticket ticket3 = airport.parkVehicle(Parking.Constants.BIKE);
        Date ticket3Entry = ticket3.getEntryTime();
        ticket3.setExitTime(new Date(ticket3Entry.getTime() + 24 * Parking.Constants.HOUR + 12 * Parking.Constants.HOUR));
        airport.exitVehicle(ticket3.getTickerNr());
        assertEquals(160, ticket3.getFee());

        Ticket ticket4 = airport.parkVehicle(Parking.Constants.CAR);
        Date ticket4Entry = ticket4.getEntryTime();
        ticket4.setExitTime(new Date(ticket4Entry.getTime() + 50 * Parking.Constants.MIN));
        airport.exitVehicle(ticket4.getTickerNr());
        assertEquals(60, ticket4.getFee());

        Ticket ticket5 = airport.parkVehicle(Parking.Constants.CAR);
        Date ticket5Entry = ticket5.getEntryTime();
        ticket5.setExitTime(new Date(ticket5Entry.getTime() + 23 * Parking.Constants.HOUR + 59 * Parking.Constants.MIN));
        airport.exitVehicle(ticket5.getTickerNr());
        assertEquals(80, ticket5.getFee());

        Ticket ticket6 = airport.parkVehicle(Parking.Constants.CAR);
        Date ticket6Entry = ticket6.getEntryTime();
        ticket6.setExitTime(new Date(ticket6Entry.getTime() + 3 * 24 * Parking.Constants.HOUR + 1 * Parking.Constants.HOUR));
        airport.exitVehicle(ticket6.getTickerNr());
        assertEquals(400, ticket6.getFee());
    }
}

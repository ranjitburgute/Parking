package com.example.parking;

import com.example.ticket.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MallTest {

//    Park motorcycle
//    Park scooter
//    Park scooter: No space available
//    Exit scooter, ticket number 002, fee 10
//    Park motorcycle
//    Exit motorcycle, ticket number 001, fee 40

    @Test
    public void mallCase1() {

        Mall mall = new Mall(2, 0, 0);

        Ticket ticket1 = mall.parkVehicle(Parking.Constants.BIKE);
        Date ticket1Entry = ticket1.getEntryTime();

        Ticket ticket2 = mall.parkVehicle(Parking.Constants.BIKE);
        Date ticket2Entry = ticket2.getEntryTime();

        Ticket ticket3 = mall.parkVehicle(Parking.Constants.BIKE);

        ticket2.setExitTime(new Date(ticket2Entry.getTime() + Parking.Constants.HOUR));
        mall.exitVehicle(ticket2.getTickerNr());
        assertEquals(10, ticket2.getFee());

        Ticket ticket4 = mall.parkVehicle(Parking.Constants.BIKE);
        Date ticket4Entry = ticket4.getEntryTime();

        ticket1.setExitTime(new Date(ticket1Entry.getTime() + 4 * Parking.Constants.HOUR));
        mall.exitVehicle(ticket1.getTickerNr());
        assertEquals(40, ticket1.getFee());
    }


//    Motorcycle parked for 3 hours and 30 mins. Fees: 40
//    Car parked for 6 hours and 1 min. Fees: 140
//    Truck parked for 1 hour and 59 mins. Fees: 100

    @Test
    public void mallCase2() {

        Mall mall = new Mall(100, 80, 10);

        Ticket ticket1 = mall.parkVehicle(Parking.Constants.BIKE);
        Date ticket1Entry = ticket1.getEntryTime();
        ticket1.setExitTime(new Date(ticket1Entry.getTime() + 3 * Parking.Constants.HOUR + 30 * Parking.Constants.MIN));
        mall.exitVehicle(ticket1.getTickerNr());
        assertEquals(40, ticket1.getFee());

        Ticket ticket2 = mall.parkVehicle(Parking.Constants.CAR);
        Date ticket2Entry = ticket2.getEntryTime();
        ticket2.setExitTime(new Date(ticket2Entry.getTime() + 6 * Parking.Constants.HOUR + Parking.Constants.MIN));
        mall.exitVehicle(ticket2.getTickerNr());
        assertEquals(140, ticket2.getFee());

        Ticket ticket3 = mall.parkVehicle(Parking.Constants.BUS);
        Date ticket3Entry = ticket3.getEntryTime();
        ticket3.setExitTime(new Date(ticket3Entry.getTime() + Parking.Constants.HOUR + 59 * Parking.Constants.MIN));
        mall.exitVehicle(ticket3.getTickerNr());
        assertEquals(100, ticket3.getFee());
    }
}

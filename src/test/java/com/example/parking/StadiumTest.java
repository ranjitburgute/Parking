package com.example.parking;

import com.example.resource.Ticket;
import com.example.utils.Constants;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StadiumTest {

//    Motorcycle parked for 3 hours and 40 mins. Fees: 30
//    Motorcycle parked for 14 hours and 59 mins. Fees: 390.
//            ○ 30 for the first 4 hours. 60 for the next 8 hours. And then 300 for the remaining duration.
//    Electric SUV parked for 11 hours and 30 mins. Fees: 180.
//            ○ 60 for the first 4 hours and then 120 for the remaining duration.
//    SUV parked for 13 hours and 5 mins. Fees: 580.
//            ○ 60 for the first 4 hours and then 120 for the next 8 hours. 400 for the remaining duration.

    @Test
    public void stadiumCase1() {

        Stadium stadium = new Stadium(1000, 1500);

        System.out.println("=================== Stadium 1 ==============================");
        Ticket ticket1 = stadium.parkVehicle(Constants.BIKE);
        Date ticket1Entry = ticket1.getEntryTime();
        ticket1.setExitTime(new Date(ticket1Entry.getTime() + 3 * Constants.HOUR + 40 * Constants.MIN));
        stadium.exitVehicle(ticket1.getTickerNr());
        assertEquals(30, ticket1.getFee());

        Ticket ticket2 = stadium.parkVehicle(Constants.BIKE);
        Date ticket2Entry = ticket2.getEntryTime();
        ticket2.setExitTime(new Date(ticket2Entry.getTime() + 14 * Constants.HOUR + 59 * Constants.MIN));
        stadium.exitVehicle(ticket2.getTickerNr());
        assertEquals(390, ticket2.getFee());

        Ticket ticket3 = stadium.parkVehicle(Constants.CAR);
        Date ticket3Entry = ticket3.getEntryTime();
        ticket3.setExitTime(new Date(ticket3Entry.getTime() + 11 * Constants.HOUR + 30 * Constants.MIN));
        stadium.exitVehicle(ticket3.getTickerNr());
        assertEquals(180, ticket3.getFee());

        Ticket ticket4 = stadium.parkVehicle(Constants.CAR);
        Date ticket4Entry = ticket4.getEntryTime();
        ticket4.setExitTime(new Date(ticket4Entry.getTime() + 13 * Constants.HOUR + 5 * Constants.MIN));
        stadium.exitVehicle(ticket4.getTickerNr());
        assertEquals(580, ticket4.getFee());
    }
}

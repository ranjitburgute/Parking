package com.example.ticket;

import java.util.Date;

public class Ticket {

    private final int tickerNr;
    private final int spotNr;
    private final Date entryTime;
    private final String vehicleType;
    private long fee;
    private Date exitTime;

    public Ticket(int tickerNr, int spotNr, Date entryTime, String vehicleType) {
        this.tickerNr = tickerNr;
        this.spotNr = spotNr;
        this.entryTime = entryTime;
        this.vehicleType = vehicleType;
    }

    public int getSpotNr() {
        return spotNr;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public int getTickerNr() {
        return tickerNr;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }


}


package com.example.resource;

import java.util.Date;

public class Ticket {

    private final String vehicleType;
    private final int tickerNr;
    private final int spotNr;
    private final Date entryTime;
    private int receiptNr;
    private Date exitTime;
    private long fee;

    public Ticket(int tickerNr, int spotNr, Date entryTime, String vehicleType) {
        this.tickerNr = tickerNr;
        this.spotNr = spotNr;
        this.entryTime = entryTime;
        this.vehicleType = vehicleType;
    }

    public void setReceiptNr(int receiptNr) {
        this.receiptNr = receiptNr;
    }

    public String printTicket() {
        return "Ticket{" +
                "tickerNr=" + tickerNr +
                ", vehicleType='" + vehicleType + '\'' +
                ", spotNr=" + spotNr +
                ", entryTime=" + entryTime +
                '}';
    }

    public String printReceipt() {
        return "Receipt{" +
                "receiptNr=R-" + receiptNr +
                ", vehicleType='" + vehicleType + '\'' +
                ", fee=" + fee +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                '}';
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


package com.example.resource;

public class Spot {
    protected int currentSpotNr;
    protected int totalSpots;

    public Spot(int totalSpots) {
        this.currentSpotNr = 0;
        this.totalSpots = totalSpots;
    }

    public int getNextSpotNr() {
        if (currentSpotNr < totalSpots) {
            currentSpotNr++;
        } else {
            System.out.println("No Parking spot available...");
            return -1;
        }
        return currentSpotNr;
    }

    public int freeSpotNr() {
        if (currentSpotNr > 0) {
            currentSpotNr--;
        }
        return currentSpotNr;
    }

    public void setCurrentSpotNr(int currentSpotNr) {
        this.currentSpotNr = currentSpotNr;
    }
}


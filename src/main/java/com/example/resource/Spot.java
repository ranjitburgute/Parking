package com.example.resource;

public class Spot {
    protected int currentSpotNr;
    protected int totalSpots;

    public Spot(int totalSpots) {
        this.currentSpotNr = 0;
        this.totalSpots = totalSpots;
    }

    public int getNextSpot() {
        if (currentSpotNr < totalSpots) {
            currentSpotNr++;
        } else {
            System.out.println("No parking space available...");
            return -1;
        }
        return currentSpotNr;
    }

    public int removeSpot() {
        if (currentSpotNr > 1) {
            currentSpotNr--;
        }
        return currentSpotNr;
    }

    public void setCurrentSpotNr(int currentSpotNr) {
        this.currentSpotNr = currentSpotNr;
    }
}


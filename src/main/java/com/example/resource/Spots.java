package com.example.resource;

public class Spots {
    protected int totalSpots;
    protected int[] spots;

    public Spots(int totalSpots) {

        this.totalSpots = totalSpots;
        this.spots = new int[totalSpots];
    }

    public int getNextSpot() {
        for (int i = 0; i < spots.length; i++) {
            if (spots[i] == 0) {
                spots[i] = 1;
                return i + 1;
            }
        }
        System.out.println("No parking space available...");
        return -1;
    }

    public void removeSpot(int spotNr) {
        spots[spotNr - 1] = 0;
    }
}


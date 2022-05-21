package com.example.parking;

public class Mall extends Parking {

    public Mall(int totalSpotsBike, int totalSpotsCar, int totalSpotsBus) {
        super.totalSpotsBike = totalSpotsBike;
        super.totalSpotsCar = totalSpotsCar;
        super.totalSpotsBus = totalSpotsBus;
    }

    public boolean isVehicleAllowed(String vehicle) {
        String supported = Constants.BIKE + Constants.CAR + Constants.BUS;
        return supported.contains(vehicle);
    }

    public long getFee(String vehicleType, long time) {
        int rate = 0;
        long fee = 0;
        if (Constants.BIKE.equalsIgnoreCase(vehicleType)) {
            rate = 10;
        }
        if (Constants.CAR.equalsIgnoreCase(vehicleType)) {
            rate = 20;
        }
        if (Constants.BUS.equalsIgnoreCase(vehicleType)) {
            rate = 50;
        }
        if (time % Constants.HOUR > 0) {
            fee += rate;
        }
        long hours = time / Constants.HOUR;
        return fee + hours * rate;
    }
}

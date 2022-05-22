package com.example.parking;

import com.example.resource.Spots;
import com.example.utils.Constants;

public class Mall extends Parking {

    public Mall(int totalSpotsBike, int totalSpotsCar, int totalSpotsBus) {
        spots.put(Constants.BIKE, new Spots(totalSpotsBike));
        spots.put(Constants.CAR, new Spots(totalSpotsCar));
        spots.put(Constants.BUS, new Spots(totalSpotsBus));

        supportedVehicle.add(Constants.BIKE);
        supportedVehicle.add(Constants.CAR);
        supportedVehicle.add(Constants.BUS);
    }

    public long calculateFee(String vehicleType, long time) {
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
        if (time < Constants.HOUR || time % Constants.HOUR > 0) {
            fee += rate;
        }
        long hours = time / Constants.HOUR;

        return fee + hours * rate;
    }
}

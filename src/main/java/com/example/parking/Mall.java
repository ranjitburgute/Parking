package com.example.parking;

import com.example.resource.Spots;
import com.example.utils.Constants;


public class Mall extends Parking {

    public static final int BIKE_FEE_PER_HOUR = 10;
    public static final int CAR_FEE_PER_HOUR = 20;
    public static final int BUS_FEE_PER_HOUR = 50;

    public Mall(int totalSpotsBike, int totalSpotsCar, int totalSpotsBus) {
        spots.put(Constants.BIKE, new Spots(totalSpotsBike));
        spots.put(Constants.CAR, new Spots(totalSpotsCar));
        spots.put(Constants.BUS, new Spots(totalSpotsBus));

        supportedVehicle.add(Constants.BIKE);
        supportedVehicle.add(Constants.CAR);
        supportedVehicle.add(Constants.BUS);
    }

    /**
     * Per-hour flat fees
     *
     * @param vehicleType
     * @param time
     * @return
     */
    public long calculateFee(String vehicleType, long time) {
        int rate = 0;
        long fee = 0;
        if (Constants.BIKE.equalsIgnoreCase(vehicleType)) {
            rate = BIKE_FEE_PER_HOUR;
        }
        if (Constants.CAR.equalsIgnoreCase(vehicleType)) {
            rate = CAR_FEE_PER_HOUR;
        }
        if (Constants.BUS.equalsIgnoreCase(vehicleType)) {
            rate = BUS_FEE_PER_HOUR;
        }
        if (time < Constants.HOUR || time % Constants.HOUR > 0) {
            fee += rate;
        }
        long hours = time / Constants.HOUR;

        return fee + hours * rate;
    }
}

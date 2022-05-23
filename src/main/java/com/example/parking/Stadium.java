package com.example.parking;

import com.example.resource.Spots;
import com.example.utils.Constants;

public class Stadium extends Parking {

    public static final int BIKE_FEE_PER_00_04_HOUR = 30;
    public static final int BIKE_FEE_PER_04_12_HOUR = 60;
    public static final int BIKE_FEE_PER_12_N_HOUR = 100;

    public static final int CAR_FEE_PER_00_04_HOUR = 60;
    public static final int CAR_FEE_PER_04_12_HOUR = 120;
    public static final int CAR_FEE_PER_12_N_HOUR = 200;

    public Stadium(int totalSpotsBike, int totalSpotsCar) {

        spots.put(Constants.BIKE, new Spots(totalSpotsBike));
        spots.put(Constants.CAR, new Spots(totalSpotsCar));

        supportedVehicle.add(Constants.BIKE);
        supportedVehicle.add(Constants.CAR);
    }

    @Override
    public long calculateFee(String vehicleType, long time) {
        long fee = 0;
        if (Constants.BIKE.equalsIgnoreCase(vehicleType)) {
            fee = calculateBikeFee(time);
        } else if (Constants.CAR.equalsIgnoreCase(vehicleType)) {
            fee = calculateCarFee(time);
        }
        return fee;
    }

    /**
     * Flat rate up to a few hours and then per-hour rate.
     * The total fee is the sum of all the previous interval fees
     */
    public long calculateBikeFee(long time) {
        long fee = 0;
        while (time > 0) {
            if (time > 12 * Constants.HOUR) {
                long moreThan12Hrs = time - 12 * Constants.HOUR;
                fee += moreThan12Hrs / Constants.HOUR * BIKE_FEE_PER_12_N_HOUR;
                if (moreThan12Hrs % Constants.HOUR > 0) {
                    fee += BIKE_FEE_PER_12_N_HOUR;
                }
                time = 12 * Constants.HOUR;
            } else if (time < 12 * Constants.HOUR && time > 4 * Constants.HOUR) {
                fee += BIKE_FEE_PER_04_12_HOUR;
                time -= (12 - 4) * Constants.HOUR;
            } else {
                fee += BIKE_FEE_PER_00_04_HOUR;
                time -= 4 * Constants.HOUR;
            }
        }
        return fee;
    }

    /**
     * Flat rate up to a few hours and then per-hour rate.
     * The total fee is the sum of all the previous interval fees
     */
    public long calculateCarFee(long time) {
        long fee = 0;
        while (time > 0) {
            if (time > 12 * Constants.HOUR) {
                long moreThan12Hrs = time - 12 * Constants.HOUR;
                fee += moreThan12Hrs / Constants.HOUR * CAR_FEE_PER_12_N_HOUR;
                if (moreThan12Hrs % Constants.HOUR > 0) {
                    fee += CAR_FEE_PER_12_N_HOUR;
                }
                time = 12 * Constants.HOUR;
            } else if (time < 12 * Constants.HOUR && time > 4 * Constants.HOUR) {
                fee += CAR_FEE_PER_04_12_HOUR;
                time -= (12 - 4) * Constants.HOUR;
            } else {
                fee += CAR_FEE_PER_00_04_HOUR;
                time -= 4 * Constants.HOUR;
            }
        }
        return fee;
    }
}

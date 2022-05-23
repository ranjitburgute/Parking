package com.example.parking;

import com.example.resource.Spots;
import com.example.utils.Constants;

public class Airport extends Parking {

    public static final int BIKE_FEE_PER_01_08_HOUR = 40;
    public static final int BIKE_FEE_PER_08_24_HOUR = 60;
    public static final int BIKE_FEE_MORE_THAN_DAY = 80;

    public static final int CAR_FEE_PER_00_12_HOUR = 60;
    public static final int CAR_FEE_PER_12_24_HOUR = 80;
    public static final int CAR_FEE_MORE_THAN_DAY = 100;

    public Airport(int totalSpotsBike, int totalSpotsCar) {
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
     * Flat rate up to one day. Then per-day rate.
     * There is no summing up of the previous interval fees.
     */
    public int calculateBikeFee(long time) {
        int fee = 0;
        while (time > 0) {
            if (time > Constants.DAY) {
                long days = time / Constants.DAY;
                fee += BIKE_FEE_MORE_THAN_DAY * days;
                if (time % Constants.DAY > 0) {
                    fee += BIKE_FEE_MORE_THAN_DAY;
                }
                return fee;
            } else if (time > 8 * Constants.HOUR && time < 24 * Constants.HOUR) {
                fee += BIKE_FEE_PER_08_24_HOUR;
                time -= 24 * Constants.HOUR;
            } else if (time > Constants.HOUR && time < 8 * Constants.HOUR) {
                fee += BIKE_FEE_PER_01_08_HOUR;
                time -= 8 * Constants.HOUR;
            } else if (time < Constants.HOUR) {
                time -= Constants.HOUR;
            }
        }
        return fee;
    }

    /**
     * Flat rate up to one day. Then per-day rate.
     * There is no summing up of the previous interval fees.
     */
    public long calculateCarFee(long time) {
        long fee = 0;
        while (time > 0) {
            if (time > Constants.DAY) {
                long days = time / Constants.DAY;
                fee += CAR_FEE_MORE_THAN_DAY * days;
                if (time % Constants.DAY > 0) {
                    fee += CAR_FEE_MORE_THAN_DAY;
                }
                return fee;
            } else if (time > 12 * Constants.HOUR && time < 24 * Constants.HOUR) {
                fee += CAR_FEE_PER_12_24_HOUR;
                time -= 24 * Constants.HOUR;
            } else if (time < 12 * Constants.HOUR) {
                fee += CAR_FEE_PER_00_12_HOUR;
                time -= 12 * Constants.HOUR;
            }
        }
        return fee;
    }
}

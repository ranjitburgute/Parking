package com.example.parking;

import com.example.resource.Spots;
import com.example.utils.Constants;

public class Stadium extends Parking {

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

    public long calculateBikeFee(long time) {
        long fee = 0;
        while (time > 0) {
            if (time > 12 * Constants.HOUR) {
                long moreThan12Hrs = time - 12 * Constants.HOUR;
                fee += moreThan12Hrs / Constants.HOUR * 100;
                if (moreThan12Hrs % Constants.HOUR > 0) {
                    fee += 100;
                }
                time = 12 * Constants.HOUR;
            } else if (time < 12 * Constants.HOUR && time > 4 * Constants.HOUR) {
                fee += 60;
                time -= 8 * Constants.HOUR;
            } else {
                fee += 30;
                time -= 4 * Constants.HOUR;
            }
        }
        return fee;
    }

    public long calculateCarFee(long time) {
        long fee = 0;
        while (time > 0) {
            if (time > 12 * Constants.HOUR) {
                long moreThan12Hrs = time - 12 * Constants.HOUR;
                fee += moreThan12Hrs / Constants.HOUR * 200;
                if (moreThan12Hrs % Constants.HOUR > 0) {
                    fee += 200;
                }
                time = 12 * Constants.HOUR;
            } else if (time < 12 * Constants.HOUR && time > 4 * Constants.HOUR) {
                fee += 120;
                time -= 8 * Constants.HOUR;
            } else {
                fee += 60;
                time -= 4 * Constants.HOUR;
            }
        }
        return fee;
    }
}

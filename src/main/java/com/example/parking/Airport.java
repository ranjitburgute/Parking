package com.example.parking;

import com.example.resource.Spots;
import com.example.utils.Constants;

public class Airport extends Parking {

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

    public int calculateBikeFee(long time) {
        int fee = 0;
        while (time > 0) {
            if (time > Constants.DAY) {
                long days = time / Constants.DAY;
                fee += 80 * days;
                if (time % Constants.DAY > 0) {
                    fee += 80;
                }
                return fee;
            } else if (time > 8 * Constants.HOUR && time < 24 * Constants.HOUR) {
                fee += 60;
                time -= 16 * Constants.HOUR;
            } else if (time > Constants.HOUR && time < 8 * Constants.HOUR) {
                fee += 40;
                time -= 8 * Constants.HOUR;
            } else if (time < Constants.HOUR) {
                time -= Constants.HOUR;
            }
        }
        return fee;
    }

    public long calculateCarFee(long time) {
        long fee = 0;
        while (time > 0) {
            if (time > Constants.DAY) {
                long days = time / Constants.DAY;
                fee += 100 * days;
                if (time % Constants.DAY > 0) {
                    fee += 100;
                }
                return fee;

            } else if (time > 12 * Constants.HOUR && time < 24 * Constants.HOUR) {
                fee += 80;
                time -= 24 * Constants.HOUR;
            } else if (time < 12 * Constants.HOUR) {
                fee += 60;
                time -= 12 * Constants.HOUR;
            }
        }
        return fee;
    }
}

package com.example.parking;

import com.example.resource.Spot;
import com.example.utils.Constants;

public class Airport extends Parking {

    public Airport(int totalSpotsBike, int totalSpotsCar) {
        availableSpots.put(Constants.BIKE, new Spot(totalSpotsBike));
        availableSpots.put(Constants.CAR, new Spot(totalSpotsCar));
    }

    public boolean isVehicleAllowed(String vehicle) {
        String supported = Constants.BIKE + Constants.CAR;
        return supported.contains(vehicle);
    }

    @Override
    public long calculateFee(String vehicleType, long time) {
        long fee = 0;
        if (Constants.BIKE.equalsIgnoreCase(vehicleType)) {
            fee = getBikeFee(time);
        } else if (Constants.CAR.equalsIgnoreCase(vehicleType)) {
            fee = getCarFee(time);
        }
        return fee;
    }

    public int getBikeFee(long time) {
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

    public long getCarFee(long time) {
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

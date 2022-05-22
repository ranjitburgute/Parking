package com.example.parking;

import com.example.resource.Spot;
import com.example.utils.Constants;

public class Stadium extends Parking {

    public Stadium(int totalSpotsBike, int totalSpotsCar) {

        availableSpots.put(Constants.BIKE, new Spot(totalSpotsBike));
        availableSpots.put(Constants.CAR, new Spot(totalSpotsCar));
        addSupportedVehicle();
    }

    public void addSupportedVehicle() {
        supportedVehicle.add(Constants.BIKE);
        supportedVehicle.add(Constants.CAR);
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

    public long getBikeFee(long time) {
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

    public long getCarFee(long time) {
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

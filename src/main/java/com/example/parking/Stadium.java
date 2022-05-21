package com.example.parking;

public class Stadium extends Parking {

    public Stadium(int totalSpotsBike, int totalSpotsCar, int totalSpotsBus) {
        this.totalSpotsBike = totalSpotsBike;
        this.totalSpotsCar = totalSpotsCar;
        this.totalSpotsBus = totalSpotsBus;
    }

    @Override
    public boolean isVehicleAllowed(String vehicle) {
        String supported = Constants.BIKE + Constants.CAR;
        return supported.contains(vehicle);
    }

    @Override
    public long getFee(String vehicleType, long time) {
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

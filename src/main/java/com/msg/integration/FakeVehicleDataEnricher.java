package com.msg.integration;

import com.msg.domain.vehicle.enums.FuelType;
import com.msg.domain.vehicle.model.Vehicle;

import java.util.Random;

public class FakeVehicleDataEnricher implements VehicleDataEnricher {
    @Override
    public void setVehicleAdditionalData(Vehicle vehicle) {
        FuelType fuelType[] = FuelType.values();

        vehicle.setPower(10);
        vehicle.setDoors(2);
        vehicle.setFuelType(fuelType[new Random().nextInt(fuelType.length)]);
    }
}

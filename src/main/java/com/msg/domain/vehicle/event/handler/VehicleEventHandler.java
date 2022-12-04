package com.msg.domain.vehicle.event.handler;

import com.msg.domain.vehicle.model.Vehicle;
import com.msg.integration.VehicleDataEnricher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler
public class VehicleEventHandler {

    @Autowired
    private VehicleDataEnricher enricher;

    @HandleBeforeCreate
    public void handleVehicleBeforeCreate(Vehicle vehicle) {
        enricher.setVehicleAdditionalData(vehicle);
    }
}

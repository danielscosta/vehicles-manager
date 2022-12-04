package com.msg.configuration;

import com.msg.domain.vehicle.event.handler.VehicleEventHandler;
import com.msg.integration.FakeVehicleDataEnricher;
import com.msg.integration.VehicleDataEnricher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    VehicleEventHandler vehicleEventHandler() {
        return new VehicleEventHandler();
    }

    @Bean
    VehicleDataEnricher vehicleDataEnricher() {
        return new FakeVehicleDataEnricher();
    }
}

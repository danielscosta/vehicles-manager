package com.msg;

import com.msg.domain.vehicle.enums.Country;
import com.msg.domain.vehicle.model.Vehicle;
import com.msg.domain.vehicle.repository.VehicleRepository;
import com.msg.domain.vehicle.service.VehicleService;
import com.msg.integration.VehicleDataEnricher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VehiclesRestServiceApplicationTests {

    @Autowired
    private VehicleDataEnricher vehicleDataEnricher;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void contextLoads() {
        assertThat(vehicleDataEnricher).isNotNull();
        assertThat(vehicleDataEnricher).isNotNull();
        assertThat(vehicleRepository).isNotNull();
    }

    @Test
    void createVehicleEnrichSaveAndFindByLicenseAndCountry() {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicense("AB 00 03");
        vehicle.setCountry(Country.PT);
        vehicle.setRiskFactor(new BigDecimal("0.33"));
        vehicleDataEnricher.setVehicleAdditionalData(vehicle);

        assert vehicleRepository.save(vehicle).getId() != null;

        assert vehicleRepository.findByLicenseAndCountry("AB 00 03", Country.AF) == null;
        assert vehicleRepository.findByLicenseAndCountry("AB 00 03", Country.PT) != null;
    }

    @Test
    void bulkVehicles() {
        List<Vehicle> vehicles = vehicleService.bulk(getClass().getClassLoader().getResourceAsStream("bulkVehicles.csv"));

        assert vehicles.size() == 2;
        assert vehicles.get(0).getLicense().equals("AB 00 01");
        assert vehicles.get(1).getLicense().equals("AB 00 02");
    }
}

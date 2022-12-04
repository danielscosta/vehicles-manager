package com.msg.domain.vehicle.service;

import com.msg.domain.vehicle.enums.Country;
import com.msg.domain.vehicle.model.Vehicle;
import com.msg.domain.vehicle.repository.VehicleRepository;
import com.msg.integration.VehicleDataEnricher;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    private VehicleDataEnricher enricher;

    public List<Vehicle> bulk(InputStream inputStream) {
        try {
            List<Vehicle> vehicles = fileToVehicles(inputStream);
            return StreamSupport.stream(vehicleRepository.saveAll(vehicles).spliterator(), false).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private List<Vehicle> fileToVehicles(InputStream inputStream) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        CSVParser csvParser = new CSVParser(fileReader,
                CSVFormat.DEFAULT.builder()
                        .setHeader(new String[]{"License", "Country", "RiskFactor"})
                        .setSkipHeaderRecord(true)
                        .setTrim(true)
                        .setIgnoreHeaderCase(true).build());

        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
            String license = csvRecord.get("License");
            Country country = Country.valueOf(csvRecord.get("Country"));
            Vehicle vehicle = vehicleRepository.findByLicenseAndCountry(license, country);
            if (vehicle == null) {
                vehicle = new Vehicle();
                vehicle.setLicense(license);
                vehicle.setCountry(country);
            }
            vehicle.setRiskFactor(new BigDecimal(csvRecord.get("RiskFactor")));
            enricher.setVehicleAdditionalData(vehicle);
            vehicles.add(vehicle);
        }

        return vehicles;
    }
}

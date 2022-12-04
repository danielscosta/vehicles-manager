package com.msg.domain.vehicle.controller;

import com.msg.domain.vehicle.model.Vehicle;
import com.msg.domain.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/bulk")
    public ResponseEntity<List<Vehicle>> bulk(@RequestParam("file") MultipartFile file) throws IOException {
        List<Vehicle> vehicles = vehicleService.bulk(file.getInputStream());

        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }
}

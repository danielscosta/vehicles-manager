package com.msg.domain.vehicle.repository;

import com.msg.domain.vehicle.enums.Country;
import com.msg.domain.vehicle.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "vehicles", path = "vehicles")
public interface VehicleRepository extends CrudRepository<Vehicle, UUID> {

    @Override
    @RestResource(exported = false)
    void delete(Vehicle entity);

    @RestResource(exported = false)
    Vehicle findByLicenseAndCountry(String license, Country country);
}

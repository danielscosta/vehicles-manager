package com.msg.domain.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msg.domain.vehicle.enums.Country;
import com.msg.domain.vehicle.enums.FuelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"license", "country"}))
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(updatable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{2}\s[a-zA-Z0-9]{2}\s[a-zA-Z0-9]{2}$")
    private String license;

    @NotNull
    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private Country country;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("0.99")
    private BigDecimal riskFactor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Min(1)
    private Integer doors;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Min(1)
    private Integer power;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty
    public String getCountryName() {
        return country.getFriendlyName();
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public BigDecimal getRiskFactor() {
        return riskFactor;
    }

    public void setRiskFactor(BigDecimal riskFactor) {
        this.riskFactor = riskFactor.setScale(2);
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}

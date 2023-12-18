package com.emsi.entities;

import java.util.Objects;

public class Car {
    private Integer register;
    private String make;
    private String model;
    private int production_year;
    private double mpg;
    private int kilometrage;
    private int maxSpeed;
    private String fuelType;
    private String transmissionType;
    public static final String path = "./src/main/resources/com/emsi/Data/";

    public Car() {
    }

    public Car(Integer register, String make, String model, int production_year, double mpg, int kilometrage,
            int maxSpeed, String fuelType, String transmissionType) {
        this.register = register;
        this.make = make;
        this.model = model;
        this.production_year = production_year;
        this.mpg = mpg;
        this.kilometrage = kilometrage;
        this.maxSpeed = maxSpeed;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
    }
    
    public Car(String make, String model, int production_year, double mpg, int kilometrage, int maxSpeed, String fuelType, String transmissionType) {
        this.make = make;
        this.model = model;
        this.production_year = production_year;
        this.mpg = mpg;
        this.kilometrage = kilometrage;
        this.maxSpeed = maxSpeed;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
    }
    public Integer getRegister() {
        return this.register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return this.production_year;
    }

    public void setProductionYear(int production_year) {
        this.production_year = production_year;
    }

    public double getMpg() {
        return this.mpg;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public int getKilometrage() {
        return this.kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return this.transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }


    @Override
    public String toString() {
        return  register + " | " + make + " | " + model + " | " + production_year + " | " + mpg + " | " + kilometrage + " | " + maxSpeed + " | " + fuelType + " | " + transmissionType ;
    }
    // equals method
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return register == car.register && Objects.equals(make, car.make) && Objects.equals(model, car.model)
                && production_year == car.production_year && mpg == car.mpg && kilometrage == car.kilometrage
                && maxSpeed == car.maxSpeed && Objects.equals(fuelType, car.fuelType)
                && Objects.equals(transmissionType, car.transmissionType);
    }
    
}
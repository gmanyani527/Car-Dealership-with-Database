package com.pluralsight.dealership;

public class Vehicle {

    private int vin;
    private String make;
    private int year;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;
    private boolean sold;

    public Vehicle(int vin, String make, int year, String model, String vehicleType, boolean sold, String color, int odometer, double price) {
        this.vin = vin;
        this.make = make;
        this.year = year;
        this.model = model;
        this.vehicleType = vehicleType;
        this.sold = sold;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }



    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return
                "vin=" + vin +
                ", year=" + year +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", type='" + vehicleType + '\'' +
                ", color='" + color + '\'' +
                ", odometer=" + odometer +
                ", price=" + price +
                '}';
    }


    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}

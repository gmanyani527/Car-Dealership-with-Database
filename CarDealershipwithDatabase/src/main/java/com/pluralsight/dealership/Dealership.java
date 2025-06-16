package com.pluralsight.dealership;

import java.util.ArrayList;
public class Dealership {
    private String name;
    private String addresss;
    private String phone;
    ArrayList<Vehicle> inventory;

    public Dealership(String name, String addresss, String phone) {
        this.name = name;
        this.addresss = addresss;
        this.phone = phone;
        this.inventory = new ArrayList<>();

    }
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max){
                results.add(vehicle);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehicleByMakeModel(String make, String model){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehicleByYear(int min, int max){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getYear() > min && vehicle.getYear() < max){
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehicleByColor(String color){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getColor().equalsIgnoreCase(color)){
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehicleByMileage(double min, double max){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getOdometer() > min && vehicle.getOdometer() < max){
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehicleByType(String vehicleType){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            results.add(vehicle);
        }
        return results;
    }

    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
      //  System.out.println("Added vehicle: " + vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
        System.out.println("Removed vehicle: " + vehicle);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return addresss;
    }

    public String getPhone() {
        return phone;
    }
}

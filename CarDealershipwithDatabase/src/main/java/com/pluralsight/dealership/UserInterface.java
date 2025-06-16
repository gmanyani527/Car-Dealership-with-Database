package com.pluralsight.dealership;

import java.util.ArrayList;
import java.util.Scanner;
public class UserInterface {

private DealershipFileManager dealership;
private Dealership dealership1;

    public void display(){
        init();
        printWelcomeBanner();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();  // wait for user

            boolean running = true;
       while(running){
           displayMenu(); // So users see options every time

           System.out.println("Enter your choice: ");
           String input = scanner.nextLine();

           switch (input) {
               case "1":
                   processGetAllVehicleRequest(); // View All Vehicles
                   break;

               case "2":
                   processGetByPriceRequest(); // Search By Price
                   break;

               case "3":
                   processGetByMakeModelRequest(); // Search by Make/Model
                   break;

               case "4":
                   processGetByYearRequest(); // Search by Year
                   break;

               case "5":
                   processGetByColorRequest(); // Search by Color
                   break;

               case "6":
                   processGetByMileageRequest(); // Search by Mileage
                   break;

               case "7":
                   processGetByVehicleTypeRequest(); // Search by Vehicle Type
                   break;

               case "8":
                   processAddVehicleRequest(); // Add a Vehicle
                   break;

               case "9":
                   processRemoveVehicleRequest(); // Remove a Vehicle
                   break;

               case "0":
                   System.out.println("Goodbye!");
                   running = false;
                   break;

               default:
                   System.out.println("Invalid choice. Please try again.");
           }


       }

    }

    public void displayMenu(){
        System.out.println("\n ******* Dealership Menu *******");
        System.out.println("1. View All Vehicles");
        System.out.println("2. Search By Price");
        System.out.println("3. Search by Make/Model (Ex: Toyota... Ferrari ... PAGANNIII)");
        System.out.println("4. Search by Year (Ex: Simply the Year DUHHHH JK JK)");
        System.out.println("5. Search by Color (Ex: Hopefully your not color blind LOL)");
        System.out.println("6. Search by Mileage (Ex: Car Traveled how much... hopefully not too much)");
        System.out.println("7. Search by Vehicle Type (Ex: Sedan and SUV)");
        System.out.println("8. Add a Vehicle ");
        System.out.println("9. Remove a Vehicle");
        System.out.println("0. Quit");
    }


    private void init(){
        dealership = new DealershipFileManager();
        dealership1 = dealership.getDealership();

    }

    private void displayVehicles(ArrayList<Vehicle> vehicles){
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("\n---------------------------------------------------------------------------------------------");
        System.out.printf("| %-7s | %-4s | %-10s | %-10s | %-8s | %-8s | %-8s | %-10s |\n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Vehicle v : vehicles) {
            System.out.printf("| %-7d | %-4d | %-10s | %-10s | %-8s | %-8s | %-8d | $%-9.2f |\n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
        }

        System.out.println("---------------------------------------------------------------------------------------------\n");

    }

    public void processGetByPriceRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the lowest price you want to search for (e.g. 10,000 or 10000): ");
        String lowStr = scanner.nextLine().replace(",", "").trim();
        double price1 = Double.parseDouble(lowStr);

        System.out.println("Enter the highest price you want to search for (e.g. 20,000 or 20000): ");
        String highStr = scanner.nextLine().replace(",", "").trim();
        double price2 = Double.parseDouble(highStr);

        ArrayList<Vehicle> results = dealership1.getVehiclesByPrice(price1, price2);
        displayVehicles(results);
    }

    public void processGetByMakeModelRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter make you want to search for: ");
       String make = scanner.nextLine();
        System.out.println("Enter the model you want to search for: ");
        String model = scanner.nextLine();
        ArrayList<Vehicle> results = dealership1.getVehicleByMakeModel(make, model);

        displayVehicles(results);
    }

    public void processGetByYearRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter earliest model year to include: ");
        int minYear = scanner.nextInt();
        System.out.println("Enter the latest model year to include: ");
        int maxYear = scanner.nextInt();
        ArrayList<Vehicle> results = dealership1.getVehicleByYear(minYear, maxYear);

        displayVehicles(results);
    }

    public void processGetByColorRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter color your want to search for: ");
        String color = scanner.nextLine();
        ArrayList<Vehicle> results = dealership1.getVehicleByColor(color);

        displayVehicles(results);
    }

    public void processGetByMileageRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car minimum mileage your want to search for: ");
        double minMileage = scanner.nextDouble();
        System.out.println("Enter car maximum mileage your want to search for: ");
        double maxMileage = scanner.nextDouble();
        ArrayList<Vehicle> results = dealership1.getVehicleByMileage(minMileage, maxMileage);

        displayVehicles(results);
    }

    public void processGetByVehicleTypeRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car type your want to search for: ");
        String type = scanner.nextLine();
        ArrayList<Vehicle> results = dealership1.getVehicleByType(type);

        displayVehicles(results);
    }

    public void processGetAllVehicleRequest(){
        ArrayList<Vehicle> allVehicles = dealership1.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN number: ");
        int vin = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Make: ");
        String make = scanner.nextLine();

        System.out.print("Enter Model: ");
        String model = scanner.nextLine();

        System.out.print("Enter Vehicle Type: ");
        String type = scanner.nextLine();

        System.out.print("Enter Color: ");
        String color = scanner.nextLine();

        System.out.print("Enter Odometer Reading: ");
        int odometer = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        // Match constructor order: vin, year, make, model, type, color, odometer, price
        Vehicle newVehicle = new Vehicle(vin, make, year, model, type, color, odometer, price);


        dealership1.addVehicle(newVehicle);
        dealership.saveDealership(dealership1);
        System.out.println("Vehicle saved successfully");

    }

    public void processRemoveVehicleRequest(){
        processGetAllVehicleRequest();
        Vehicle toRemove = null;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Vehicle> results = dealership1.getAllVehicles();

        System.out.print("Enter VIN number: ");
        int vin = Integer.parseInt(scanner.nextLine());
        for (Vehicle vehicle : results) {
            if(vin == vehicle.getVin()){
                toRemove = vehicle;
                break;
            }
        }
        if(toRemove != null){
            dealership1.removeVehicle(toRemove);
            dealership.saveDealership(dealership1);
            System.out.println("Vehicle removed successfully");
        } else{
            System.out.println("Vehicle with a vin " + vin + " not found");
        }

    }

    public void printWelcomeBanner() {
        System.out.println("==============================================================");
        System.out.println("||                                                          ||");
        System.out.printf ("||        WELCOME TO %-39s||\n", dealership1.getName().toUpperCase());
        System.out.printf ("||        ADDRESS: %-41s||\n", dealership1.getAddress());
        System.out.printf ("||        PHONE:   %-41s||\n", dealership1.getPhone());
        System.out.println("||                                                          ||");
        System.out.println("==============================================================");
    }




}

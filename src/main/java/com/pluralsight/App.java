package com.pluralsight;



import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final VehicleDao vehicleDao = new VehicleDao();
    private static final SalesContractDao salesDao = new SalesContractDao();
    private static final LeaseContractDao leaseDao = new LeaseContractDao();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Car Dealership Menu ===");
            System.out.println("1. Show All Vehicles");
            System.out.println("2. Search by Make");
            System.out.println("3. Search by Price Range");
            System.out.println("4. Search by Year Range");
            System.out.println("5. Search by Color");
            System.out.println("6. Search by Mileage Range");
            System.out.println("7. Search by Type");
            System.out.println("8. Sell or Lease a Vehicle");
            System.out.println("9. Exit");

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1": showVehicles(vehicleDao.getAllVehicles()); break;
                case "2": searchByMake(); break;
                case "3": searchByPriceRange(); break;
                case "4": searchByYearRange(); break;
                case "5": searchByColor(); break;
                case "6": searchByMileage(); break;
                case "7": searchByType(); break;
                case "8": sellOrLeaseVehicle(); break;
                case "9": System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void showVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.printf("%s %s %s %s %d $%.2f\n", v.getVin(), v.getMake(), v.getModel(), v.getColor(), v.getYear(), v.getPrice());
            }
        }
    }

    private static void searchByMake() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        showVehicles(vehicleDao.getVehiclesByMake(make));
    }

    private static void searchByPriceRange() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());
        showVehicles(vehicleDao.getVehiclesByPriceRange(min, max));
    }

    private static void searchByYearRange() {
        System.out.print("Min year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max year: ");
        int max = Integer.parseInt(scanner.nextLine());
        showVehicles(vehicleDao.getVehiclesByYearRange(min, max));
    }

    private static void searchByColor() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        showVehicles(vehicleDao.getVehiclesByColor(color));
    }

    private static void searchByMileage() {
        System.out.print("Min mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max mileage: ");
        int max = Integer.parseInt(scanner.nextLine());
        showVehicles(vehicleDao.getVehiclesByMileageRange(min, max));
    }

    private static void searchByType() {
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        showVehicles(vehicleDao.getVehiclesByType(type));
    }

    private static void sellOrLeaseVehicle() {
        System.out.print("Enter VIN of vehicle to sell/lease: ");
        String vin = scanner.nextLine();
        Vehicle vehicle = vehicleDao.getVehicleByVin(vin);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Sale or Lease? (sale/lease): ");
        String type = scanner.nextLine();

        System.out.print("Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Customer Email: ");
        String email = scanner.nextLine();

        String date = java.time.LocalDate.now().toString();

        if (type.equalsIgnoreCase("sale")) {
            System.out.print("Do they want financing? (yes/no): ");
            boolean financed = scanner.nextLine().equalsIgnoreCase("yes");

            SalesContract contract = new SalesContract(date, name, email, vehicle, financed);
            salesDao.save(contract);
            vehicleDao.removeVehicle(vin);
            System.out.println("Sales contract completed.");
        } else if (type.equalsIgnoreCase("lease")) {
            LeaseContract contract = new LeaseContract(date, name, email, vehicle);
            leaseDao.save(contract);
            vehicleDao.removeVehicle(vin);
            System.out.println("Lease contract completed.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
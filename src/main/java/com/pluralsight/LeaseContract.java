package com.pluralsight;


public class LeaseContract extends Contract {
    public LeaseContract(String date, String customerName, String email, Vehicle vehicle) {
        super(date, customerName, email, vehicle);
    }

    @Override
    public double getTotalPrice() {
        double expectedEndingValue = getVehicle().getPrice() * 0.5;
        double leaseFee = getVehicle().getPrice() * 0.07;

        return expectedEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double total = getTotalPrice();
        double interestRate = 0.04;
        int months = 36;

        return (total * (1 + interestRate)) / months;
    }
}

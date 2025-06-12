package com.pluralsight;


public class SalesContract extends Contract {
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String email, Vehicle vehicle, boolean isFinanced) {
        super(date, customerName, email, vehicle);
        this.isFinanced = isFinanced;
    }

    @Override
    public double getTotalPrice() {
        double salesTax = getVehicle().getPrice() * 0.05;
        double recordingFee = 100.0;
        double processingFee = getVehicle().getPrice() < 10000 ? 295.0 : 495.0;

        return getVehicle().getPrice() + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) return 0.0;

        double loanAmount = getTotalPrice();
        double interestRate = loanAmount >= 10000 ? 0.0425 : 0.0525;
        int months = loanAmount >= 10000 ? 48 : 24;

        return (loanAmount * (1 + interestRate)) / months;
    }

    public boolean isFinanced() { return isFinanced; }
}

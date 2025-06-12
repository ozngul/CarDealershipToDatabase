package com.pluralsight;


public abstract class Contract {
    private String date;
    private String customerName;
    private String email;
    private Vehicle vehicle;

    public Contract(String date, String customerName, String email, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.email = email;
        this.vehicle = vehicle;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    public String getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public Vehicle getVehicle() { return vehicle; }
}

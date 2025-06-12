package com.pluralsight;


public class Vehicle {
    private String vin;
    private String make;
    private String model;
    private String color;
    private int year;
    private double price;
    private boolean sold;

    public Vehicle(String vin, String make, String model, String color, int year, double price, boolean sold) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.sold = sold;
    }

    // Getters and Setters
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isSold() { return sold; }
    public void setSold(boolean sold) { this.sold = sold; }
}

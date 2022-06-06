package com.revature.model;

public class Car {
    public enum Status{
        AVAILABLE, TAKEN;
    }
    public String make;
    public String model;
    public int year;
    public Status status;

    public Car(){}

    public Car(Status status){
        this.status = status;
    }

    public Car(String make, String model, int year, Status status){
        this.make = make;
        this.model = model;
        this.year = year;
        this.status = status;
    }
    @Override
    public String toString() {
        return "Year: " + year + "\n" +
                "Make: " + make + "\n" +
                "Model: " + model + "\n" +
                "Status: " + status + "\n\n";
    }
}

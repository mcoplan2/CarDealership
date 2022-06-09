package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {
    public enum Status{
        AVAILABLE, TAKEN;
    }
    private String make;
    private String model;
    private int year;
    private int id;
    public static int incrementId = 0;
    public Status status;

    public Car(){}

    public Car(Status status){
        this.status = status;
    }

    public Car(String make, String model, int year, Status status){
        this.make = make;
        this.model = model;
        this.year = year;
        this.id = incrementId++;
        this.status = status;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && id == car.id && Objects.equals(make, car.make) && Objects.equals(model, car.model) && status == car.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, id, status);
    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Year: " + year + "\n" +
                "Make: " + make + "\n" +
                "Model: " + model + "\n" +
                "Status: " + status + "\n\n";
    }
}

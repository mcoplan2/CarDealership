package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private String make;
    private String model;
    private int year;
    private int id;
    private int userId;
    private static int incrementId = 0;
    public CarStatus status;

    public Car(){
        this.id = incrementId++;
    }

    public Car(CarStatus status){
        this.status = status;
    }

    public Car(String make, String model, int year, CarStatus status){
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

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && id == car.id && userId == car.userId && Objects.equals(make, car.make) && Objects.equals(model, car.model) && status == car.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, id, userId, status);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}

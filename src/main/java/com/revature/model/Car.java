package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private String make;
    private String model;
    private int year;
    private int id;
    private int userId;
    private CarStatus status;

    public Car(){
    }

    public Car(String make, String model, int year, CarStatus status){
        this.make = make;
        this.model = model;
        this.year = year;
        this.status = status;
    }

    public String getMake() {
        return make;
    }

    public Car setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Car setYear(int year) {
        this.year = year;
        return this;
    }

    public int getId() {
        return id;
    }

    public Car setId(int id) {
        this.id = id;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Car setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public CarStatus getStatus() {
        return status;
    }

    public Car setStatus(CarStatus status) {
        this.status = status;
        return this;
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

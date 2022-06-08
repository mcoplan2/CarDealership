package com.revature.service;

import com.revature.model.Car;
import com.revature.model.User;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private static List<Car> cars = new ArrayList<>();

    // Only Employees can create cars, takes in a car object and a user ID
    // If the Role for the ID is an EMPLOYEE, then createCar is allowed.
    public void createCar(Car car, int id){
        List<User> users = UserService.getUsers();
        for(int i = 0; i < UserService.userCount(); i++) {
            if (users.get(i).id == id && users.get(i).role.equals(User.Role.EMPLOYEE)) {
                cars.add(car);
            }
        }
    }
    public static List<Car> getCars() {
        return cars;
    }

    public Car getCarById(int id){
        for(Car car: cars){
            if(car.id == id) {
                return car;
            }
        }
        return null;
    }

    public static int carCount(){
        return cars.size();
    }
}


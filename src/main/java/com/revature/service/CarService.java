package com.revature.service;

import com.revature.model.Car;
import com.revature.model.Offer;
import com.revature.model.User;
import com.revature.model.UserRoles;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private static List<Car> cars;

    public CarService(){
        cars = new ArrayList<>();
    }

    public CarService(List<Car> cars){
        this.cars = cars;

    }

    // Only Employees can create cars, takes in a car object and a user ID
    // If the Role for the ID is an EMPLOYEE, then createCar is allowed.
    public void createCar(Car car, int id){
        List<User> users = UserService.getUsers();
        for(int i = 0; i < UserService.userCount(); i++) {
            if (users.get(i).getId() == id && users.get(i).getRole().equals(UserRoles.EMPLOYEE)) {
                cars.add(car);
            }
        }
    }
    public static List<Car> getCars() {
        return cars;
    }

    public Car getCarById(int id){
        for(Car car: cars){
            if(car.getId() == id) {
                return car;
            }
        }
        return null;
    }


    public static int carCount(){
        return cars.size();
    }

    public boolean deleteCarById(int id){
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).getId() == id){
                cars.remove(i);
                return true;
            }
        }
        return false;
    }

    // Updates the car at the current ID
    // Pass in the ID you want to modify wih the new Car Object.
    public boolean updateCarById(int id, Car car){
        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getId() == id) {
                cars.set(id, car);
                return true;
            }
        }
        return false;
    }
}
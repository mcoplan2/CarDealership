package com.revature.service;

import com.revature.model.*;
import com.revature.repository.CarRepository;
import com.revature.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private CarRepository carRepository;

    public CarService() {
        carRepository = new CarRepository();
    }

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Only Employees can create cars, takes in a car object and a user ID
    // If the Role for the ID is an EMPLOYEE, then createCar is allowed.

    public Car createNewCar(Car car, int id) {
        UserService userService = new UserService();
        for (int i = 0; i < userService.userCount() ; i++) {
            if (userService.getUserById(id).getRole().equals(UserRoles.EMPLOYEE)
                    && userService.getUserById(id).getId() == id) {
                // grab the user ID and set it in the cars list.
                car.setUserId(id);
                return carRepository.create(car);
                //return true;
            }
        }
        return null;
    }


    /*public Car createNewCar(Car car) {
        return carRepository.create(car);
    }

     */
    public List<Car> getCars() {
        return carRepository.getAll();
    }

    public List<Car> getAllCarsByStatus(CarStatus status) {
        return carRepository.getAllByStatus(status);
    }
    public Car getCarById(int id){
        return carRepository.getById(id);
    }

    public int carCount(){
        return carRepository.count();
    }

    public boolean deleteCarById(int id){
        return carRepository.deleteById(id);
    }

    // Updates the car at the current ID
    // Pass in the ID you want to modify wih the new Car Object.
    public Car updateCarById(Car car) {
        return carRepository.update(car);
    }

    public Car getCarIdAndCheckStatus(int id, CarStatus status) {
        return carRepository.getCarIdByRole(id,status);
    }

    public List<Car> getAllCarsOwnedFromASpecificUserId(int id) {
        return carRepository.getAllCarsOwnedFromASpecificUserId(id);
    }
}
package com.revature.service;

import com.revature.model.*;
import com.revature.repository.CarRepository;
import com.revature.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private CarRepository carRepository;
    private UserService userService;

    private static CarService instance;

    public CarService() {
        carRepository = new CarRepository();
        instance = this;
        this.userService = UserService.getInstance();
    }

    // Add user service to the carservice constructor.
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
        instance = this;
        this.userService = UserService.getInstance();
    }

    public static CarService getInstance() {
        return instance;
    }

    // Only Employees can create cars, takes in a car object and a user ID
    // If the Role for the ID is an EMPLOYEE, then createCar is allowed.

    public Car createNewCar(Car car, int id) {
         User user = userService.getUserById(id);
        if(user.getRole().equals(UserRoles.EMPLOYEE)){
            return carRepository.create(car);
        }
        else
            return null;

            /*
        for (int i = 0; i < users.size() ; i++) {
            if (users.get(i).getUserById(id).getRole().equals(UserRoles.EMPLOYEE)
                    && userService.getUserById(id).getId() == id) {
                // grab the user ID and set it in the cars list.
                car.setUserId(id);
                return carRepository.create(car);
                //return true;
            }
        }
        return null;

             */
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

    public Car getCarFromIdAndCheckStatus(int id, CarStatus status) {
        return carRepository.getCarIdByRole(id,status);
    }

    public List<Car> getAllCarsOwnedFromASpecificUserId(int id) {
        return carRepository.getAllCarsOwnedFromASpecificUserId(id);
    }
}
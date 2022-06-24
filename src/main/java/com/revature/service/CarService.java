package com.revature.service;
import com.revature.model.*;
import com.revature.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarService {

    private CarRepository carRepository;
    private UserService userService;
    private static CarService instance;

    static Logger logger = LoggerFactory.getLogger(CarService.class);

    public CarService() {
        carRepository = new CarRepository();
        instance = this;
        this.userService = UserService.getInstance();
    }

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
    }

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

    public Car updateCarById(Car car) {
        return carRepository.update(car);
    }

    public Car getCarFromIdAndCheckStatus(int id, CarStatus status) {
        return carRepository.getCarIdByRole(id,status);
    }

    public List<Car> getAllCarsOwnedFromASpecificUserId(int id) {
        return carRepository.getAllByUserId(id);
    }
}
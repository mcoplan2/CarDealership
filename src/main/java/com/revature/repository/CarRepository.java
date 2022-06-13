package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.model.User;
import com.revature.model.UserRoles;

import java.util.ArrayList;
import java.util.List;

public class CarRepository  implements CrudDAO<Car> {
    private List<Car> cars;

    public CarRepository(){
        cars = new ArrayList<>();
    }

    public CarRepository(List<Car> cars){
        this.cars = cars;
    }

    // POST Method
    @Override
    public Car create(Car user) {
        if(cars.add(user)) {
            return user;
        } else {
            return null;
        }
    }

    // GET Method
    @Override
    public List<Car> getAll() {
        return cars;
    }

    // GET Method
    @Override
    public Car getById(int id) {
        for(int i = 0; i<cars.size(); i++) {
            if(cars.get(i).getId() == id){
                return cars.get(i);
            }
        }
        return null;
    }

    // PUT Method
    @Override
    public Car update(Car car) {
        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getId() == car.getId()) {
                return cars.set(i, car);
            }
        }
        return null;
    }

    // DELETE Method
    @Override
    public boolean deleteById(int id) {
        for (int i = 0; i < cars.size(); i++){
            if (cars.get(i).getId() == id) {
                cars.remove(i);
                return true;
            }
        }
        return false;
    }

    // Method to return size of List
    @Override
    public int count() {
        return cars.size();
    }

    public Car getCarIdByRole(int id, CarStatus status) {
        for(int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getId() == id && cars.get(i).getStatus().equals(status)){
                return cars.get(i);
            }
        }
        return null;
    }

    // Method to get all users by a specific Role
    public List<Car> getAllByStatus(CarStatus status) {
        List<Car> filteredCars = new ArrayList<>();

        for(int i = 0; i<cars.size(); i++) {
            if (cars.get(i).getStatus().equals(status)) {
                filteredCars.add(cars.get(i));
            }
        }
        return filteredCars;
    }

    public List<Car> getAllCarsOwnedFromASpecificUserId(int id) {
        List<Car> filteredCars = new ArrayList<>();

        for(int i = 0; i<cars.size(); i++) {
            if (cars.get(i).getUserId() == id && cars.get(i).getStatus().equals(CarStatus.PURCHASED) ) {
                filteredCars.add(cars.get(i));
            }
        }
        return filteredCars;
    }
}

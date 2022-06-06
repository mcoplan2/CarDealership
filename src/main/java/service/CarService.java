package service;

import model.Car;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    UserService userService = new UserService();
    private List<Car> cars = new ArrayList<>();

    // Only Employees can create cars, takes in a car object and a user ID
    // If the Role for the ID is an EMPLOYEE, then createCar is allowed.
    public void createCar(Car car, int id){
        User user = userService.getUserById(id);
        if (user.role.equals(User.Role.EMPLOYEE))
            cars.add(car);
    }
    public List<Car> getCars() {
        return cars;
    }

    public int carCount(){
        return cars.size();
    }
}


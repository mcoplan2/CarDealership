package com.revature.services;

import com.revature.model.Car;
import com.revature.model.User;
import com.revature.service.CarService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CarServiceTest {

    @Test
    public void carServiceCreateCarAsEmployeeWorksCarCountEquals2(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test1","Test","test","test",User.Role.EMPLOYEE));
        userService.createNewUser(new User("Test2","Test","test","test",User.Role.CUSTOMER));
        userService.createNewUser(new User("Test3","Test","test","test",User.Role.EMPLOYEE));
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.TAKEN),2);
        Assertions.assertEquals(2, carService.carCount());
    }

    @Test
    public void carServiceCreateCarAsCustomerDoesNotWorkCarCountEquals0(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test","Test","test","test",User.Role.CUSTOMER));
        userService.createNewUser(new User("Test2","Test","test","test",User.Role.CUSTOMER));
        userService.createNewUser(new User("Test3","Test","test","test",User.Role.CUSTOMER));
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),1);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),2);
        Assertions.assertEquals(0, carService.carCount());
    }
    @Test
    public void carServiceCheckIfGetCarByIDWorks(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test","Test","test","test",User.Role.CUSTOMER));
        userService.createNewUser(new User("Test2","Test","test","test",User.Role.EMPLOYEE));
        userService.createNewUser(new User("Test3","Test","test","test",User.Role.CUSTOMER));
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),1);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),2);
        Assertions.assertEquals("1", carService.getCarById(1).toString());
    }
}
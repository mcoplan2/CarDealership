package com.revature.services;

import com.revature.model.*;
import com.revature.service.CarService;
import com.revature.service.OfferService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;


public class CarServiceTest {

    List<Car> mockedList = Mockito.mock(List.class);

    @Test
    public void whenGivenOfferObjectCreateNewOfferDoesNotThrowAnException() {
        User user = new User("Test", "Test", "test", "test", UserRoles.CUSTOMER);
        List<User> mockedList2 = Mockito.mock(List.class);
        UserService userService = new UserService(mockedList2);
        user.setId(1);

        Mockito.when(mockedList2.size()).thenReturn(1);
        Mockito.when(mockedList2.get(0)).thenReturn(user);

        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        CarService carService = new CarService(mockedList);
        car.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);

        Assertions.assertDoesNotThrow(() -> carService.createCar(car, 1));
    }

    @Test
    public void whenGivenUserObjectCreateNewUserReturnsTrueAsEmployee(){
        User user = new User("Test", "Test", "test", "test", UserRoles.EMPLOYEE);
        List<User> mockedList2 = Mockito.mock(List.class);
        UserService userService = new UserService(mockedList2);
        user.setId(1);

        Mockito.when(mockedList2.size()).thenReturn(1);
        Mockito.when(mockedList2.get(0)).thenReturn(user);

        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        CarService carService = new CarService(mockedList);
        car.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);


        Mockito.when(mockedList.add(car)).thenReturn(true);
        boolean result = carService.createCar(car,1);
        Assertions.assertTrue(result);
    }

    @Test
    public void whenGivenUserObjectCreateNewUserReturnsFalseAsCustomer(){
        User user = new User("Test", "Test", "test", "test", UserRoles.CUSTOMER);
        List<User> mockedList2 = Mockito.mock(List.class);
        UserService userService = new UserService(mockedList2);
        user.setId(1);

        Mockito.when(mockedList2.size()).thenReturn(1);
        Mockito.when(mockedList2.get(0)).thenReturn(user);

        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        CarService carService = new CarService(mockedList);
        car.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);


        Mockito.when(mockedList.add(car)).thenReturn(true);
        boolean result = carService.createCar(car,1);
        Assertions.assertFalse(result);
    }

    @Test
    public void whenGetCarsIsCalledDoesNotThrowAnException() {
        CarService carService = new CarService();
        Assertions.assertDoesNotThrow(CarService::getCars);
    }

    @Test
    public void whenGivenValidIdGetCarsByIdReturnsCorrectCar() {
        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        car.setId(1);
        CarService carService = new CarService(mockedList);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);

        Car result = carService.getCarById(1);
        Assertions.assertEquals(result, car);
    }

    @Test
    public void whenCarCountIsCalledItReturnsTheCorrectNumberOfCars(){
        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        Mockito.when(mockedList.size()).thenReturn(1);

        Car car2 = new Car("Test2", "test2", 600, CarStatus.AVAILABLE);
        Mockito.when(mockedList.size()).thenReturn(2);

        CarService carService = new CarService(mockedList);
        int result = CarService.carCount();
        Assertions.assertEquals(2, result);
    }


    @Test
    public void whenGivenUserIdDeleteUserByIdReturnsTrue(){
        CarService carService = new CarService(mockedList);
        Car car = new Car();
        car.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);
        Mockito.when(mockedList.remove(0)).thenReturn(car);

        Assertions.assertTrue(carService.deleteCarById(1));
    }

    @Test
    public void whenGivenUserIdUpdateUserByIdReturnsTrue() {
        CarService carService = new CarService(mockedList);;
        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        Mockito.when(mockedList.size()).thenReturn(1);

        Car car2 = new Car("Test2", "test2", 600, CarStatus.AVAILABLE);
        Mockito.when(mockedList.size()).thenReturn(2);

        Mockito.when(mockedList.get(0)).thenReturn(car);
        Mockito.when(mockedList.set(0, car2)).thenReturn(car2);

        Assertions.assertTrue(carService.updateCarById(0, car2));
    }

    @Test
    public void carServiceCreateCarAsEmployeeWorksCarCountEquals2(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test1","Test","test","test", UserRoles.EMPLOYEE));
        userService.createNewUser(new User("Test2","Test","test","test", UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test3","Test","test","test", UserRoles.EMPLOYEE));
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.TAKEN),2);
        Assertions.assertEquals(2, carService.carCount());
    }

    @Test
    public void carServiceCreateCarAsCustomerDoesNotWorkCarCountEquals0(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test","Test","test","test",UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test2","Test","test","test",UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test3","Test","test","test",UserRoles.CUSTOMER));
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),1);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),2);
        Assertions.assertEquals(0, carService.carCount());
    }
    @Test
    public void carServiceCheckIfGetCarByIDWorks(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test","Test","test","test",UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test2","Test","test","test",UserRoles.EMPLOYEE));
        userService.createNewUser(new User("Test3","Test","test","test",UserRoles.CUSTOMER));
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),1);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),2);
        Assertions.assertEquals(1, carService.getCarById(1).getId());
    }
}
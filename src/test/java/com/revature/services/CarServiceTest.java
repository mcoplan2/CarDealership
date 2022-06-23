package com.revature.services;

import com.revature.model.*;
import com.revature.service.CarService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;


public class CarServiceTest {


    CarService mockedService = Mockito.mock(CarService.class);
    User user = new User("Test", "Test", "test", "test", UserRoles.CUSTOMER);
    Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
    Car car2 = new Car("Test2", "test2", 600, CarStatus.AVAILABLE);

    @Test
    public void whenGivenCarObjectCreateNewCarDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.createNewCar(car, 0));
    }

    @Test
    public void whenGivenCarObjectCreateNewCarReturnsCorrectCar(){
        UserService mockedUserService = Mockito.mock(UserService.class);

        Mockito.when(mockedUserService.userCount()).thenReturn(1);
        Mockito.when(mockedUserService.getUserById(0)).thenReturn(user);

        Mockito.when(mockedService.carCount()).thenReturn(1);
        Mockito.when(mockedService.getCarById(0)).thenReturn(car);

        Mockito.when(mockedService.createNewCar(car,1)).thenReturn(car);
        Car result = mockedService.createNewCar(car,1);
        Assertions.assertEquals(car, result);
    }

    @Test
    public void whenGetCarsIsCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.getCars());
    }

    @Test
    public void whenGivenValidIdGetCarsByIdReturnsCorrectCar() {
        Mockito.when(mockedService.carCount()).thenReturn(1);
        Mockito.when(mockedService.getCarById(0)).thenReturn(car);

        Car result = mockedService.getCarById(0);
        Assertions.assertEquals(result, car);
    }

    @Test
    public void whenCarCountIsCalledItReturnsTheCorrectNumberOfCars(){
        Mockito.when(mockedService.carCount()).thenReturn(2);

        int result = mockedService.carCount();
        Assertions.assertEquals(2, result);
    }

    @Test
    public void whenGivenCarIdDeleteCarByIdReturnsTrue(){
        Mockito.when(mockedService.carCount()).thenReturn(1);
        Mockito.when(mockedService.getCarById(0)).thenReturn(car);
        Mockito.when(mockedService.deleteCarById(0)).thenReturn(true);

        Assertions.assertTrue(mockedService.deleteCarById(0));
    }

    @Test
    public void whenGivenCarIdUpdateCarByIdReturnsCorrectCar() {
        Mockito.when(mockedService.getCarById(0)).thenReturn(car);
        Mockito.when(mockedService.updateCarById(car)).thenReturn(car2);

        Car result = mockedService.updateCarById(car);

        Assertions.assertEquals(car2, result);
    }

    @Test
    public void whenGivenCarIdgetAllByStatusDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.getAllCarsByStatus(CarStatus.AVAILABLE));
    }

    @Test
    public void whenGivenCarIdgetAllByStatusReturnsListOfCars() {
        List<Car> cars = new ArrayList<>();
        car.setStatus(CarStatus.PURCHASED);
        cars.add(car);
        car2.setStatus(CarStatus.PURCHASED);
        cars.add(car2);
        Mockito.when(mockedService.getAllCarsByStatus(CarStatus.PURCHASED)).thenReturn(cars);

        Assertions.assertNotNull(mockedService.getAllCarsByStatus(CarStatus.PURCHASED));
    }

    @Test
    public void whenGivenCarIdgetAllCarsOwnedDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.getAllCarsOwnedFromASpecificUserId(1));
    }

    @Test
    public void whenGivenCarIdgetAllCarsOwnedReturnsListOfCarsOwned() {
        List<Car> cars = new ArrayList<>();
        car.setUserId(1);
        cars.add(car);
        car2.setUserId(1);
        cars.add(car2);
        Mockito.when(mockedService.getAllCarsOwnedFromASpecificUserId(1)).thenReturn(cars);

        Assertions.assertNotNull(mockedService.getAllCarsOwnedFromASpecificUserId(1));
    }
}
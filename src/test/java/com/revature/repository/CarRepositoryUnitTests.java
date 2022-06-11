package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.service.CarService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class CarRepositoryUnitTests {

    List<Car> mockedList = Mockito.mock(List.class);
    Car car = new Car();
    CarRepository carRepository = new CarRepository(mockedList);

    @Test
    public void whenGivenCarObjectCreateNewCarDoesNotThrowAnException() {
        User user = new User("Test", "Test", "test", "test", UserRoles.CUSTOMER);
        List<User> mockedList2 = Mockito.mock(List.class);
        UserRepository userRepository = new UserRepository(mockedList2);
        user.setId(1);

        Mockito.when(mockedList2.size()).thenReturn(1);
        Mockito.when(mockedList2.get(0)).thenReturn(user);

        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        car.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);

        Assertions.assertDoesNotThrow(() -> carRepository.create(car));
    }

    @Test
    public void whenGivenCarObjectCreateNewCarReturnsCorrectCar(){
        User user = new User("Test", "Test", "test", "test", UserRoles.EMPLOYEE);
        List<User> mockedList2 = Mockito.mock(List.class);
        UserRepository userRepository = new UserRepository(mockedList2);
        user.setId(1);

        Mockito.when(mockedList2.size()).thenReturn(1);
        Mockito.when(mockedList2.get(0)).thenReturn(user);

        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        car.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);

        Mockito.when(mockedList.add(car)).thenReturn(true);
        Car result = carRepository.create(car);
        Assertions.assertEquals(car, result);
    }

    @Test
    public void whenGetCarsIsCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> carRepository.getAll());
    }

    @Test
    public void whenGivenValidIdGetCarsByIdReturnsCorrectCar() {
        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        car.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);

        Car result = carRepository.getById(1);
        Assertions.assertEquals(result, car);
    }

    @Test
    public void whenCarCountIsCalledItReturnsTheCorrectNumberOfCars(){
        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        Mockito.when(mockedList.size()).thenReturn(1);

        Car car2 = new Car("Test2", "test2", 600, CarStatus.AVAILABLE);
        Mockito.when(mockedList.size()).thenReturn(2);

        int result = carRepository.count();
        Assertions.assertEquals(2, result);
    }


    @Test
    public void whenGivenCarIdDeleteCarByIdReturnsTrue(){
        Car car = new Car();
        car.setId(0);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(car);
        Mockito.when(mockedList.remove(0)).thenReturn(car);

        Assertions.assertTrue(carRepository.deleteById(0));
    }

    @Test
    public void whenGivenCarIdUpdateCarByIdReturnsCorrectCar() {
        Car car = new Car("Test", "test", 500, CarStatus.AVAILABLE);
        Mockito.when(mockedList.size()).thenReturn(1);

        Car car2 = new Car("Test2", "test2", 600, CarStatus.AVAILABLE);
        Mockito.when(mockedList.size()).thenReturn(2);

        Mockito.when(mockedList.get(0)).thenReturn(car);
        Mockito.when(mockedList.set(0, car)).thenReturn(car2);

        Car result = carRepository.update(car);

        Assertions.assertEquals(car2, result);
    }
}

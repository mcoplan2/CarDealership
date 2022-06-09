package com.revature.services;

import com.revature.model.*;
import com.revature.service.CarService;
import com.revature.service.OfferService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

public class OfferServiceTest {
    List<Offer> mockedList = Mockito.mock(List.class);

    @Test
    public void whenGivenOfferObjectCreateNewOfferDoesNotThrowAnException() {
        Car car = new Car("Test", "Test", 2322, CarStatus.AVAILABLE);
        List<Car> mockedList2 = Mockito.mock(List.class);
        CarService carService = new CarService(mockedList2);
        car.setId(1);

        Mockito.when(mockedList2.size()).thenReturn(1);
        Mockito.when(mockedList2.get(0)).thenReturn(car);

        Offer offer = new Offer(500, OfferStatus.OPEN);
        OfferService offerService = new OfferService(mockedList);
        offer.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(offer);


        Assertions.assertDoesNotThrow(() -> offerService.createOffer(offer,1));
    }

    @Test
    public void whenGivenUserObjectCreateNewUserReturnsTrue(){
        Car car = new Car("Test", "Test", 2322, CarStatus.AVAILABLE);
        List<Car> mockedList2 = Mockito.mock(List.class);
        CarService carService = new CarService(mockedList2);
        car.setId(1);

        Mockito.when(mockedList2.size()).thenReturn(1);
        Mockito.when(mockedList2.get(0)).thenReturn(car);

        Offer offer = new Offer(500, OfferStatus.OPEN);
        OfferService offerService = new OfferService(mockedList);
        offer.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(offer);


        Mockito.when(mockedList.add(offer)).thenReturn(true);
        boolean result = offerService.createOffer(offer,1);
        Assertions.assertTrue(result);
    }

    @Test
    public void whenGetOffersIsCalledDoesNotThrowAnException() {
        OfferService offerService = new OfferService();
        Assertions.assertDoesNotThrow(offerService::getOffers);
    }

    @Test
    public void whenGivenValidIdGetOfferByIdReturnsCorrectOffer() {
        Offer offer = new Offer(500, OfferStatus.OPEN);
        offer.setId(1);
        OfferService offerService = new OfferService(mockedList);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(offer);

        Offer result = offerService.getOfferById(1);
        Assertions.assertEquals(result, offer);

    }
    @Test
    public void whenOfferCountIsCalledItReturnsTheCorrectNumberOfOffers(){
        Offer offer = new Offer(500, OfferStatus.OPEN);
        Mockito.when(mockedList.size()).thenReturn(1);

        Offer offer2 = new Offer(500, OfferStatus.OPEN);
        Mockito.when(mockedList.size()).thenReturn(2);

        OfferService offerService = new OfferService(mockedList);
        int result = OfferService.offerCount();
        Assertions.assertEquals(2, result);
    }


    @Test
    public void whenGivenUserIdDeleteUserByIdReturnsTrue(){
        OfferService service = new OfferService(mockedList);
        Offer offer = new Offer();
        offer.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(offer);
        Mockito.when(mockedList.remove(0)).thenReturn(offer);

        Assertions.assertTrue(service.deleteOfferById(1));
    }

    @Test
    public void whenGivenUserIdUpdateUserByIdReturnsTrue() {
        OfferService offerService = new OfferService(mockedList);
        Offer offer = new Offer(500, OfferStatus.OPEN);
        Mockito.when(mockedList.size()).thenReturn(1);

        Offer offer2 = new Offer(500, OfferStatus.REJECTED);
        Mockito.when(mockedList.size()).thenReturn(2);

        Mockito.when(mockedList.size()).thenReturn(2);
        Mockito.when(mockedList.get(0)).thenReturn(offer);
        Mockito.when(mockedList.set(0, offer2)).thenReturn(offer2);

        Assertions.assertTrue(offerService.updateOfferById(0, offer2));
    }
    @Test
    public void offerServiceCreateOfferWorks(){
        OfferService offerService = new OfferService();
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test1","Test","test","test", UserRoles.EMPLOYEE));
        userService.createNewUser(new User("Test2","Test","test","test", UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test3","Test","test","test", UserRoles.EMPLOYEE));
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.TAKEN),2);
        offerService.createOffer(new Offer(500, OfferStatus.OPEN),0);
        offerService.createOffer(new Offer(500, OfferStatus.OPEN),1);
        Assertions.assertEquals(1, OfferService.offerCount());
    }
}

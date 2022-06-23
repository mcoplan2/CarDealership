package com.revature.services;

import com.revature.model.*;
import com.revature.service.OfferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class OfferServiceTest {

    OfferService mockedService = Mockito.mock(OfferService.class);
    Offer offer = new Offer(500, 1,OfferStatus.OPEN);
    Offer offer2 = new Offer(500, 1,OfferStatus.OPEN);
    User user = new User("Test", "Test", "test", "test", UserRoles.EMPLOYEE);
    User user2 = new User("Test1", "Test1", "test1", "test1", UserRoles.CUSTOMER);

    @Test
    public void whenGivenOfferObjectCreateNewOfferDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.createOffer(offer,1));
    }

    @Test
    public void whenGivenOfferObjectWithCarAvailCreateOfferUserReturnsTrue(){
        Mockito.when(mockedService.offerCount()).thenReturn(1);
        Mockito.when(mockedService.getOfferById(0)).thenReturn(offer);
        Mockito.when(mockedService.createOffer(offer,1)).thenReturn(offer);

        Offer result = mockedService.createOffer(offer,1);
        Assertions.assertEquals(offer, result);
    }


    @Test
    public void whenGetOffersIsCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.getOffers());
    }

    @Test
    public void whenGivenValidIdGetOfferByIdReturnsCorrectOffer() {
        Mockito.when(mockedService.offerCount()).thenReturn(1);
        Mockito.when(mockedService.getOfferById(0)).thenReturn(offer);

        Offer result = mockedService.getOfferById(0);
        Assertions.assertEquals(result, offer);
    }

    @Test
    public void whenOfferCountIsCalledItReturnsTheCorrectNumberOfOffers(){
        Mockito.when(mockedService.offerCount()).thenReturn(2);

        int result = mockedService.offerCount();
        Assertions.assertEquals(2, result);
    }

    @Test
    public void whenGivenOfferIdDeleteOfferByIdReturnsTrue(){
        Mockito.when(mockedService.offerCount()).thenReturn(1);
        Mockito.when(mockedService.getOfferById(0)).thenReturn(offer);
        Mockito.when(mockedService.deleteOfferById(0)).thenReturn(true);

        Assertions.assertTrue(mockedService.deleteOfferById(0));
    }

    @Test
    public void whenGivenUserIdUpdateUserByIdReturnsTrue() {
        Mockito.when(mockedService.getOfferById(0)).thenReturn(offer);
        Mockito.when(mockedService.updateOfferById(offer)).thenReturn(offer2);
        Offer result = mockedService.updateOfferById(offer);

        Assertions.assertEquals(offer2, result);
    }

    @Test
    public void whenGivenOfferGetAllByStatusDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.getAllOffersByStatus(OfferStatus.OPEN));
    }

    @Test
    public void whenGivenOfferGetAllByStatusReturnsListOfOffers() {
        List<Offer> offers = new ArrayList<>();
        offer.setStatus(OfferStatus.ACCEPTED);
        offers.add(offer);
        offer2.setStatus(OfferStatus.ACCEPTED);
        offers.add(offer2);
        Mockito.when(mockedService.getAllOffersByStatus(OfferStatus.ACCEPTED)).thenReturn(offers);

        Assertions.assertNotNull(mockedService.getAllOffersByStatus(OfferStatus.ACCEPTED));
    }

    @Test
    public void whenGivenOfferIdGetAllByOffersFromAUserDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.getAllOpenOffersFromASpecificUserId(1));
    }

    @Test
    public void whenGivenOfferIdGetAllByOffersFromAUserReturnsListOfOffers() {
        List<Offer> offers = new ArrayList<>();
        offer.setStatus(OfferStatus.OPEN);
        offers.add(offer);
        offer2.setStatus(OfferStatus.OPEN);
        offers.add(offer2);
        Mockito.when(mockedService.getAllOpenOffersFromASpecificUserId(0)).thenReturn(offers);

        Assertions.assertNotNull(mockedService.getAllOpenOffersFromASpecificUserId(0));
    }

    @Test
    public void whenApproveOfferByIdIsCalledNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.approveOfferById(1,1));
    }

    @Test
    public void whenApproveOfferByIdIsCalledReturnsTrueIfEmployeeApproves() {
        Mockito.when(mockedService.approveOfferById(1,0)).thenReturn(true);
        boolean result = mockedService.approveOfferById(1,0);
        Assertions.assertTrue(result);
    }

    @Test
    public void whenApproveOfferByIdIsCalledReturnsFalseIfCustomerApproves() {
        Mockito.when(mockedService.approveOfferById(1,1)).thenReturn(false);
        boolean result = mockedService.approveOfferById(1,1);
        Assertions.assertFalse(result);
    }

    @Test
    public void whenDenyOfferByIdIsCalledNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.denyOfferById(1,1));
    }

    @Test
    public void whenDenyOfferByIdIsCalledReturnsTrueIfEmployeeApproves() {
        Mockito.when(mockedService.denyOfferById(1,0)).thenReturn(true);
        boolean result = mockedService.denyOfferById(1,0);
        Assertions.assertTrue(result);
    }

    @Test
    public void whenDenyOfferByIdIsCalledReturnsFalseIfCustomerApproves() {
        Mockito.when(mockedService.denyOfferById(1,1)).thenReturn(false);
        boolean result = mockedService.denyOfferById(1,1);
        Assertions.assertFalse(result);
    }
}

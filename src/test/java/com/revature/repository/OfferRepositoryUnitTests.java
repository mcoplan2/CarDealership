package com.revature.repository;

import com.revature.model.*;
import com.revature.service.CarService;
import com.revature.service.OfferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class OfferRepositoryUnitTests {
    List<Offer> mockedList = Mockito.mock(List.class);
    Offer offer = new Offer();
    OfferRepository offerRepository = new OfferRepository(mockedList);

    @Test
    public void whenGivenOfferObjectCreateNewOfferDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> offerRepository.create(offer));
    }

    @Test
    public void whenGivenOfferObjectWithCarAvailCreateOfferUserReturnsTrue(){
        Offer offer = new Offer(500, 1,OfferStatus.OPEN);
        offer.setId(0);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(offer);
        Mockito.when(mockedList.add(offer)).thenReturn(true);

        Offer result = offerRepository.create(offer);
        Assertions.assertEquals(offer, result);
    }

    @Test
    public void whenGetOffersIsCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> offerRepository.getAll());
    }

    @Test
    public void whenGivenValidIdGetOfferByIdReturnsCorrectOffer() {
        Offer offer = new Offer(500, 1 ,OfferStatus.OPEN);
        offer.setId(0);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(offer);

        Offer result = offerRepository.getById(0);
        Assertions.assertEquals(result, offer);
    }

    @Test
    public void whenOfferCountIsCalledItReturnsTheCorrectNumberOfOffers(){
        Offer offer = new Offer(500, 1,OfferStatus.OPEN);
        Mockito.when(mockedList.size()).thenReturn(1);

        Offer offer2 = new Offer(500, 1,OfferStatus.OPEN);
        Mockito.when(mockedList.size()).thenReturn(2);


        int result = offerRepository.count();
        Assertions.assertEquals(2, result);
    }

    @Test
    public void whenGivenOfferIdDeleteOfferByIdReturnsTrue(){
        Offer offer = new Offer();
        offer.setId(0);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(offer);
        Mockito.when(mockedList.remove(0)).thenReturn(offer);

        Assertions.assertTrue(offerRepository.deleteById(0));
    }

    @Test
    public void whenGivenUserIdUpdateUserByIdReturnsTrue() {
        Offer offer = new Offer(500, 1,OfferStatus.OPEN);
        Mockito.when(mockedList.size()).thenReturn(1);

        Offer offer2 = new Offer(500, 1,OfferStatus.REJECTED);
        Mockito.when(mockedList.size()).thenReturn(2);

        Mockito.when(mockedList.size()).thenReturn(2);
        Mockito.when(mockedList.get(0)).thenReturn(offer);
        Mockito.when(mockedList.set(0, offer)).thenReturn(offer2);

        Offer result = offerRepository.update(offer);

        Assertions.assertEquals(offer2, result);
    }
}

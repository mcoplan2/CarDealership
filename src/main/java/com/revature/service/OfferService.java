package com.revature.service;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.model.Offer;
import com.revature.model.User;

import java.util.ArrayList;
import java.util.List;

public class OfferService {

    private static final List<Offer> offers = new ArrayList<>();

    public List<Offer> getOffers() {
        return offers;
    }

    // IF Car is AVAIABLE then user can make an offer. Also, GET USERID of user making offer.
    public void createOffer(Offer offer, int carId) {
        List<Car> cars = CarService.getCars();
        for (int i = 0; i < CarService.carCount(); i++) {
            if (cars.get(i).status.equals(CarStatus.AVAILABLE) && cars.get(i).getId() == carId)
                offers.add(offer);
        }
    }

    public static int offerCount() {
        return offers.size();
    }

    public boolean deleteOfferById(int id){
        for(int i = 0; i < offers.size(); i++){
            if(offers.get(i).getId() == id){
                offers.remove(i);
                return true;
            }
        }
        return false;
    }

    // Updates the offer at the current ID
    // Pass in the ID you want to modify wih the new Offer Object.
    public boolean updateOfferById(int id, Offer offer){
        for (int i = 0; i < offers.size(); i++) {
            if(offers.get(i).getId() == id) {
                offers.set(id, offer);
                return true;
            }
        }
        return false;
    }
}

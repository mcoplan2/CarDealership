package com.revature.service;

import com.revature.model.*;
import com.revature.repository.OfferRepository;

import java.util.ArrayList;
import java.util.List;

public class OfferService {

    private OfferRepository offerRepository;

    public OfferService() {
        offerRepository = new OfferRepository();
    }

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    public List<Offer> getOffers() {
        return offerRepository.getAll();
    }

    // IF Car is AVAIABLE then user can make an offer. Also, GET USERID of user making offer.
        public boolean createOffer(Offer offer, int carId) {
        CarService carService = new CarService();
        for (int i = 0; i < carService.carCount(); i++) {
            if (carService.getCarById(carId).status.equals(CarStatus.AVAILABLE)
                    && carService.getCarById(carId).getId() == carId) {
                offerRepository.create(offer);
                offer.setCarId(carId);
                return true;
            }
        }
        return false;
    }

    public List<Offer> getAllOffersByStatus(OfferStatus status) {
        return offerRepository.getAllByStatus(status);
    }

    public Offer getOfferById(int id){
        return offerRepository.getById(id);
    }

    public int offerCount() {
        return offerRepository.count();
    }

    public boolean deleteOfferById(int id){
        return offerRepository.deleteById(id);
    }

    // Updates the offer at the current ID
    // Pass in the ID you want to modify wih the new Offer Object.
    public Offer updateOfferById(Offer offer){
        return offerRepository.update(offer);
    }
}

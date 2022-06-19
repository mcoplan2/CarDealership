package com.revature.service;

import com.revature.model.*;
import com.revature.repository.OfferRepository;
import java.util.List;

public class OfferService {

    private OfferRepository offerRepository;
    private CarService carService;
    private UserService userService;

    public OfferService() {
        offerRepository = new OfferRepository();
        this.carService = CarService.getInstance();
        this.userService = UserService.getInstance();
    }

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
        this.carService = CarService.getInstance();
        this.userService = UserService.getInstance();
    }


    public List<Offer> getOffers() {
        return offerRepository.getAll();
    }

    // IF Car is AVAIABLE then user can make an offer. Also, GET USERID of user making offer.
    // TODO ADD CAR ID TO OFFER SIGNATURE JSON BODY

    // TODO
    //  CHECK IF BELOW IS WORKING, IT IS ISNT WORKING DO ABOVE
    //  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public Offer createOffer(Offer offer, int carId) {
        if (carService.getCarById(carId).getStatus().equals(CarStatus.AVAILABLE)) {
            Offer offer2 = offerRepository.create(offer);
            offer2.setCarId(carId);
            offerRepository.update(offer2);
            return offer2;
        } else
            return null;
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

    public List<Offer> getAllOffersFromASpecificUserId(int id) {
        return offerRepository.getAllOffersFromASpecificUserId(id);
    }

    //TODO: Remove Enums from json and handle in service class, only leave Customer and Employee
    // Maybe add Offers/ID/Approve to Javalin path and run this command through
    // (Grab USER ID from body and check if it is an employee?)
    // LOOP TO REJECT ALL OFFERS THAT BELONG TO THIS CAR ID
    public boolean approveOfferById(int offerId, int userId){
        Offer pendingOffer = offerRepository.getById(offerId);
        int carId = pendingOffer.getCarId();
        Car pendingCar = carService.getCarById(carId);
        if(userService.getUserById(userId).getRole().equals(UserRoles.EMPLOYEE))
            if (pendingOffer.getStatus().equals(OfferStatus.OPEN) && pendingCar.getStatus().equals(CarStatus.AVAILABLE)) {
                    pendingOffer.setStatus(OfferStatus.ACCEPTED);
                    offerRepository.update(pendingOffer);
                    pendingCar.setStatus(CarStatus.PURCHASED).setUserId(userId);
                    try {
                        carService.updateCarById(pendingCar);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    for(int i = 0; i < offerCount(); i++) {
                        if(offerRepository.getById(i).getCarId() == pendingCar.getId() && offerRepository.getById(i) != offerRepository.getById(offerId)) {
                            offerRepository.getById(i).setStatus(OfferStatus.REJECTED);
                            offerRepository.update(pendingOffer);
                        }
                    }
                    return true;
        }
        return false;
    }
    public void denyOfferById(int id){
        Offer pendingOffer = offerRepository.getById(id);
        int carId = pendingOffer.getCarId();
        Car pendingCar = carService.getCarById(carId);
        pendingCar.setUserId(pendingOffer.getUserId());
        pendingOffer.setStatus(OfferStatus.REJECTED);
        pendingCar.setStatus(CarStatus.AVAILABLE);
    }

}

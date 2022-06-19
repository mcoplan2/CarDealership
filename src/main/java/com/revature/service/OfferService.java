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

    public Offer createOffer(Offer offer, int carId) {
        if (carService.getCarById(carId).getStatus().equals(CarStatus.AVAILABLE)) {
            offer.setCarId(carId);
            return offerRepository.create(offer);
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

    public boolean approveOfferById(int offerId, int userId){
        Offer pendingOffer = offerRepository.getById(offerId);
        int carId = pendingOffer.getCarId();
        Car pendingCar = carService.getCarById(carId);
        if(userService.getUserById(userId).getRole().equals(UserRoles.EMPLOYEE))
            if (pendingOffer.getStatus().equals(OfferStatus.OPEN) && pendingCar.getStatus().equals(CarStatus.AVAILABLE)) {
                pendingOffer.setStatus(OfferStatus.ACCEPTED);
                offerRepository.update(pendingOffer);
                pendingCar.setStatus(CarStatus.PURCHASED);
                pendingCar.setUserId(pendingOffer.getUserId());
                carService.updateCarById(pendingCar);
                int count = offerRepository.count();
                for(int i = 0; i < offerRepository.count(); i++) {
                    // TODO FIX THIS LOGIC
                    if(offerRepository.getById(i).getCarId() == pendingCar.getId() && offerRepository.getById(i) != offerRepository.getById(offerId)) {
                        offerRepository.getById(i).setStatus(OfferStatus.REJECTED);
                        offerRepository.update(pendingOffer);
                    }
                }
                return true;
        }
        return false;
    }
    public boolean denyOfferById(int id, int userId){
        Offer pendingOffer = offerRepository.getById(id);
        int carId = pendingOffer.getCarId();
        Car pendingCar = carService.getCarById(carId);
        if(userService.getUserById(userId).getRole().equals(UserRoles.EMPLOYEE)) {
            if (pendingOffer.getStatus().equals(OfferStatus.OPEN) && pendingCar.getStatus().equals(CarStatus.AVAILABLE)) {
                pendingOffer.setStatus(OfferStatus.REJECTED);
                offerRepository.update(pendingOffer);
                pendingCar.setStatus(CarStatus.AVAILABLE);
                carService.updateCarById(pendingCar);
                return true;
            }
        }
        return false;
    }

}

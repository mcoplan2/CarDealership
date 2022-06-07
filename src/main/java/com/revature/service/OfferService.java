package com.revature.service;

import com.revature.model.Car;
import com.revature.model.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferService {

    private final List<Offer> offers = new ArrayList<>();

    public List<Offer> getOffers() {
        return offers;
    }

    public void createOffer(Offer offer) {
        offers.add(offer);
    }
}

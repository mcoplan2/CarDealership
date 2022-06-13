package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.model.Offer;
import com.revature.model.OfferStatus;

import java.util.ArrayList;
import java.util.List;

public class OfferRepository implements CrudDAO<Offer> {

    private List<Offer> offers;

    public OfferRepository() {
        offers = new ArrayList<>();
    }

    public OfferRepository(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public Offer create(Offer offer) {
        if(offers.add(offer)) {
            return offer;
        } else {
            return null;
        }
    }

    @Override
    public List<Offer> getAll() {
        return offers;
    }

    @Override
    public Offer getById(int id) {
        for(int i = 0; i<offers.size(); i++) {
            if(offers.get(i).getId() == id){
                return offers.get(i);
            }
        }
        return null;
    }

    @Override
    public Offer update(Offer offer) {
        for (int i = 0; i < offers.size(); i++) {
            if(offers.get(i).getId() == offer.getId()) {
                return offers.set(i, offer);
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        for (int i = 0; i < offers.size(); i++){
            if (offers.get(i).getId() == id) {
                offers.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return offers.size();
    }

    public List<Offer> getAllByStatus(OfferStatus status) {
        List<Offer> filteredOffers = new ArrayList<>();

        for(int i = 0; i<offers.size(); i++) {
            if (offers.get(i).getStatus().equals(status)) {
                filteredOffers.add(offers.get(i));
            }
        }
        return filteredOffers;
    }

    public List<Offer> getAllOffersFromASpecificUserId(int id) {
        List<Offer> filteredOffers = new ArrayList<>();

        for(int i = 0; i<offers.size(); i++) {
            if (offers.get(i).getUserId() == id && offers.get(i).getStatus().equals(OfferStatus.OPEN)) {
                filteredOffers.add(offers.get(i));
            }
        }
        return filteredOffers;
    }

    //TODO Add a getIDIfStatusisOPEN ?
}

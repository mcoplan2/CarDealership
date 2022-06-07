package com.revature.controller;

import com.revature.model.Car;
import com.revature.model.Offer;
import com.revature.service.CarService;
import com.revature.service.OfferService;
import io.javalin.http.Handler;

import java.util.List;

public class OfferController {

    OfferService offerService = new OfferService();
    CarService carService = new CarService();

    public Handler getAllOffers = ctx -> {
        List<Offer> offers = offerService.getOffers();
        ctx.json(offers);
    };

    public Handler createNewOffer = ctx -> {
        Offer offer = ctx.bodyAsClass(Offer.class);
        // grabs the user id from the path and converts it to type int.
        offerService.createOffer(offer, Integer.parseInt(ctx.pathParam("id")));
    };
}

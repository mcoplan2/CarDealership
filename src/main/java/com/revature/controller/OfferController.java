package com.revature.controller;

import com.revature.model.Offer;
import com.revature.service.OfferService;
import io.javalin.http.Handler;

import java.util.List;

public class OfferController {

    OfferService offerService = new OfferService();

    public Handler getAllOffers = ctx -> {
        List<Offer> offers = offerService.getOffers();
        ctx.json(offers);
    };

    public Handler createNewOffer = ctx -> {
        Offer offer = ctx.bodyAsClass(Offer.class);
        // grabs the user id from the path and converts it to type int.
        offerService.createOffer(offer, Integer.parseInt(ctx.pathParam("id")));
    };

    public Handler getOfferById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(offerService.getOfferById(id));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };
}

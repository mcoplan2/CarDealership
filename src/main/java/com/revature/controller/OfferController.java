package com.revature.controller;

import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.service.OfferService;
import io.javalin.http.Handler;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class OfferController {

    OfferService offerService = new OfferService();

    public Handler createNewOffer = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);

        Offer offer = ctx.bodyAsClass(Offer.class);
        offerService.createOffer(offer, id);
    };

    public Handler getAllOffers = ctx -> {
        List<Offer> offers;

        String statusParam = ctx.queryParam("status");

        if(statusParam == null){
            offers = offerService.getOffers();
        }

        else {
            try {
                OfferStatus status = OfferStatus.valueOf(statusParam.toUpperCase(Locale.ROOT));
                offers = offerService.getAllOffersByStatus(status);
            } catch (IllegalArgumentException e){

                String failureMessage = "{\"success\":false, \"message\":\"" +
                        "Please only use the following role values: " + Arrays.toString(OfferStatus.values())
                        + "\"}";

                ctx.status(400).json(failureMessage);
                return;
            }
        }
        ctx.json(offers);
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

    public Handler updateOfferById = ctx -> {
        Offer offer = ctx.bodyAsClass(Offer.class);

        offerService.updateOfferById(offer);
    };

    public Handler deleteUserById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);

        offerService.deleteOfferById(id);
    };
}

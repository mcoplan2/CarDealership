package com.revature.controller;

import com.revature.model.*;
import com.revature.service.AuthService;
import com.revature.service.OfferService;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class OfferController {

    OfferService offerService = new OfferService();


    public OfferController() {}

    public Handler createNewOffer = ctx -> {
        String param = ctx.pathParam("id");
        Offer offer = ctx.bodyAsClass(Offer.class);


        int id = 0;
        try {
            id = Integer.parseInt(param);
            ctx.status(HttpCode.CREATED).json(offerService.createOffer(offer, id));
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Offer " + id + " could not be created, car is already purchased");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
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

                ctx.status(HttpCode.BAD_REQUEST).json(failureMessage);
                return;
            }
        }
        ctx.json(offers);
    };

    public Handler getOfferById = ctx -> {
        String param = ctx.pathParam("id");
        try {
            int id = Integer.parseInt(param);
            ctx.json(offerService.getOfferById(id));
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Enter a valid Offer ID");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
    };

    public Handler updateOfferById = ctx -> {
        Offer offer = ctx.bodyAsClass(Offer.class);

        offerService.updateOfferById(offer);
    };

    public Handler deleteOfferById = ctx -> {
        String param = ctx.pathParam("id");
        int id = 0;
        try {
            id = Integer.parseInt(param);
            offerService.deleteOfferById(id);
            ctx.status(HttpCode.OK).result("Offer " + id + " has been deleted");
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Offer " + id + " is not found, please enter an existing offer.");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }

    };

    public Handler getAllOpenOffersFromASpecificUserId = ctx -> {
        List<Offer> offers;

        String param = ctx.path();
        char[] ch = param.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : ch) {
            if (Character.isDigit(c)) {
                stringBuilder.append(c);
            }
        }
        int id = 0;
        try {
            id = Integer.parseInt(stringBuilder.toString());
            offers = offerService.getAllOpenOffersFromASpecificUserId(id);
            if (!offers.isEmpty())
                ctx.status(HttpCode.OK).json(offers);
            else
                ctx.status(HttpCode.NOT_FOUND).result("User " + id + " does not have any open offers");
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Offer " + id + " could not be found");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
    };

    public Handler approveOffer = ctx -> {
        String param = ctx.path();
        char[] ch = param.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : ch) {
            if (Character.isDigit(c)) {
                stringBuilder.append(c);
            }
        }

        // TODO ADD FOREIGN KEYS AGAIN REDO TABLE
        int offerId = 0;
        try {
            offerId = Integer.parseInt(stringBuilder.toString());
            String body = ctx.body();
            int userId = Integer.parseInt(body);
            String token = ctx.header("Authorization").replace("Bearer ", "");
            boolean authResult= AuthService.isValidEmployee(token);
            if (authResult) {
                boolean result = offerService.approveOfferById(offerId, userId);
                if (result)
                    ctx.status(HttpCode.OK).result("Offer " + offerId + " has been approved");
            } else
                ctx.status(HttpCode.FORBIDDEN).result("You cannot approve offers as a customer");
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Offer " + offerId + " is not found, please enter an existing offer.");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result("Please enter an ID as an integer");
        }
    };

    public Handler denyOffer = ctx -> {
        String param = ctx.path();
        char[] ch = param.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : ch) {
            if (Character.isDigit(c)) {
                stringBuilder.append(c);
            }
        }
        int offerId = 0;
        try {
            offerId = Integer.parseInt(stringBuilder.toString());
            String body = ctx.body();
            int userId = Integer.parseInt(body);
            String token = ctx.header("Authorization").replace("Bearer ", "");
            boolean authResult= AuthService.isValidEmployee(token);
            if (authResult) {
                boolean result = offerService.denyOfferById(offerId, userId);
                if (result)
                    ctx.status(HttpCode.OK).result("Offer " + offerId + " has been denied");
            }
            else
                ctx.status(HttpCode.FORBIDDEN).result("You cannot deny offers as a customer");
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Offer " + offerId + " is not found, please enter an existing offer.");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result("Please enter an ID as an integer");
        }
    };
}

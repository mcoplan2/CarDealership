package com.revature;

import com.revature.controller.AuthController;
import com.revature.controller.CarController;
import com.revature.controller.OfferController;
import com.revature.controller.UserController;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static io.javalin.apibuilder.ApiBuilder.*;

public class Driver {
    public static void main(String[] arg){

        Logger logger = LoggerFactory.getLogger(Driver.class);

        logger.debug("Debug Message Logged !!!");
        logger.info("Info Message Logged !!!");
        logger.error("Error Message Logged !!!", new NullPointerException("NullError"));

        UserController userController = new UserController();
        CarController carController = new CarController();
        OfferController offerController = new OfferController();

        Javalin app = Javalin.create().start(8080);

        app.get("/", context -> context.result("Welcome to the Car Dealership API"));

        app.before("/offers*", AuthController.withAuth);
        app.before("/cars*", AuthController.withAuth);
        app.before("/users*", AuthController.withAuth);
        app.routes(() -> {
            path("register", () -> {
                post(userController.createNewUser);
            });
            path("authenticate", () -> {
                post(AuthController.authenticate);
            });
            path("users", () -> {
                get(userController.getAllUsers);
                path("{id}", () -> {
                    get(userController.getUserById);
                    put(userController.updateUserById);
                    delete(userController.deleteUserById);
                    path("cars", () -> {
                        get(carController.getAllCarsFromASpecificUserId);
                        post(carController.createNewCar);
                    });
                    path("offers", () -> {
                        get(offerController.getAllOpenOffersFromASpecificUserId);
                    });
                });
            });
            path("cars", () -> {
                get(carController.getAllCars);
                path("{id}", () -> {
                    get(carController.getCarById);
                    put(carController.updateCarById);
                    delete(carController.deleteCarById);
                    path("offers", () -> {
                        post(offerController.createNewOffer);
                    });
                });
            });
            path("offers", () -> {
                get(offerController.getAllOffers);
                path("{id}", () -> {
                    get(offerController.getOfferById);
                    put(offerController.updateOfferById);
                    delete(offerController.deleteOfferById);
                    path("approve", () -> {
                        post(offerController.approveOffer);
                    });
                    path("deny", () -> {
                        post(offerController.denyOffer);
                    });
                });
            });
        });
    }
}

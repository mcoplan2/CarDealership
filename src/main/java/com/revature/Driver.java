package com.revature;

import com.revature.controller.CarController;
import com.revature.controller.OfferController;
import com.revature.controller.UserController;
import io.javalin.Javalin;

public class Driver {
    public static void main(String[] arg){

        UserController userController = new UserController();
        CarController carController = new CarController();
        OfferController offerController = new OfferController();

        Javalin app = Javalin.create().start(8080);

        app.get("/", context -> context.result("Welcome to the Car Dealership API"));

        // READ All users & CREATE a user
        app.get("/users", userController.getAllUsers);
        app.post("/users", userController.createNewUser);

        // READ user @ ID, UPDATE user @ ID, DELETE user @ ID
        app.get("/users/{id}", userController.getUserById);
        app.put("/users/{id}", userController.updateUserById);
        app.delete("/users/{id}", userController.deleteUserById);
        app.get("/users/{id}/offers", offerController.getAllOffersFromASpecificUserId);

        app.get("/cars", carController.getAllCars);
        app.post("/users/{id}/cars", carController.createNewCar);
        app.get("/users/{id}/cars", carController.getAllCarsFromASpecificUserId);

        app.get("/cars/{id}", carController.getCarById);
        app.put("/cars/{id}", carController.updateCarById);
        app.delete("/cars/{id}", carController.deleteCarById);

        app.get("/offers", offerController.getAllOffers);
        app.post("/cars/{id}/offers", offerController.createNewOffer);

        app.get("/offers/{id}", offerController.getOfferById);
        app.put("/offers/{id}", offerController.updateOfferById);
        app.delete("/offers/{id}", offerController.deleteUserById);
        app.post("/offers/{id}/review", offerController.approveOrDenyOffer);

        app.get("/customers", userController.getAllCustomers);
        app.get("/customers/{id}", userController.getCustomerById);

        app.get("/employees", userController.getAllEmployees);
        app.get("/employees/{id}", userController.getEmployeeById);
        //layout
        /*
        /users/{id}/customers/{id}
        /users/{id}/employees/{id}
        /
        */

    }
}

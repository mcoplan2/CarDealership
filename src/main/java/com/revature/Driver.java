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

        app.get("/users", userController.getAllUsers);
        app.post("/users", userController.createNewUser);

        app.get("/users/{id}", userController.getUserById);
        app.put("/users/{id}", userController.updateUserById);

        app.get("/users/{id}/cars", carController.getAllCars);
        app.post("/users/{id}/cars", carController.createNewCar);

        app.get("/cars", carController.getAllCars);
        app.get("/cars/{id}", carController.getCarById);

        app.get("/cars/{id}/offers", offerController.getAllOffers);
        app.post("/cars/{id}/offers", offerController.createNewOffer);

        app.get("/offers", offerController.getAllOffers);
        app.get("/offers/{id}", offerController.getOfferById);

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

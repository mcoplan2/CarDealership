package com.revature;

import com.revature.controller.CarController;
import com.revature.controller.UserController;
import io.javalin.Javalin;

public class Driver {
    public static void main(String[] arg){

        UserController userController = new UserController();
        CarController carController = new CarController();
        Javalin app = Javalin.create().start(8080);

        app.get("/", context -> context.result("Welcome to the Car Dealership API"));
        app.get("/users", userController.getAllUsers);
        app.get("/users/{id}", userController.getUserById);
        app.post("/users", userController.createNewUser);
        app.get("/customers", userController.getAllCustomers);
        app.get("/customers/{id}", userController.getCustomerById);
        app.get("/employees", userController.getAllEmployees);
        app.get("/employees/{id}", userController.getEmployeeById);
        app.get("/employees/*/cars", carController.getAllCars);
        app.post("/employees/{id}/cars", carController.createNewCar);
        app.get("/customers/*/cars", carController.getAllCars);
        app.post("/customers/{id}/cars", carController.createNewCar);
        //layout
        /*
        /users/{id}/customers/{id}
        /users/{id}/employees/{id}
        /
        */

    }
}

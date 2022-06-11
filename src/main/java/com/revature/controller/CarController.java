package com.revature.controller;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.service.CarService;
import io.javalin.http.Handler;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CarController {
    CarService carService = new CarService();

    public Handler createNewCar = ctx -> {
        Car car = ctx.bodyAsClass(Car.class);

        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);

        carService.createNewCar(car,id);
    };

    public Handler getAllCars = ctx -> {
        List<Car> cars;

        String roleParam = ctx.queryParam("status");

        if(roleParam == null){
            cars = carService.getCars();
        }

        else {
            try {
                CarStatus status = CarStatus.valueOf(roleParam.toUpperCase(Locale.ROOT));
                cars = carService.getAllCarsByStatus(status);
            } catch (IllegalArgumentException e){

                String failureMessage = "{\"success\":false, \"message\":\"" +
                        "Please only use the following role values: " + Arrays.toString(CarStatus.values())
                        + "\"}";

                ctx.status(400).json(failureMessage);
                return;
            }
        }
        ctx.json(cars);
    };


    public Handler getCarById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(carService.getCarById(id));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler updateCarById = ctx -> {
        Car car = ctx.bodyAsClass(Car.class);

        carService.updateCarById(car);
    };

    public Handler deleteCarById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);

        carService.deleteCarById(id);
    };

    public Handler getAllCarsFromASpecificUserId = ctx -> {
        List<Car> cars;

        String param = ctx.queryParam("id");
        int id = Integer.parseInt(param);

        cars = carService.getAllCarsOwnedFromASpecificUserId(id);
        ctx.json(cars);
        };

}

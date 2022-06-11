package com.revature.controller;

import com.revature.model.Car;
import com.revature.service.CarService;
import io.javalin.http.Handler;

import java.util.List;

public class CarController {
    CarService carService = new CarService();

    public Handler createNewCar = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        
        Car car = ctx.bodyAsClass(Car.class);
        carService.createNewCar(car, id);
    };

    public Handler getAllCars = ctx -> {
        List<Car> cars = carService.getCars();
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

}

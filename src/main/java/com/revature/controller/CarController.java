package com.revature.controller;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.service.CarService;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CarController {
    CarService carService = new CarService();

    public Handler createNewCar = ctx -> {
        Car car = ctx.bodyAsClass(Car.class);

        String param = ctx.pathParam("id");

        int id = 0;
        try {
            id = Integer.parseInt(param);
            ctx.status(HttpCode.CREATED).json(carService.createNewCar(car, id));
        } catch (NullPointerException e) {
            ctx.status(HttpCode.BAD_REQUEST).result("ID: " + id + " can not create cars, they are not an employee");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
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
        int id = 0;
        try {
            id = Integer.parseInt(param);
            ctx.json(carService.getCarById(id));
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Car " + id + " could not be found");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
    };

    public Handler updateCarById = ctx -> {
        Car car = ctx.bodyAsClass(Car.class);

        carService.updateCarById(car);
    };

    public Handler deleteCarById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            boolean result = carService.deleteCarById(id);
            if (result)
                ctx.status(HttpCode.OK).result("Car " + id + " successfully deleted");
            else
                ctx.status(HttpCode.BAD_REQUEST).result("Car " + id + " could not be deleted");
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Car " + id + " could not be found");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
    };

    public Handler getAllCarsFromASpecificUserId = ctx -> {
        List<Car> cars;

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
            cars = carService.getAllCarsOwnedFromASpecificUserId(id);
            if (!cars.isEmpty())
                ctx.status(HttpCode.OK).json(cars);
            else
                ctx.status(HttpCode.NOT_FOUND).result("User " + id + " does not own any cars");
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Car " + id + " could not be found");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
    };

}

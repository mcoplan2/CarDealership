package controller;

import io.javalin.http.Handler;
import model.Car;
import model.User;
import service.CarService;

import java.util.List;

public class CarController {
    CarService carService = new CarService();

    public Handler createNewCar = ctx -> {
        Car car = ctx.bodyAsClass(Car.class);
        // need to get user ID from path
        carService.createCar(car, Integer.parseInt(ctx.pathParam("id")));
    };

    public Handler getAllCars = ctx -> {
        List<Car> cars = carService.getCars();
        ctx.json(cars);
    };
}

package controller;

import io.javalin.http.Handler;
import model.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    UserService userService = new UserService();

    public Handler getAllUsers = ctx -> {
        List<User> users = userService.getUsers();
        ctx.json(users);
    };

    public Handler getAllCustomers = ctx -> ctx.json(userService.getAllCustomersAsString());

    public Handler getAllEmployees = ctx -> ctx.json(userService.getAllEmployeesAsString());


    public Handler getUserById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getUserById(id));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler getEmployeeById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getEmployeeById(id, true));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler getCustomerById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getCustomerById(id,false));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };


    public Handler createNewUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        userService.createUser(user);
    };
}

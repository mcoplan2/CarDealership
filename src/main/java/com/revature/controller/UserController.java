package com.revature.controller;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.service.UserService;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class UserController {

    UserService userService = new UserService();

    public Handler createNewUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        ctx.status(HttpCode.CREATED).json(userService.createNewUser(user));
    };

    public Handler getAllUsers = ctx -> {
        List<User> users;

        String roleParam = ctx.queryParam("role");

        if(roleParam == null){
            users = userService.getUsers();
        }

        else {
            try {
                UserRoles role = UserRoles.valueOf(roleParam.toUpperCase(Locale.ROOT));
                users = userService.getAllUsersByRole(role);
            } catch (IllegalArgumentException e){

                String failureMessage = "{\"success\":false, \"message\":\"" +
                        "Please only use the following role values: " + Arrays.toString(UserRoles.values())
                        + "\"}";

                ctx.status(400).json(failureMessage);
                return;
            }
        }
        ctx.json(users);
    };

    public Handler getAllCustomers = ctx -> {
        List<User> customers = userService.getAllUsersByRole(UserRoles.CUSTOMER);
        ctx.json(customers);
    };

    public Handler getAllEmployees = ctx -> {
        List<User> employees = userService.getAllUsersByRole(UserRoles.EMPLOYEE);
        ctx.json(employees);
    };

    public Handler getUserById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getUserById(id));
        } catch (NullPointerException e){
            ctx.status(HttpCode.BAD_REQUEST).result("User is not found, please enter a valid user ID");
        }
    };

    public Handler getEmployeeById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getUserIdAndCheckRole(id, UserRoles.EMPLOYEE));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler getCustomerById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getUserIdAndCheckRole(id, UserRoles.CUSTOMER));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler updateUserById = ctx -> {
        User user = ctx.bodyAsClass(User.class);

        ctx.json(userService.updateUserById(user));
    };

    public Handler deleteUserById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);

        if(userService.deleteUserById(id)) {
            ctx.result("User " + id + "successfully deleted");
        } else
            ctx.status(HttpCode.BAD_REQUEST).result("User " + id + "could not be deleted");
        };
}

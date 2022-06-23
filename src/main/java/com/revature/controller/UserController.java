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
        try {
            ctx.status(HttpCode.CREATED).json(userService.createNewUser(user));
        } catch (NullPointerException | NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result("User could not be created");
        }
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

                ctx.status(HttpCode.BAD_REQUEST).json(failureMessage);
                return;
            }
        }
        ctx.json(users);
    };

    public Handler getUserById = ctx -> {
        String param = ctx.pathParam("id");
        int id = 0;
        try {
            id = Integer.parseInt(param);
            ctx.json(userService.getUserById(id));
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("User " + id + " could not be found");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
    };

    public Handler updateUserById = ctx -> {
        User user = ctx.bodyAsClass(User.class);

        ctx.json(userService.updateUserById(user));
    };

    public Handler deleteUserById = ctx -> {
        String param = ctx.pathParam("id");

        int id = 0;
        try {
            id = Integer.parseInt(param);
            boolean result = userService.deleteUserById(id);
            if (result)
                ctx.status(HttpCode.OK).result("User " + id + " successfully deleted");
            else
                ctx.status(HttpCode.BAD_REQUEST).result("User " + id + " could not be deleted");
        } catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("User " + id + " could not be found");
        } catch (NumberFormatException e) {
            ctx.status(HttpCode.BAD_REQUEST).result(param + " is not a valid integer. Enter a valid integer");
        }
    };
}

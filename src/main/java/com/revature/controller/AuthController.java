package com.revature.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.revature.model.User;
import com.revature.service.AuthService;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class AuthController {

    public static Handler authenticate =  ctx -> {
        User user = ctx.bodyAsClass(User.class);
        String token = AuthService.authenticateUser(user);
        // check if null fail, check if not null load token

        if (token != null) {
            ctx.status(HttpCode.OK).result("Bearer " + token);
        } else {
            ctx.status(HttpCode.UNAUTHORIZED).result("Bad credentials.");
        }
    };

    // every handler must auth before using app
    public static Handler withAuth = ctx -> {
      //check for authorization header
        try {
            String token = ctx.header("Authorization").replace("Bearer ", "");

            AuthService.isValidToken(token);

        } catch (NullPointerException | JWTVerificationException e) {
            throw new ForbiddenResponse("You must add in a valid bearer token under Headers");
        }
    };

    public static Handler withEmployeeAuth = ctx -> {
        //check for authorization header
        try {
            String token = ctx.header("Authorization").replace("Bearer ", "");

            boolean result = AuthService.isValidEmployee(token);
            if(!result) {
                ctx.status(HttpCode.FORBIDDEN).result("You must be an Employee for this action");
            }

        } catch (NullPointerException | JWTVerificationException e) {
            throw new ForbiddenResponse("You must add in a valid bearer token under Headers");
        }
    };
}

package com.revature.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthService {

    // TODO properties file
    private static final String ISSUER = "auth0";
    private static final String SECRET = "secret";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    private static final JWTVerifier JWT_VERIFIER = JWT.require(ALGORITHM).withIssuer(ISSUER).build();
    private static final UserRepository userRepository = new UserRepository();

    static Logger logger = LoggerFactory.getLogger(AuthService.class);
    public static String authenticateUser(User user) {
        // Get the user by its username
        User dbUser = userRepository.getByUserName(user.getUserName());
        if (dbUser != null) {
            // check if passwords match
            if (dbUser.getPassword().equals(user.getPassword())) {
                // build them a token
                return generateToken(dbUser);

            }
        }
        return null;
    }

    public static void isValidToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = JWT_VERIFIER.verify(token);
    }

    public static boolean isValidEmployee(String token) throws JWTVerificationException {
        DecodedJWT jwt = JWT_VERIFIER.verify(token);

        Claim claim = jwt.getClaim("role");
        UserRoles role = UserRoles.valueOf(claim.as(String.class));
        return role == UserRoles.EMPLOYEE;
    }

    private static String generateToken(User user) {
        String token = null;

        try {
            token = JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim("role", user.getRole().toString())
                    .sign(ALGORITHM);
        } catch (JWTCreationException e) {
            logger.warn(e.getMessage());
        }
        return token;
    }
}

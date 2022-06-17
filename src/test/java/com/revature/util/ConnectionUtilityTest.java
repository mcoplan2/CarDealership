package com.revature.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtilityTest {

    @Test
    public void whenGivenValidCredentialsGetConnectionReturnsValidConnection() throws SQLException {
        Connection connection = ConnectionUtility.getConnection();

        Assertions.assertNotNull(connection);
    }
    @Test
    public void whenLoadingPropertiesLoadsValidProperties(){
        String url="jdbc:postgresql://localhost:5432/postgres?currentSchema=project1";
        String user="postgres";
        String password="password";
        Properties properties = ConnectionUtility.loadProperties();

        Assertions.assertEquals(url, properties.getProperty(url));
        Assertions.assertEquals(user, properties.getProperty(user));
        Assertions.assertEquals(password, properties.getProperty(password));
    }
}
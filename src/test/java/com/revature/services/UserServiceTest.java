package com.revature.services;

import com.revature.model.User;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserServiceTest {

    //Test that creating an Employee populates the List
    @Test
    public void userServiceCreateEmployee(){
        UserService userService = new UserService();
        userService.createUser(new User("Test","Test","test","test",User.Role.EMPLOYEE));
        Assertions.assertEquals(1, userService.userCount());
    }
    //Test that creating a Customer populates the List
    @Test
    public void userServiceCreateCustomer(){
        UserService userService = new UserService();
        userService.createUser(new User("Test","Test","test","test",User.Role.CUSTOMER));
        Assertions.assertEquals(1, userService.userCount());
    }

    //Test that creates various different users to test different functionality
    //Testing different String combinations
    //Testing various IDs
    //Testing that Customers cannot create users
    //Flag = TRUE implies it is an Employee(Only Employees can create Users)
    //However, if there are no users, one can be created.
    @Test
    public void userServiceCreateManyUsers(){
        int numUsers = 5;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        User.Role[] role = {User.Role.CUSTOMER, User.Role.EMPLOYEE, User.Role.CUSTOMER, User.Role.CUSTOMER, User.Role.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals(5, userService.userCount());
    }
    @Test
    public void userServiceCreateManyUsersTestAllUsersString(){
        int numUsers = 5;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        int[] id  = {0, 1, 2, 3, 4};
        User.Role[] role = {User.Role.CUSTOMER, User.Role.EMPLOYEE, User.Role.CUSTOMER, User.Role.CUSTOMER, User.Role.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals("2",userService.getUsers());
    }
    @Test
    public void userServiceCreateManyUsersTestCustomerString(){
        int numUsers = 5;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        int[] id  = {0, 1, 2, 3, 4};
        User.Role[] role = {User.Role.CUSTOMER, User.Role.EMPLOYEE, User.Role.CUSTOMER, User.Role.CUSTOMER, User.Role.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals("2",userService.getCustomers());
    }
    @Test
    public void userServiceCreateManyUsersTestEmployeeString(){
        int numUsers = 5;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        int[] id  = {0, 1, 2, 3, 4};
        User.Role[] role = {User.Role.CUSTOMER, User.Role.EMPLOYEE, User.Role.CUSTOMER, User.Role.CUSTOMER, User.Role.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals("2",userService.getAllEmployeesAsString());
    }
    @Test
    public void userServiceCheckIfGetUserByIDWorks(){
        int numUsers = 5;
        String s = "ID: 2" + "\n" + "Username: " +"Test4" + "\n"+ "Role: " + User.Role.CUSTOMER + "\n\n";
        Object d = s;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        int[] id  = {1, 2, 3, 4, 5};
        User.Role[] role = {User.Role.CUSTOMER, User.Role.EMPLOYEE, User.Role.CUSTOMER, User.Role.CUSTOMER, User.Role.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals(d,userService.getUserById(2).toString());
    }
}

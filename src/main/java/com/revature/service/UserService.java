package com.revature.service;

import com.revature.model.Car;
import com.revature.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final List<User> users = new ArrayList<>();


    // maybe in future we check if user(0) to see role, then create the API layers for offers

    // maybe check if the user has been created
    public void createUser(User user) {
        // if (user.flag || users.size() == 0)
        // boolean found = true;
        //List<User> users = getUsers();
        //if(userCount() == 0){
        users.add(user);
        //}
        /*
        for(int i=0; i<userCount(); i++){
            if(user.id != users.get(i).id){
                found = false;
            }
        }
        if (!found) {
            users.add(user);
        }

         */
    }

    public static List<User> getUsers() {
        return users;
    }

    public List<User> getCustomers() {
        List<User> users = getUsers();
        List<User> customers = new ArrayList<>();
        for (int i = 0; i < userCount(); i++) {
            if (users.get(i).getRole().equals(User.Role.CUSTOMER)) {
                customers.add(users.get(i));
            }
        }
        return customers;
    }


    public List<User> getEmployees() {
        List<User> users = getUsers();
        List<User> employees = new ArrayList<>();
        for (int i = 0; i < userCount(); i++) {
            if (users.get(i).getRole().equals(User.Role.EMPLOYEE)) {
                employees.add(users.get(i));
            }
        }
        return employees;
    }

    public String getAllCustomersAsString() {
        List<User> customers = getUsers();
        StringBuilder builder = new StringBuilder();

        for (User user : customers) {
            if (user.getRole().equals(User.Role.CUSTOMER)) {
                builder.append(user.getUserName()).append(" ").append(user.getLastName()).
                        append(" ").append(user.getFirstName()).append("\n\n");
            }
        }
        return builder.toString();
    }

    public String getAllEmployeesAsString() {
        List<User> employees = getUsers();
        StringBuilder builder = new StringBuilder();

        for (User user : employees) {
            if (user.getRole().equals(User.Role.EMPLOYEE)) {
                builder.append(user.id).append("\t").append(user.getUserName()).append("\t").append(user.getLastName()).
                        append("\t").append(user.getFirstName()).append("\n\n");

            }
        }
        return builder.toString();
    }


    public String getAllUsersAsString() {
        List<User> users = getUsers();
        StringBuilder builder = new StringBuilder();

        for (User user : users) {
            builder.append(user.id).append("\t").append(user.getUserName()).append("\t").append(user.getLastName()).
                    append("\t").append(user.getFirstName()).append("\n\n");

        }
        return builder.toString();

    }


    public User getUserById(int id) {
        for (User user : users) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }

    public User getCustomerById(int id, User.Role role) {
        for (User user : users) {
            if (user.id == id && role.equals(User.Role.CUSTOMER)) {
                return user;
            }
        }
        return null;
    }

    public User getEmployeeById(int id, User.Role role) {
        for (User user : users) {
            if (user.id == id && role.equals(User.Role.EMPLOYEE)) {
                return user;
            }
        }
        return null;
    }

    public static int userCount() {
        return users.size();
    }

    // checks to see if the user id matches the user and if the car they own is equal.
    // Look at OFFERS, maybem ove this function to offers
    // ALSO NEED TO UPDATE CURRENT CAR TO TAKEN WHEN APPROVED BY EMPLOYEE ( THIS IS OFFERS).
    public User getCarsById(int id) {
        List<Car> cars = CarService.getCars();
        for (User user : users) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }
}


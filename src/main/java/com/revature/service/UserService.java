package com.revature.service;

import com.revature.model.Car;
import com.revature.model.User;
import com.revature.model.UserRoles;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public UserService(List<User> users) {
        this.users = users;
    }

    // maybe check if the user has been created
    public boolean createNewUser(User user) {
        return users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }

    public List<User> getAllUsersByRole(UserRoles role) {
        List<User> filteredUsers = new ArrayList<>();
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getRole().equals(role)) {
                filteredUsers.add(users.get(i));
            }
        }
        return filteredUsers;
    }

    public List<User> getCustomers() {
        List<User> users = getUsers();
        List<User> customers = new ArrayList<>();
        for (int i = 0; i < userCount(); i++) {
            if (users.get(i).getRole().equals(UserRoles.CUSTOMER)) {
                customers.add(users.get(i));
            }
        }
        return customers;
    }

    public List<User> getEmployees() {
        List<User> users = getUsers();
        List<User> employees = new ArrayList<>();
        for (int i = 0; i < userCount(); i++) {
            if (users.get(i).getRole().equals(UserRoles.EMPLOYEE)) {
                employees.add(users.get(i));
            }
        }
        return employees;
    }

    public User getUserById(int id) {

        for(int i = 0; i<users.size(); i++) {
            if(users.get(i).getId() == id){
                return users.get(i);
            }
        }
        return null;
    }

    public User getCustomerById(int id) {

        for (int i = 0; i < users.size(); i++) {
            if ((users.get(i).getId() == id) && users.get(i).getRole().equals(UserRoles.CUSTOMER)) {
                return users.get(i);
            }
        }
        return null;
    }

    public User getEmployeeById(int id) {

        for (int i = 0; i < users.size(); i++) {
            if ((users.get(i).getId() == id) && users.get(i).getRole().equals(UserRoles.EMPLOYEE)) {
                return users.get(i);
            }
        }
        return null;
    }

    public static int userCount() {
        return users.size();
    }

    public boolean deleteUserById(int id){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    // Updates the user at the current ID
    // Pass in the ID you want to modify wih the new User Object.
    public boolean updateUserById(int id, User user){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == id) {
                users.set(id, user);
                return true;
            }
        }
        return false;
    }
    // checks to see if the user id matches the user and if the car they own is equal.
    // Look at OFFERS, maybem ove this function to offers
    // ALSO NEED TO UPDATE CURRENT CAR TO TAKEN WHEN APPROVED BY EMPLOYEE ( THIS IS OFFERS).
    public User getCarsById(int id) {
        List<Car> cars = CarService.getCars();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}


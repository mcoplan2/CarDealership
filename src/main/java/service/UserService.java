package service;

import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();


    // maybe in future we check if user(0) to see role, then create the API layers for offers

    // maybe check if the user has been created
    public void createUser(User user) {
       // if (user.flag || users.size() == 0)
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
/*
    public List<User> getCustomers() {
        List<User> customers = getUsers();
        for(User user : customers){
            if(!user.flag){
                return users;
            }
        }
        return null;
    }


    public List<User> getAllEmployees() {
        List<User> employees = getUsers();

        for (User user : employees) {
            if (!user.flag) {
                employees.add(user);
            }
        }
        return employees;
    }
    */
    public String getAllCustomersAsString() {
        List<User> customers = getUsers();
        StringBuilder builder = new StringBuilder();

        for (User user : customers) {
            if (!user.flag) {
                builder.append(user.userName).append(" ").append(user.lastName).
                        append(" ").append(user.firstName).append("\n\n");
            }
        }
        return builder.toString();
    }

    public String getAllEmployeesAsString() {
        List<User> employees = getUsers();
        StringBuilder builder = new StringBuilder();

        for (User user : employees) {
            if (user.flag) {
                builder.append(user.id).append("\t").append(user.userName).append("\t").append(user.lastName).
                        append("\t").append(user.firstName).append("\n\n");

            }
        }
        return builder.toString();
    }

    public String getAllUsersAsString() {
        List<User> users = getUsers();
        StringBuilder builder = new StringBuilder();

        for (User user : users) {
            builder.append(user.id).append("\t").append(user.userName).append("\t").append(user.lastName).
                    append("\t").append(user.firstName).append("\n\n");

        }
        return builder.toString();

    }

    public User getUserById(int id){
        for(User user:users){
            if(user.id == id) {
                return user;
            }
        }
        return null;
    }
    public User getCustomerById(int id, boolean flag){
        for (User user:users){
            if(user.id == id && user.flag == flag){
                return user;
            }
        }
        return null;
    }

    public User getEmployeeById(int id, boolean flag){
        for (User user:users){
            if(user.id == id && user.flag == flag){
                return user;
            }
        }
        return null;
    }
    public int userCount() {
        return users.size();
    }

    public User get(int index){
        return users.get(index);
        }
}


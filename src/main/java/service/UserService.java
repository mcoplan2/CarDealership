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

    public List<User> getUsers() {
        return users;
    }

    public List<User> getCustomers() {
        List<User> users = getUsers();
        List<User> customers = new ArrayList<>();
        for (int i=0; i < userCount(); i++){
            if (users.get(i).role.equals(User.Role.CUSTOMER)) {
                customers.add(users.get(i));
            }
        }
        return customers;
    }


    public List<User> getEmployees() {
        List<User> users = getUsers();
        List<User> employees = new ArrayList<>();
        for (int i=0; i < userCount(); i++){
            if (users.get(i).role.equals(User.Role.EMPLOYEE)) {
                employees.add(users.get(i));
            }
        }
        return employees;
    }

    public String getAllCustomersAsString() {
        List<User> customers = getUsers();
        StringBuilder builder = new StringBuilder();

        for (User user : customers) {
            if (user.role.equals(User.Role.CUSTOMER)) {
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
            if (user.role.equals(User.Role.EMPLOYEE)) {
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
    public User getCustomerById(int id, User.Role role){
        for (User user:users){
            if(user.id == id && role.name().equalsIgnoreCase("CUSTOMER")){
                return user;
            }
        }
        return null;
    }

    public User getEmployeeById(int id, User.Role role){
        for (User user:users){
            if(user.id == id && role.name().equalsIgnoreCase("EMPLOYEE")){
                return user;
            }
        }
        return null;
    }
    public int userCount() {
        return users.size();
    }

    /*
    public User get(int index){
        return users.get(index);
        }

     */
}


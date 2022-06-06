package com.revature.model;

public class User {

    public enum Role{
        CUSTOMER, EMPLOYEE;
    }
    public String firstName;
    public String lastName;
    public String userName;
    public String password;
    public int id;
    //0 for customer, 1 for employee
    public Role role;

    public User(){
    }
    //this constructor is used to check if the user is a Customer or Employee.
   public User(Role role){
        this.role=role;
    }

    public User(String firstName, String lastName, String userName, String password, int id, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Username: " + userName + "\n" +
                "Role: " + role + "\n\n";
    }
}

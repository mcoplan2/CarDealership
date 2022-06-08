package com.revature.model;

public class User {

    public enum Role{
        CUSTOMER, EMPLOYEE;
    }
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    public int id;
    private static int incrementId = 0;
    //0 for customer, 1 for employee
    private Role role;

    public User(){
        this.id = incrementId++;
    }
    //this constructor is used to check if the user is a Customer or Employee.
   public User(Role role){
        this.role=role;
    }

    public User(String firstName, String lastName, String userName, String password, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.id = incrementId++;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Username: " + userName + "\n" +
                "Role: " + role + "\n\n";
    }
}

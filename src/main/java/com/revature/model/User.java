package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int id;
    private UserRoles role;

    public User(){}

    public User(String firstName, String lastName, String userName, String password, UserRoles role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public UserRoles getRole() {
        return role;
    }

    public User setRole(UserRoles role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, password, id, role);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Username: " + userName + "\n" +
                "Role: " + role + "\n\n";
    }
}

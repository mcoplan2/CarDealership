package com.revature.service;

import com.revature.model.Car;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    // maybe check if the user has been created
    public User createNewUser(User user) {
        return userRepository.create(user);
    }

    public List<User> getUsers() {
        return userRepository.getAll();
    }

    public List<User> getAllUsersByRole(UserRoles role) {
        return userRepository.getAllByRole(role);
    }

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public int userCount() {
        return userRepository.count();
    }

    public User getUserIdAndCheckRole(int id, UserRoles role) {
        return userRepository.getUserIdByRole(id,role);
    }
    /*
    public User getCustomerById(int id) {
        if (getUserById(id).getId() == id && g) {
            return userRepository.getById(id);
        } else {
            return null;
        }
    }

    public User getEmployeeById(int id) {

        for (int i = 0; i < users.size(); i++) {
            if ((users.get(i).getId() == id) && users.get(i).getRole().equals(UserRoles.EMPLOYEE)) {
                return users.get(i);
            }
        }
        return null;
    }
    */

    // Updates the user at the current ID
    // Pass in the ID you want to modify wih the new User Object.
    public User updateUserById(User user) {
        return userRepository.update(user);
    }

    public boolean deleteUserById(int id) {
        return userRepository.deleteById(id);
    }
}



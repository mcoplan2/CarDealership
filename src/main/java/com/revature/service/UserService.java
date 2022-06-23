package com.revature.service;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.repository.UserRepository;
import java.util.List;

public class UserService {

    private UserRepository userRepository;
    private static UserService instance;

    public UserService() {
        userRepository = new UserRepository();
        instance = this;
    }

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        instance = this;
    }

    public static UserService getInstance() {
        return instance;
    }
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

    public User updateUserById(User user) {
        return userRepository.update(user);
    }

    public boolean deleteUserById(int id) {
        return userRepository.deleteById(id);
    }
}



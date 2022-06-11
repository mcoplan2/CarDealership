package com.revature.repository;

import com.revature.model.User;
import com.revature.model.UserRoles;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements CrudDAO<User> {

    private List<User> users;

    public UserRepository(){
        users = new ArrayList<>();
    }

    public UserRepository(List<User> users){
        this.users = users;
    }

    // POST Method
    @Override
    public User create(User user) {
        if(users.add(user)) {
            return user;
        } else {
            return null;
        }
    }

    // GET Method
    @Override
    public List<User> getAll() {
        return users;
    }

    // GET Method
    @Override
    public User getById(int id) {
        for(int i = 0; i<users.size(); i++) {
            if(users.get(i).getId() == id){
                return users.get(i);
            }
        }
        return null;
    }

    // PUT Method
    @Override
    public User update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == user.getId()) {
                return users.set(i, user);
            }
        }
        return null;
    }

    // DELETE Method
    @Override
    public boolean deleteById(int id) {
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    // Method to return size of List
    @Override
    public int count() {
        return users.size();
    }

    // Method to get all users by a specific Role
    public List<User> getAllByRole(UserRoles role) {
        List<User> filteredUsers = new ArrayList<>();

        for(int i = 0; i<users.size(); i++) {
            if (users.get(i).getRole().equals(role)) {
                filteredUsers.add(users.get(i));
            }
        }
        return filteredUsers;
    }

    public User getUserIdByRole(int id, UserRoles role) {
        for(int i = 0; i<users.size(); i++) {
            if(users.get(i).getId() == id && users.get(i).getRole().equals(role)){
                return users.get(i);
            }
        }
        return null;
    }


}


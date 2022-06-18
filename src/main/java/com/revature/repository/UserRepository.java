package com.revature.repository;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.util.ConnectionUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserRepository implements CrudDAO<User> {

    private List<User> users;

    public UserRepository(){
        users = new ArrayList<>();
    }

    public UserRepository(List<User> users){
        this.users = users;
    }

    @Override
    public User create(User user) {
        String sql = "insert into users(first_name, last_name, username, pass, role) values(?,?,?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().name());

            int success = statement.executeUpdate();

            if (success == 1) {
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                users.add(new User()
                        .setId(results.getInt("user_id"))
                        .setFirstName(results.getString("first_name"))
                        .setLastName(results.getString("last_name"))
                        .setUserName(results.getString("username"))
                        .setPassword(results.getString("pass"))
                        .setRole((UserRoles.valueOf(results.getString("role")))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(int id) {
        String sql = "select * from users where user_id = "+id;

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if(results.next()) {
                User user = new User();
                users.add(user
                        .setId(results.getInt("user_id"))
                        .setFirstName(results.getString("first_name"))
                        .setLastName(results.getString("last_name"))
                        .setUserName(results.getString("username"))
                        .setPassword(results.getString("pass"))
                        .setRole((UserRoles.valueOf(results.getString("role")))));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User user) {
        String sql = "update users set first_name = ?, last_name = ?, username = ?, pass = ?, role = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().name());

            int success = statement.executeUpdate();

            if (success == 1) {
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // DELETE Method
    @Override
    public boolean deleteById(int id) {
        String sql = "delete from users where user_id = "+id;

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            int results = stmt.executeUpdate();

            return results == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int count() {
        String sql = "select count(*) from users";
        int count = 0;

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if(results.next()) {
                count = results.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<User> getAllByRole(UserRoles role) {
        List<User> users = new ArrayList<>();
        String sql = "select * from users where role = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,role.name());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                users.add(new User()
                        .setId(results.getInt("user_id"))
                        .setFirstName(results.getString("first_name"))
                        .setLastName(results.getString("last_name"))
                        .setUserName(results.getString("username"))
                        .setPassword(results.getString("pass"))
                        .setRole((UserRoles.valueOf(results.getString("role")))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserIdByRole(int id, UserRoles role) {
        String sql = "select * from users where user_id = ? and role = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, role.name());
            ResultSet results = stmt.executeQuery();

            if(results.next()) {
                User user = new User();
                users.add(user
                        .setId(results.getInt("user_id"))
                        .setFirstName(results.getString("first_name"))
                        .setLastName(results.getString("last_name"))
                        .setUserName(results.getString("username"))
                        .setPassword(results.getString("pass"))
                        .setRole((UserRoles.valueOf(results.getString("role")))));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


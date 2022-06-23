package com.revature.repository;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements CrudDAO<User> {

    private List<User> users;

    static Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository(){
        users = new ArrayList<>();
    }

    public UserRepository(List<User> users){
        this.users = users;
    }

    @Override
    public User create(User user) {
        String sql = "insert into users(first_name, last_name, username, pass, user_role_id) values(?,?,?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getRole().ordinal());

            int success = statement.executeUpdate();

            if (success == 1) {
                return user;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
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
                        .setRole((UserRoles.values()[(results.getInt("user_role_id"))])));
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return users;
    }

    @Override
    public User getById(int id) {
        String sql = "select * from users where user_id = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();

            if(results.next()) {
                User user = new User();
                users.add(user
                        .setId(results.getInt("user_id"))
                        .setFirstName(results.getString("first_name"))
                        .setLastName(results.getString("last_name"))
                        .setUserName(results.getString("username"))
                        .setPassword(results.getString("pass"))
                        .setRole((UserRoles.values()[(results.getInt("user_role_id"))])));
                return user;
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public User update(User user) {
        String sql = "update users set first_name = ?, last_name = ?, username = ?, pass = ?, role = ? where user_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getRole().ordinal());

            int success = statement.executeUpdate();

            if (success == 1) {
                return user;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    // DELETE Method
    @Override
    public boolean deleteById(int id) {
        String sql = "delete from users where user_id = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            int results = stmt.executeUpdate();

            return results == 1;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
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
            logger.warn(e.getMessage());
        }
        return count;
    }

    public List<User> getAllByRole(UserRoles role) {
        List<User> users = new ArrayList<>();
        String sql = "select * from users where user_role_id = ?";

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
                        .setRole((UserRoles.valueOf(results.getString("user_role_id")))));
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return users;
    }

    public User getByUserName(String username) {
        String sql = "select * from users where username= ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet results = stmt.executeQuery();

            if(results.next()) {
                User user = new User();
                users.add(user
                        .setId(results.getInt("user_id"))
                        .setFirstName(results.getString("first_name"))
                        .setLastName(results.getString("last_name"))
                        .setUserName(results.getString("username"))
                        .setPassword(results.getString("pass"))
                        .setRole((UserRoles.values()[(results.getInt("user_role_id"))])));
                return user;
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }
}


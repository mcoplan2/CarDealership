package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository  implements CrudDAO<Car> {
    private List<Car> cars;

    static Logger logger = LoggerFactory.getLogger(CarRepository.class);

    public CarRepository(){
        cars = new ArrayList<>();
    }

    public CarRepository(List<Car> cars){
        this.cars = cars;
    }

    @Override
    public Car create(Car car) {
        String sql = "insert into cars(make, model, year, car_status_id) values(?,?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setInt(4, car.getStatus().ordinal());

            int success = statement.executeUpdate();

            if (success == 1) {
                return car;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setId(results.getInt("car_id")).
                        setMake(results.getString("make")).
                        setModel(results.getString("model")).
                        setYear(results.getInt("year")).
                        setStatus(CarStatus.values()[(results.getInt("car_status_id"))]).
                        setUserId(results.getInt("user_id")));
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return cars;
    }

    @Override
    public Car getById(int id) {
        String sql = "select * from cars where car_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                Car car = new Car();
                cars.add(car.
                        setId(results.getInt("car_id")).
                        setMake(results.getString("make")).
                        setModel(results.getString("model")).
                        setYear(results.getInt("year")).
                        setStatus(CarStatus.values()[(results.getInt("car_status_id"))]).
                        setUserId(results.getInt("user_id")));

                return car;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public Car update(Car car) {
        String sql = "update cars set make = ?, model = ?, year = ?, car_status_id = ?, user_id = ? where car_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setInt(4, car.getStatus().ordinal());
            statement.setInt(5, car.getUserId());
            statement.setInt(6, car.getId());

            int success = statement.executeUpdate();

            if (success == 1) {
                return getById(car.getId());
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from cars where car_id = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            int success = stmt.executeUpdate();

            return success == 1;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return false;
    }

    @Override
    public int count() {
        String sql = "select count(*) from cars";
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

    public List<Car> getAllByStatus(CarStatus status) {
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars where car_status_id = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, status.ordinal());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setId(results.getInt("car_id")).
                        setMake(results.getString("make")).
                        setModel(results.getString("model")).
                        setYear(results.getInt("year")).
                        setStatus(CarStatus.values()[(results.getInt("car_status_id"))]).
                        setUserId(results.getInt("user_id")));
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return cars;
    }

    public Car getCarIdByRole(int id, CarStatus status) {
        String sql = "select * from cars where car_id = ? and car_status_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, status.name());
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                Car car = new Car();
                cars.add(car.
                        setId(results.getInt("car_id")).
                        setMake(results.getString("make")).
                        setModel(results.getString("model")).
                        setYear(results.getInt("year")).
                        setStatus(CarStatus.values()[(results.getInt("car_status_id"))]).
                        setUserId(results.getInt("user_id")));

                return car;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    public List<Car> getAllByUserId(int userId) {
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars where user_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setId(results.getInt("car_id")).
                        setMake(results.getString("make")).
                        setModel(results.getString("model")).
                        setYear(results.getInt("year")).
                        setStatus(CarStatus.values()[(results.getInt("car_status_id"))]).
                        setUserId(results.getInt("user_id")));
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return cars;
    }
}

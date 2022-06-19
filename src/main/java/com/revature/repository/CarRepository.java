package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.util.ConnectionUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository  implements CrudDAO<Car> {
    private List<Car> cars;

    public CarRepository(){
        cars = new ArrayList<>();
    }

    public CarRepository(List<Car> cars){
        this.cars = cars;
    }

    // TODO ADD TRY CATCH BLOCK FOR UNWANTED INPUTS
    @Override
    public Car create(Car car) {
        String sql = "insert into cars(make, model, year, status) values(?,?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setString(4, car.getStatus().name());

            int success = statement.executeUpdate();

            if (success == 1) {
                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
                        setStatus(CarStatus.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // GET Method
    @Override
    public Car getById(int id) {
        String sql = "select * from cars where car_id = "+id;

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                Car car = new Car();
                cars.add(car.
                        setId(results.getInt("car_id")).
                        setMake(results.getString("make")).
                        setModel(results.getString("model")).
                        setYear(results.getInt("year")).
                        setStatus(CarStatus.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")));

                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PUT Method
    @Override
    public Car update(Car car) {
        String sql = "update cars set make = ?, model = ?, year = ?, status = ?, user_id = ? where car_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setString(4, car.getStatus().name());
            statement.setInt(5, car.getUserId());
            statement.setInt(6, car.getId());

            int success = statement.executeUpdate();

            if (success == 1) {
                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // DELETE Method
    @Override
    public boolean deleteById(int id) {
        String sql = "delete from cars where car_id = "+id;

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if(results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to return size of List
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
            e.printStackTrace();
        }
        return count;
    }

    public List<Car> getAllByStatus(CarStatus status) {
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars where status = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status.name());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setId(results.getInt("car_id")).
                        setMake(results.getString("make")).
                        setModel(results.getString("model")).
                        setYear(results.getInt("year")).
                        setStatus(CarStatus.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public Car getCarIdByRole(int id, CarStatus status) {
        String sql = "select * from cars where car_id = ? and status = ?";

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
                        setStatus(CarStatus.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")));

                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  TODO :THIS IS A JOIN? RESEARCH THIS LATER
    public List<Car> getAllCarsOwnedFromASpecificUserId(int id) {
        List<Car> filteredCars = new ArrayList<>();

        for(int i = 0; i<cars.size(); i++) {
            if (cars.get(i).getUserId() == id && cars.get(i).getStatus().equals(CarStatus.PURCHASED) ) {
                filteredCars.add(cars.get(i));
            }
        }
        return filteredCars;
    }
}

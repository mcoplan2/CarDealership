package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository implements CrudDAO<Offer> {

    private List<Offer> offers;

    static Logger logger = LoggerFactory.getLogger(OfferRepository.class);

    public OfferRepository() {
        offers = new ArrayList<>();
    }

    public OfferRepository(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public Offer create(Offer offer) {
        String sql = "insert into offers(amount, offer_status_id, user_id, car_id) values(?,?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, offer.getAmount());
            statement.setInt(2, offer.getStatus().ordinal());
            statement.setInt(3, offer.getUserId());
            statement.setInt(4, offer.getCarId());

            int success = statement.executeUpdate();

            if (success == 1) {
                return offer;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Offer> getAll() {
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                offers.add(new Offer().
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.values()[(results.getInt("offer_status_id"))]).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return offers;
    }

    // GET Method
    @Override
    public Offer getById(int id) {
        String sql = "select * from offers where offer_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                Offer offer = new Offer();
                offers.add(offer.
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.values()[(results.getInt("offer_status_id"))]).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));

                return offer;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    // PUT Method
    @Override
    public Offer update(Offer offer) {
        String sql = "update offers set amount = ?, offer_status_id = ?, car_id = ?, user_id = ? where offer_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, offer.getAmount());
            statement.setInt(2, offer.getStatus().ordinal());
            statement.setInt(3, offer.getCarId());
            statement.setInt(4, offer.getUserId());
            statement.setInt(5, offer.getId());

            int success = statement.executeUpdate();

            if (success == 1) {
                return offer;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    // DELETE Method
    @Override
    public boolean deleteById(int id) {
        String sql = "delete from offers where offer_id = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();

            if(results.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return false;
    }

    // Method to return size of List
    @Override
    public int count() {
        String sql = "select count(*) from offers";
        int count = 0;
        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();


            while(results.next()){
                count = results.getInt("count");
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return count;
    }

    public List<Offer> getAllByStatus(OfferStatus status) {
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers where offer_status_id = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status.name());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                offers.add(new Offer().
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.values()[(results.getInt("offer_status_id"))]).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return offers;
    }

    public Offer getOfferIdByRole(int id, OfferStatus status) {
        String sql = "select * from offers where offer_id = ? and offer_status_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, status.name());
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                Offer offer = new Offer();
                offers.add(offer.
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.values()[(results.getInt("offer_status_id"))]).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));
                return offer;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    public List<Offer> getAllByCarId(int carId) {
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers where car_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, carId);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                offers.add(new Offer().
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.values()[(results.getInt("offer_status_id"))]).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return offers;
    }

    public List<Offer> getAllOpenOffersByUserId(int userId) {
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers where user_id = ? and offer_status_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, OfferStatus.OPEN.ordinal());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                offers.add(new Offer().
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.values()[(results.getInt("offer_status_id"))]).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));

            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return offers;
    }
}

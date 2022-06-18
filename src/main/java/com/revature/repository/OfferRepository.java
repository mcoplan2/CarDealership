package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.util.ConnectionUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository implements CrudDAO<Offer> {

    private List<Offer> offers;

    public OfferRepository() {
        offers = new ArrayList<>();
    }

    public OfferRepository(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public Offer create(Offer offer) {
        String sql = "insert into offers(amount, status, user_id) values(?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, offer.getAmount());
            statement.setString(2, offer.getStatus().name());
            statement.setInt(3, offer.getUserId());

            int success = statement.executeUpdate();

            if (success == 1) {
                return offer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
                        setStatus(OfferStatus.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

    // GET Method
    @Override
    public Offer getById(int id) {
        String sql = "select * from offers where id = "+id;

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                Offer offer = new Offer();
                offers.add(offer.
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));

                return offer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PUT Method
    @Override
    public Offer update(Offer offer) {
        String sql = "update offers set amount = ?, status = ?, car_id = ?, user_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, offer.getAmount());
            statement.setString(2, offer.getStatus().name());
            statement.setInt(3, offer.getCarId());
            statement.setInt(4, offer.getUserId());

            int success = statement.executeUpdate();

            if (success == 1) {
                return offer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // DELETE Method
    @Override
    public boolean deleteById(int id) {
        String sql = "delete from offers where id = "+id;

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
        String sql = "select count(*) from offers";
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

    public List<Offer> getAllByStatus(OfferStatus status) {
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers where status = "+status;

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                offers.add(new Offer().
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

    public Offer getOfferIdByRole(int id, OfferStatus status) {
        String sql = "select * from offers where id = "+id + "and status = "+status;

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                Offer offer = new Offer();
                offers.add(offer.
                        setId(results.getInt("offer_id")).
                        setAmount(results.getDouble("amount")).
                        setStatus(OfferStatus.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("car_id")));
                return offer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Offer> getAllOffersFromASpecificUserId(int id) {
        List<Offer> filteredOffers = new ArrayList<>();

        for(int i = 0; i<offers.size(); i++) {
            if (offers.get(i).getUserId() == id && offers.get(i).getStatus().equals(OfferStatus.OPEN)) {
                filteredOffers.add(offers.get(i));
            }
        }
        return filteredOffers;
    }

    //TODO Add a getIDIfStatusisOPEN ?
}

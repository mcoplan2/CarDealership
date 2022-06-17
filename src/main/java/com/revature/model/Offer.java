package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Offer implements Serializable {

    private double amount;
    private int id;

    private int carId;
    private int userId;
    private OfferStatus status;

    public Offer(){ }

    public Offer(int amount, int userId, OfferStatus status){
        this.amount = amount;
        this.userId = userId;
        this.status = status;

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return amount == offer.amount && id == offer.id && carId == offer.carId && userId == offer.userId && status == offer.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, id, carId, userId, status);
    }
    @Override
    public String toString() {
        return "Offer{" +
                "amount=" + amount +
                ", id=" + id +
                ", carId=" + carId +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}

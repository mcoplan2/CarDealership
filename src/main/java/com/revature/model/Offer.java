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

    public Offer setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public int getId() {
        return id;
    }

    public Offer setId(int id) {
        this.id = id;
        return this;
    }

    public int getCarId() {
        return carId;
    }

    public Offer setCarId(int carId) {
        this.carId = carId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Offer setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public Offer setStatus(OfferStatus status) {
        this.status = status;
        return this;
    }

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

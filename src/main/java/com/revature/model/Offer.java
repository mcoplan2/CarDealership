package com.revature.model;

public class Offer {
    public enum Status {
        ACCEPTED, REJECTED, OPEN;
    }

    public int Amount;
    public int id;
    public int userId;
    public Status status;

    public Offer(){}

    public Offer(int id){
        this.id = id;
    }

    public Offer(int id, int userId, Status status){
        this.id = id;
        this.userId = userId;
        this.status = status;
    }
}

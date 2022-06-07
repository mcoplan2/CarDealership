package com.revature.model;

public class Offer {
    public enum Status {
        ACCEPTED, REJECTED, OPEN;
    }

    public int Amount;
    public int id;
    public Status status;

    public Offer(){}

    public Offer(int id){
        this.id = id;
    }

    public Offer(int id, Status status){
        this.id = id;
        this.status = status;
    }
}

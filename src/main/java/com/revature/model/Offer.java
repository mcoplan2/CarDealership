package com.revature.model;

public class Offer {
    public enum Status {
        ACCEPTED, REJECTED, OPEN;
    }

    public int amount;
    public int id;
    public static int incrementId = 0;
    public int userId;
    public Status status;

    public Offer(){}

    public Offer(int id){
        this.id = id;
    }

    public Offer(int amount, Status status){
        this.id = incrementId++;
        this.amount = amount;
        this.status = status;

    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "userId: " + userId + "\n" +
                "Amount: " + amount + "\n" +
                "Status: " + status + "\n\n";
    }
}

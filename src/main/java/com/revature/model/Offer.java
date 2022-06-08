package com.revature.model;

public class Offer {
    public enum Status {
        ACCEPTED, REJECTED, OPEN;
    }

    private int amount;
    private int id;
    public static int incrementId = 0;
    private Status status;

    public Offer(){}

    public Offer(int id){
        this.id = id;
    }

    public Offer(int amount, Status status){
        this.id = incrementId++;
        this.amount = amount;
        this.status = status;

    }

    public int getAmount() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Amount: " + amount + "\n" +
                "Status: " + status + "\n\n";
    }
}

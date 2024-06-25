package com.example.purchasingscrapapp.model;

public class Ad {
    private int id;
    private int scrapId;
    private int userId;
    private double price;
    private String contact;
    private boolean isDonated;
    private long timestamp;

    public Ad() {
    }

    public Ad(int id, int scrapId, int userId, double price, String contact, boolean isDonated, long timestamp) {
        this.id = id;
        this.scrapId = scrapId;
        this.userId = userId;
        this.price = price;
        this.contact = contact;
        this.isDonated = isDonated;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScrapId() {
        return scrapId;
    }

    public void setScrapId(int scrapId) {
        this.scrapId = scrapId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isDonated() {
        return isDonated;
    }

    public void setDonated(boolean donated) {
        isDonated = donated;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

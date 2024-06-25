package com.example.purchasingscrapapp.model;

public class Order {
    private int id;
    private int buyerId;
    private int sellerId;
    private int scrapId;
    private int quantity;
    private double totalPrice;
    private String status;
    private long timestamp;

    public Order() {
    }

    public Order(int id, int buyerId, int sellerId, int scrapId, int quantity, double totalPrice, String status, long timestamp) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.scrapId = scrapId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getScrapId() {
        return scrapId;
    }

    public void setScrapId(int scrapId) {
        this.scrapId = scrapId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

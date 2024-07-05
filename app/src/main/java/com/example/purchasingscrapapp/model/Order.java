package com.example.purchasingscrapapp.model;

import java.io.Serializable;

public class Order implements Serializable {
    private String id;
    private String buyerId;
    private String sellerId;
    private String scrapId;
    private int quantity;
    private double totalPrice;
    private String status;
    private long timestamp;

    public Order() {
    }

    public Order(String id, String buyerId, String sellerId, String scrapId, int quantity, double totalPrice, String status, long timestamp) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.scrapId = scrapId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getScrapId() {
        return scrapId;
    }

    public void setScrapId(String scrapId) {
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

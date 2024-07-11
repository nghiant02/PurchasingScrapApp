package com.example.purchasingscrapapp.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class Order implements Serializable {
    private String id;
    private String buyerId;
    private String sellerId;
    private String scrapId;
    private int quantity;
    private double totalPrice;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Order() {}

    public Order(String id, String buyerId, String sellerId, String scrapId, int quantity, double totalPrice, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.scrapId = scrapId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

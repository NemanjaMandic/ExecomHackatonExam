package com.example.nemus.execomhackaton.model;

import com.example.nemus.execomhackaton.model.User;

/**
 * Created by nemus on 17-May-17.
 */

public class Item {

    private int itemId;

    private User user;
    private String name;
    private int price;
    private String description;
    private String date;

    public Item() {
    }

    public Item(String name, int price, String description, String date) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.date = date;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

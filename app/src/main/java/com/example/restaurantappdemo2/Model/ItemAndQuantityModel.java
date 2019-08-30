package com.example.restaurantappdemo2.Model;

public class ItemAndQuantityModel {

    String itemName;
    int quantity;
    int price;
    int id;

    public ItemAndQuantityModel(String itemName, int quantity, int price, int id) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
    }


    public ItemAndQuantityModel(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public ItemAndQuantityModel(String itemName, int quantity, int price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

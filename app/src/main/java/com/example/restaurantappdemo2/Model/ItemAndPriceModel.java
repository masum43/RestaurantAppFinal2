package com.example.restaurantappdemo2.Model;

public class ItemAndPriceModel {

    String itemName;
    int price;
    int ratings;

    public ItemAndPriceModel() {
    }

    public ItemAndPriceModel(String itemName, int price, int ratings) {
        this.itemName = itemName;
        this.price = price;
        this.ratings = ratings;
    }


    public ItemAndPriceModel(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

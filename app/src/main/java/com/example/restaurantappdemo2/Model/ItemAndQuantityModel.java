package com.example.restaurantappdemo2.Model;

public class ItemAndQuantityModel {

    String itemName;
    int quantity;
    int price;
    int id;
    int table_no;
    long order_no;

    public ItemAndQuantityModel(String itemName, int quantity, int price, int table_no, long order_no) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.table_no = table_no;
        this.order_no = order_no;
    }

    public ItemAndQuantityModel(String itemName, int quantity, int price, int id) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
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

    public int getTable_no() {
        return table_no;
    }

    public void setTable_no(int table_no) {
        this.table_no = table_no;
    }

    public long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }
}

package com.example.restaurantappdemo2.Model;

public class OrderListModel {

    int _id;
    String item_name;
    int quantity;
    int price;
    int table_no;
    int order_no;

    public OrderListModel(int _id, String item_name, int quantity, int price, int table_no, int order_no) {
        this._id = _id;
        this.item_name = item_name;
        this.quantity = quantity;
        this.price = price;
        this.table_no = table_no;
        this.order_no = order_no;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTable_no() {
        return table_no;
    }

    public void setTable_no(int table_no) {
        this.table_no = table_no;
    }

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }
}

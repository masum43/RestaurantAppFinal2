package com.example.restaurantappdemo2.Methods;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.Model.ItemAndQuantityModel;
import com.example.restaurantappdemo2.Model.OrderListModel;

import java.util.ArrayList;

import static com.example.restaurantappdemo2.Methods.MyDatabaseHelper.COL_ORDER_NO_ORDER_LIST;
import static com.example.restaurantappdemo2.Methods.MyDatabaseHelper.COL_TABLE_NO_ORDER_LIST;
import static com.example.restaurantappdemo2.Methods.MyDatabaseHelper.ITEM_QUANTITY_TABLE_NAME;
import static com.example.restaurantappdemo2.Methods.MyDatabaseHelper.TABLE_NAME;
import static com.example.restaurantappdemo2.Methods.MyDatabaseHelper.TABLE_NAME_3;

public class MyDatabaseSource {

    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    ItemAndPriceModel itemAndPriceModel;
    OrderListModel orderListModel;

    public MyDatabaseSource(Context context)
    {
        myDatabaseHelper = new MyDatabaseHelper(context);
    }


    public void open()
    {
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

    }


    public void close()
    {
        myDatabaseHelper.close();
    }

    public boolean addItemWithPrice(ItemAndPriceModel itemAndPriceModel)
    {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseHelper.COL_NAME,itemAndPriceModel.getItemName()); //MyDatabaseHelper e error thakte pare
        contentValues.put(MyDatabaseHelper.COL_PRICE,itemAndPriceModel.getPrice());
        contentValues.put(MyDatabaseHelper.COL_RATING,itemAndPriceModel.getRatings());

        Long insertedRow = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        this.close();

        if(insertedRow>0)
        {
            return true;
        }
        else return false;
    }



    public void updateTable1(ItemAndPriceModel itemAndPriceModel , String item_name)
    {
        this.open();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyDatabaseHelper.COL_RATING,itemAndPriceModel.getRatings());

        sqLiteDatabase.update(TABLE_NAME, contentValues,
                MyDatabaseHelper.COL_NAME + " = ? ", new String[]{item_name});

    }



    //Item Name with Quantity 2nd table insert method
    public boolean addItemWithQuantity(ItemAndQuantityModel itemAndQuantityModel)
    {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseHelper.NAME,itemAndQuantityModel.getItemName());
        contentValues.put(MyDatabaseHelper.QUANTITY,itemAndQuantityModel.getQuantity());
        contentValues.put(MyDatabaseHelper.PRICE,itemAndQuantityModel.getPrice());
        contentValues.put(COL_TABLE_NO_ORDER_LIST,itemAndQuantityModel.getTable_no());
        contentValues.put(COL_ORDER_NO_ORDER_LIST,itemAndQuantityModel.getOrder_no());

        Long insertedRow = sqLiteDatabase.insert(MyDatabaseHelper.ITEM_QUANTITY_TABLE_NAME,null,contentValues);
        this.close();

        if(insertedRow>0)
        {
            return true;
        }
        else return false;
    }


    public ArrayList<ItemAndPriceModel> getAllItemWithPrice()
    {

        this.open();
        ArrayList<ItemAndPriceModel> arrayList = new ArrayList<>();

        // select * from table
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,null, null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                String name = cursor.getString(cursor.getColumnIndex(myDatabaseHelper.COL_NAME));
                int price = cursor.getInt(cursor.getColumnIndex(myDatabaseHelper.COL_PRICE));
                int id = cursor.getInt(cursor.getColumnIndex(myDatabaseHelper.COL_ID));
                //int rating = cursor.getInt(cursor.getColumnIndex(myDatabaseHelper.COL_RATING));


                ItemAndPriceModel itemAndPriceModel = new ItemAndPriceModel(name,price); // model e datagula set korechi constructor call kore
                arrayList.add(itemAndPriceModel);
            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();

        return arrayList;

    }



    public ArrayList<ItemAndQuantityModel> getAllItemWithQuantity()
    {

        this.open();
        ArrayList<ItemAndQuantityModel> arrayList = new ArrayList<>();

        // select * from table
        Cursor cursor = sqLiteDatabase.query(ITEM_QUANTITY_TABLE_NAME,null,null, null,null,null,null,null);
        //Cursor cursor2 = sqLiteDatabase.query(TABLE_NAME,null,null, null,null,null,null,null);

        if(cursor.moveToFirst())
        {

            do{
                String name = cursor.getString(cursor.getColumnIndex(myDatabaseHelper.NAME));
                int qnty = cursor.getInt(cursor.getColumnIndex(myDatabaseHelper.QUANTITY));
                int id = cursor.getInt(cursor.getColumnIndex(myDatabaseHelper.ID));
                int price = cursor.getInt(cursor.getColumnIndex(myDatabaseHelper.PRICE));


                ItemAndQuantityModel itemAndQuantityModel = new ItemAndQuantityModel(name,qnty,price,id); // model e datagula set korechi constructor call kore
                arrayList.add(itemAndQuantityModel);
            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();

        return arrayList;

    }



    public ArrayList<OrderListModel> getAllFromStaticOrderListTable3()
    {

        this.open();
        ArrayList<OrderListModel> arrayList = new ArrayList<>();

        // select * from table
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_3,null,null, null,null,null,null,null);
        //Cursor cursor2 = sqLiteDatabase.query(TABLE_NAME,null,null, null,null,null,null,null);

        if(cursor.moveToFirst())
        {

            do{
                String name = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_NAME_ORDER_LIST));
                int qnty = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_QUANTITY_ORDER_LIST));
                int id = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_ID_ORDER_LIST));
                int price = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_PRICE_ORDER_LIST));
                int table_no = cursor.getInt(cursor.getColumnIndex(COL_TABLE_NO_ORDER_LIST));
                int order_no = cursor.getInt(cursor.getColumnIndex(COL_ORDER_NO_ORDER_LIST));


                OrderListModel orderListModel = new OrderListModel(id, name,qnty,price,table_no,order_no); // model e datagula set korechi constructor call kore
                arrayList.add(orderListModel);
            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();

        return arrayList;

    }


    //delete method
    public boolean deleteItem(OrderListModel model){


        this.open();

        int deletedRow = sqLiteDatabase.delete(TABLE_NAME_3,MyDatabaseHelper.COL_ID_ORDER_LIST+" =?",new String[]{String.valueOf(model.get_id())});

        //Log.d("Value of Id","String.valueOf(model.get_id())");

        this.close();

        if(deletedRow>0)
        {

            return true;
        }
        else return false;


    }













}

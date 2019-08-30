package com.example.restaurantappdemo2.Methods;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.Model.ItemAndQuantityModel;
import com.example.restaurantappdemo2.MyDatabaseHelper;

import java.util.ArrayList;

import static com.example.restaurantappdemo2.MyDatabaseHelper.COL_NAME;
import static com.example.restaurantappdemo2.MyDatabaseHelper.COL_PRICE;
import static com.example.restaurantappdemo2.MyDatabaseHelper.ITEM_QUANTITY_TABLE_NAME;
import static com.example.restaurantappdemo2.MyDatabaseHelper.TABLE_NAME;

public class MyDatabaseSource {

    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    ItemAndPriceModel itemAndPriceModel;

    public MyDatabaseSource(Context context)
    {
        myDatabaseHelper = new MyDatabaseHelper(context);
    }


    public void open()
    {
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

    }

    public SQLiteDatabase cursorRead()
    {
        sqLiteDatabase = myDatabaseHelper.getReadableDatabase();

        return sqLiteDatabase;
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

        Long insertedRow = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        this.close();

        if(insertedRow>0)
        {
            return true;
        }
        else return false;
    }
    //Item Name with Quantity 2nd table insert method
    public boolean addItemWithQuantity(ItemAndQuantityModel itemAndQuantityModel)
    {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseHelper.NAME,itemAndQuantityModel.getItemName());
        contentValues.put(MyDatabaseHelper.QUANTITY,itemAndQuantityModel.getQuantity());
        contentValues.put(MyDatabaseHelper.PRICE,itemAndQuantityModel.getPrice());

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

                ItemAndPriceModel itemAndPriceModel = new ItemAndPriceModel(name,price); // student model e datagula set korechi constructor call kore
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





//Showing for table 2

   /* public ArrayList<ItemAndQuantityModel> getAmount()
    {

        String amountStr;
        this.open();
        ArrayList<ItemAndQuantityModel> arrayList2 = new ArrayList<>();
        ArrayList<ItemAndPriceModel> arrayList1 = new ArrayList<>();

        // select * from student_table
        Cursor cursor2 = sqLiteDatabase.query(myDatabaseHelper.ITEM_QUANTITY_TABLE_NAME,null,null, null,null,null,null,null);
        Cursor cursor1 = sqLiteDatabase.query(myDatabaseHelper.TABLE_NAME,null,null, null,null,null,null,null);

        if(cursor2.moveToFirst()&&cursor1.moveToFirst())
        {
            do{
                String name2 = cursor2.getString(cursor2.getColumnIndex(myDatabaseHelper.NAME));
                int quantity = cursor2.getInt(cursor2.getColumnIndex(myDatabaseHelper.QUANTITY));
                int id2 = cursor2.getInt(cursor2.getColumnIndex(myDatabaseHelper.ID));


               do{
                   String name1 = cursor1.getString(cursor1.getColumnIndex(myDatabaseHelper.COL_NAME));
                   int price = cursor1.getInt(cursor1.getColumnIndex(myDatabaseHelper.COL_PRICE));
                   int id1 = cursor1.getInt(cursor1.getColumnIndex(myDatabaseHelper.COL_ID));

                   ItemAndPriceModel itemAndPriceModel = new ItemAndPriceModel(name1,price);// student model e datagula set korechi constructor call kore

                   arrayList1.add(itemAndPriceModel);

                   if(myDatabaseHelper.NAME == myDatabaseHelper.COL_NAME){

                       int amount = price * quantity;
                       ArrayList<String>amountArray = new ArrayList<>();
                       amountArray.set(String.valueOf(amount));
                       break;

                   }
                   break;
               }
               while (cursor1.moveToNext());



                ItemAndQuantityModel itemAndQuantityModel = new ItemAndQuantityModel(name2,quantity);
                arrayList2.add(itemAndQuantityModel);

            }
            while (cursor2.moveToNext());
        }
        this.close();
        cursor1.close();
        cursor2.close();

        return amount;

    }

    */






}

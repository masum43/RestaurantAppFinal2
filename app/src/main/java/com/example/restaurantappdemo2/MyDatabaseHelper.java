package com.example.restaurantappdemo2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "restaurent.db";
    public static final int DATABASE_VERSION = 2 ;

    public static final String TABLE_NAME = "all_item_table";

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "item_name";
    public static final String COL_PRICE = "price";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+" (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NAME + " TEXT NOT NUll, " +
            COL_PRICE + " INTEGER NOT NULL " + ")";


    //2nd table
    public static final String ITEM_QUANTITY_TABLE_NAME = "item_quantity_table";
    public static final String ID = "_id";
    public static final String NAME = "item_name";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";

    public static final String CREATE_TABLE_2 = "create table "+ITEM_QUANTITY_TABLE_NAME+" (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME + " TEXT NOT NUll, " +
            QUANTITY + " INTEGER NOT NULL ," +
            PRICE + " INTEGER NOT NULL " + ")";



    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NAME);
        db.execSQL("drop table if exists " + ITEM_QUANTITY_TABLE_NAME);
        this.onCreate(db);

    }

    public Cursor Showitem(MyDatabaseHelper myDatabaseHelper)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public Cursor ShowitemWithQuantity(MyDatabaseHelper myDatabaseHelper)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ITEM_QUANTITY_TABLE_NAME,null);
        return cursor;
    }


}

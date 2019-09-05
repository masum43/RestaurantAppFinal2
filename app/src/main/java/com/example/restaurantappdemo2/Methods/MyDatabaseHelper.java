package com.example.restaurantappdemo2.Methods;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "restaurent.db";
    public static final int DATABASE_VERSION = 6 ;

    public static final String TABLE_NAME = "all_item_table";

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "item_name";
    public static final String COL_PRICE = "price";
    public static final String COL_RATING = "ratings";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+" (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NAME + " TEXT NOT NUll, " +
            COL_PRICE + " INTEGER NOT NULL, " +
            COL_RATING + " INTEGER NOT NULL " +
            ")";


    //2nd table dynamic
    public static final String ITEM_QUANTITY_TABLE_NAME = "item_quantity_table";
    public static final String ID = "_id";
    public static final String NAME = "item_name";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";

    //for 2nd and 3rd table
    public static final String COL_TABLE_NO_ORDER_LIST = "table_no";
    public static final String COL_ORDER_NO_ORDER_LIST = "order_no";


    public static final String CREATE_TABLE_2 = "create table "+ITEM_QUANTITY_TABLE_NAME+" (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME + " TEXT NOT NUll, " +
            QUANTITY + " INTEGER NOT NULL ," +
            PRICE + " INTEGER NOT NULL ," +
            COL_TABLE_NO_ORDER_LIST + " INTEGER NOT NULL ," +
            COL_ORDER_NO_ORDER_LIST + " INTEGER NOT NULL " +")";

    //3rd table-static
    public static final String TABLE_NAME_3 = "order_list";

    public static final String COL_ID_ORDER_LIST = "_id";
    public static final String COL_NAME_ORDER_LIST = "item_name";
    public static final String COL_QUANTITY_ORDER_LIST = "quantity";
    public static final String COL_PRICE_ORDER_LIST = "price";
    public static final String CREATE_TABLE_3 = "create table "+TABLE_NAME_3+" (" +
            COL_ID_ORDER_LIST + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NAME_ORDER_LIST + " TEXT NOT NUll, " +
            COL_QUANTITY_ORDER_LIST + " INTEGER NOT NUll, " +
            COL_PRICE_ORDER_LIST + " INTEGER NOT NULL ," +
            COL_TABLE_NO_ORDER_LIST + " INTEGER NOT NULL ," +
            COL_ORDER_NO_ORDER_LIST + " INTEGER NOT NULL " +")";





    public static final String COPY_TABLE = "INSERT INTO " + TABLE_NAME_3+ " SELECT * FROM "+ ITEM_QUANTITY_TABLE_NAME;
    public static final String SEARCH_FOE_EDIT_ORDER = "SELECT * FROM " + TABLE_NAME_3+ " SELECT * FROM "+ ITEM_QUANTITY_TABLE_NAME;



    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_2);
        db.execSQL(CREATE_TABLE_3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NAME);
        db.execSQL("drop table if exists " + ITEM_QUANTITY_TABLE_NAME);
        db.execSQL("drop table if exists " + TABLE_NAME_3);
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
    

    public void deleteAll(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEM_QUANTITY_TABLE_NAME,null,null);
    }

    public void copyAllRowsToAnotherTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(COPY_TABLE);

    }
    public Cursor searchForEditOrder(String  table_no,String order_no, SQLiteDatabase sqLiteDatabase, MyDatabaseHelper myDatabaseHelper){

         sqLiteDatabase=this.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+  TABLE_NAME_3 + " WHERE "+  COL_TABLE_NO_ORDER_LIST +" =? AND " + COL_ORDER_NO_ORDER_LIST + " =?";
        Cursor c = sqLiteDatabase.rawQuery(selectQuery, new String[] { table_no,order_no });

        return c;

    }




}

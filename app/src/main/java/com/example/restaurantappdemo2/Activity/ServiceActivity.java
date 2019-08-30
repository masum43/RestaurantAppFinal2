package com.example.restaurantappdemo2.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantappdemo2.Adapter.MyCustomAdapter;
import com.example.restaurantappdemo2.Adapter.MyCustomAdapterForItemWithQuantity;
import com.example.restaurantappdemo2.Methods.MyDatabaseSource;
import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.Model.ItemAndQuantityModel;
import com.example.restaurantappdemo2.MyDatabaseHelper;
import com.example.restaurantappdemo2.R;

import java.io.Serializable;

public class ServiceActivity extends AppCompatActivity {

    TextView itemNameTable2,quantity,amountTv,totalAmountTv;
    Button nextBtn,submtBtn;
    MyDatabaseSource myDatabaseSource;
    MyDatabaseHelper myDatabaseHelper;
    MyDatabaseHelperForOrderList myDatabaseHelperForOrderList;
    ListView listView;
    int total = 0;

    Spinner spinner;
    String[] tableNostr;
    AlertDialog.Builder alertDialogBuilder;
    String selection;
    String[] paymentMode;

    CharSequence charSequence[] = {"Cash","Card","Others(Paytm etc)"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        itemNameTable2 = findViewById(R.id.etItemNameTable2);
        quantity = findViewById(R.id.etQuantityTable2);
        nextBtn = findViewById(R.id.nextBtnId);
        amountTv = findViewById(R.id.amountTvId);
        totalAmountTv = findViewById(R.id.totalAmountTvId);
        listView = findViewById(R.id.serviceListViewId);
        spinner = findViewById(R.id.tableNoSpinner);
        submtBtn = findViewById(R.id.submitBtnId);

        tableNostr = getResources().getStringArray(R.array.table_no);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.tvSampleId,tableNostr);
        spinner.setAdapter(adapter);


        myDatabaseSource = new MyDatabaseSource(this);
        myDatabaseHelper = new MyDatabaseHelper(this);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String item_nameStr = itemNameTable2.getText().toString();
                String quantity_Str = quantity.getText().toString();

                /*ItemAndQuantityModel itemAndQuantityModel = new ItemAndQuantityModel(item_nameStr,Integer.valueOf(quantity_Str));
                Boolean status = myDatabaseSource.addItemWithQuantity(itemAndQuantityModel);

                if (status){
                    Toast.makeText(ServiceActivity.this, "Saved Successfully.......",Toast.LENGTH_SHORT).show();
(                }
                else {
                    Toast.makeText(ServiceActivity.this, "Failed to Save.......",Toast.LENGTH_SHORT).show();
                } */


                //Cursor cursor = (Cursor)myDatabaseSource.displayAllItem(myDatabaseHelper);
                Cursor cursor = myDatabaseHelper.Showitem(myDatabaseHelper);
                if(item_nameStr.equals("")||quantity_Str.equals("")){
                    Toast.makeText(ServiceActivity.this, "Please type first......", Toast.LENGTH_SHORT).show();
                }
                else {
                    /*if (cursor.getCount() == 0) {
                        Toast.makeText(ServiceActivity.this, "Such an item is not found", Toast.LENGTH_SHORT).show();
                    } */


                        while (cursor.moveToNext()){
                            if(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_NAME)).equals(item_nameStr)){

                                int price = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_PRICE));
                                int price_quantity = price*Integer.valueOf(quantity_Str);
                                ItemAndQuantityModel itemAndQuantityModel = new ItemAndQuantityModel(item_nameStr,Integer.valueOf(quantity_Str),price_quantity);
                                Boolean status = myDatabaseSource.addItemWithQuantity(itemAndQuantityModel);

                                if (status){
                                    Toast.makeText(ServiceActivity.this, "Saved Successfully.......",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(ServiceActivity.this, "Failed to Save.......",Toast.LENGTH_SHORT).show();
                                }


                                //int price = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_PRICE));
                                //int qnty = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.QUANTITY));
                                int qnty = Integer.parseInt(quantity_Str);

                                int amount = price*qnty;
                                String amountStr = String.valueOf(amount);
                                amountTv.setText(amountStr);

                                        total = total + amount;
                                        String total_price = String.valueOf(total);
                                        totalAmountTv.setText(total_price);

                                MyDatabaseSource source = new MyDatabaseSource(ServiceActivity.this);
                                /*MyCustomAdapter myCustomAdapter = new MyCustomAdapter(getApplicationContext(),source.getAllItemWithPrice());
                                listView.setAdapter(myCustomAdapter); */
                                MyCustomAdapterForItemWithQuantity myCustomAdapterForItemWithQuantity = new MyCustomAdapterForItemWithQuantity(ServiceActivity.this,source.getAllItemWithQuantity());
                                listView.setAdapter(myCustomAdapterForItemWithQuantity);

                            }
                        }
                    }
                }

        });

        submtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialogBuilder = new AlertDialog.Builder(ServiceActivity.this);

                //titlr
                alertDialogBuilder.setTitle("Payment Mode :");

                //alertDialogBuilder.setMessage("Payment Mode : ");

                paymentMode = getResources().getStringArray(R.array.payment_mode);
                alertDialogBuilder.setSingleChoiceItems(paymentMode, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selection = paymentMode[which];
                                //dialog.dismiss();
                    }
                });

                //alertDialogBuilder.setIcon()

                alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        openDialog();
                    }
                });

                alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ServiceActivity.this, ServiceActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = alertDialogBuilder.create();
                alertDialogBuilder.show();

            }
        });


    }

    private void openDialog() {

    }
}























class MyDatabaseHelperForOrderList extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME_ORDER_LIST = "order_list.db";
    public static final int DATABASE_VERSION_ORDER_LIST = 1 ;

    int i;
    public static final String TABLE_NAME_ORDER_LIST = "order_list";

    public static final String COL_ID_ORDER_LIST = "_id";
    public static final String COL_NAME_ORDER_LIST = "item_name";
    public static final String COL_QUANTITY_ORDER_LIST = "quantity";
    public static final String COL_PRICE_ORDER_LIST = "price";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME_ORDER_LIST+" (" +
            COL_ID_ORDER_LIST + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NAME_ORDER_LIST + " TEXT NOT NUll, " +
            COL_QUANTITY_ORDER_LIST + " INTEGER NOT NUll, " +
            COL_PRICE_ORDER_LIST + " INTEGER NOT NULL " + ")";


    //2nd table
    /*public static final String ITEM_QUANTITY_TABLE_NAME = "item_quantity_table";
    public static final String ID = "_id";
    public static final String NAME = "item_name";
    public static final String QUANTITY = "quantity";

    public static final String CREATE_TABLE_2 = "create table "+ITEM_QUANTITY_TABLE_NAME+" (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME + " TEXT NOT NUll, " +
            QUANTITY + " INTEGER NOT NULL " + ")"; */



    public MyDatabaseHelperForOrderList(Context context) {
        super(context, DATABASE_NAME_ORDER_LIST, null, DATABASE_VERSION_ORDER_LIST);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        //db.execSQL(CREATE_TABLE_2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NAME_ORDER_LIST);
        //db.execSQL("drop table if exists " + ITEM_QUANTITY_TABLE_NAME);
        this.onCreate(db);

    }

    public Cursor ShowOrderList(com.example.restaurantappdemo2.MyDatabaseHelperForOrderList myDatabaseHelper)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME_ORDER_LIST,null);
        return cursor;
    }

   /* public Cursor ShowitemWithQuantity(MyDatabaseHelperForOrderList myDatabaseHelper)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ITEM_QUANTITY_TABLE_NAME,null);
        return cursor;
    } */


}

package com.example.restaurantappdemo2.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.restaurantappdemo2.Adapter.MyCustomAdapterForItemWithQuantity;
import com.example.restaurantappdemo2.Methods.MyDatabaseSource;
import com.example.restaurantappdemo2.Model.ItemAndQuantityModel;
import com.example.restaurantappdemo2.Methods.MyDatabaseHelper;
import com.example.restaurantappdemo2.R;

public class ServiceActivity extends AppCompatActivity {

    TextView itemNameTable2,quantity,amountTv,totalAmountTv;
    Button nextBtn,submtBtn;
    MyDatabaseSource myDatabaseSource;
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    int total = 0;
    long order_nb_change;

    Spinner spinner;
    Spinner oderNoSpinner;
    String[] tableNostr;
    String[] orderNostr;
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
        oderNoSpinner = findViewById(R.id.orderNoSpinner);
        submtBtn = findViewById(R.id.submitBtnId);


        tableNostr = getResources().getStringArray(R.array.table_no);
        orderNostr = getResources().getStringArray(R.array.order_no);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.tvSampleId,tableNostr);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.tvSampleId,orderNostr);
        oderNoSpinner.setAdapter(adapter2);


        myDatabaseSource = new MyDatabaseSource(this);
        myDatabaseHelper = new MyDatabaseHelper(this);



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String item_nameStr = itemNameTable2.getText().toString();
                String quantity_Str = quantity.getText().toString();
                int table_no = Integer.parseInt(spinner.getSelectedItem().toString());
                long order_no = Long.parseLong(oderNoSpinner.getSelectedItem().toString());
                //order_nb_change = order_no;


                Cursor cursor = myDatabaseHelper.Showitem(myDatabaseHelper);
                if(item_nameStr.equals("")||quantity_Str.equals("")){
                    Toast.makeText(ServiceActivity.this, "Please type first......", Toast.LENGTH_SHORT).show();
                }
                else {

                        while (cursor.moveToNext()){
                            if(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_NAME)).equals(item_nameStr)){

                                int price = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_PRICE));
                                int price_quantity = price*Integer.valueOf(quantity_Str);
                                ItemAndQuantityModel itemAndQuantityModel = new ItemAndQuantityModel(item_nameStr,Integer.valueOf(quantity_Str),price_quantity,table_no,order_no);
                                Boolean status = myDatabaseSource.addItemWithQuantity(itemAndQuantityModel);

                                if (status){
                                    Toast.makeText(ServiceActivity.this, "Saved Successfully.......",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(ServiceActivity.this, "Failed to Save.......",Toast.LENGTH_SHORT).show();
                                }


                                int qnty = Integer.parseInt(quantity_Str);

                                int amount = price*qnty;
                                String amountStr = String.valueOf(amount);
                                amountTv.setText(amountStr);

                                        total = total + amount;
                                        String total_price = String.valueOf(total);
                                        totalAmountTv.setText(total_price);

                                MyDatabaseSource source = new MyDatabaseSource(ServiceActivity.this);
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

                    }
                });


                /*alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        myDatabaseHelper.copyAllRowsToAnotherTable();

                        myDatabaseHelper.deleteAll();
                        Intent intent = new Intent(ServiceActivity.this, ServiceActivity.class);
                        startActivity(intent);
                        //openDialog();


                    }
                }); */



                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ServiceActivity.this,AccountActiity2.class);
                        intent.putExtra("Total_Amount",String.valueOf(total));
                        Toast.makeText(ServiceActivity.this, ""+total, Toast.LENGTH_SHORT).show();
                        showaccount();
                    }

                    private void showaccount() {

                        Account_Class_Activity account_class_activity=new Account_Class_Activity();
                        Bundle bundle = new Bundle();
                        bundle.putString("TOTAL", String.valueOf(total));
                        account_class_activity.setArguments(bundle);
                        account_class_activity.show((ServiceActivity.this).getSupportFragmentManager(),"Account Class Activity");


                        //account_class_activity.show(getSupportFragmentManager(),"Account Class Activity");
                    }
                });



                alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDatabaseHelper.copyAllRowsToAnotherTable();
                        myDatabaseHelper.deleteAll();

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


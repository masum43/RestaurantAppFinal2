package com.example.restaurantappdemo2.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.restaurantappdemo2.R;

public class MainActivity extends AppCompatActivity {

    ImageView addItem,searchItem,newItem,mEditOrderId,mItemReview,mReportImageViewId;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItem = findViewById(R.id.addItemImageViewId);
        searchItem = findViewById(R.id.searchItemImageViewId);
        newItem = findViewById(R.id.newOrderImageViewId);
        mEditOrderId = findViewById(R.id.editOrderId);
        mItemReview = findViewById(R.id.itemReviewImageViewId);
        mReportImageViewId = findViewById(R.id.reportImageViewId);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddItemActivity = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intentAddItemActivity);
                finish();
            }
        });

            searchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentSearchItemActivity = new Intent(MainActivity.this, SearchItemActivity.class);
                    startActivity(intentSearchItemActivity);
                    finish();
                }
            });

            newItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                    //titlr
                    //alertDialogBuilder.setTitle("Select type of Service");

                    alertDialogBuilder.setMessage("Select type of Service");

                    //alertDialogBuilder.setIcon()

                    alertDialogBuilder.setPositiveButton("PARCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(MainActivity.this, "Parcel", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alertDialogBuilder.setNegativeButton("SERVICE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    alertDialogBuilder.setNeutralButton("HOME DELIVERY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "HOME DELIVERY", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alertDialogBuilder.show();


                }
            });

            mEditOrderId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, EditOrderActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            mItemReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ItemReviewActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            mReportImageViewId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                    finish();

                }
            });






    }
}

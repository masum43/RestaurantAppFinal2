package com.example.restaurantappdemo2.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurantappdemo2.Methods.MyDatabaseHelper;
import com.example.restaurantappdemo2.Methods.MyDatabaseSource;
import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.R;

import java.util.ArrayList;

public class ItemReviewActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    MyDatabaseHelper myDatabaseHelper;
    //ItemAndPriceModel itemAndPriceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_review);


        listView = findViewById(R.id.listview);

        arrayList = new ArrayList<>();
        myDatabaseHelper = new MyDatabaseHelper(this);
        //itemAndPriceModel = new ItemAndPriceModel();

        Cursor cursor = myDatabaseHelper.Showitem(myDatabaseHelper);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No item is found", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                arrayList.add((cursor.getString(cursor.getColumnIndex(myDatabaseHelper.NAME)) + "\t\t\t\t\t") + "\t\t\t\t\t" + cursor.getString(cursor.getColumnIndex(myDatabaseHelper.COL_PRICE)) + "\t\t\t\t\t" +"Ratings : "+ cursor.getString(cursor.getColumnIndex(myDatabaseHelper.COL_RATING)));
            }
            arrayAdapter = new ArrayAdapter(this, R.layout.sample_view, R.id.tvSampleId, arrayList);
            listView.setAdapter(arrayAdapter);
        }




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MyDatabaseSource myDatabaseSource = new MyDatabaseSource(getApplicationContext());
                ArrayList<ItemAndPriceModel> itemNameArray = myDatabaseSource.getAllItemWithPrice();
                String itemNameStr = itemNameArray.get(position).getItemName();
                String priceStr = String.valueOf(itemNameArray.get(position).getPrice());


                Intent intent = new Intent(ItemReviewActivity.this,SingleItemReviewActivity.class);

                intent.putExtra("Item_Name",itemNameStr);
                intent.putExtra("price",priceStr);

                startActivity(intent);
                finish();

            }
        });

    }
}

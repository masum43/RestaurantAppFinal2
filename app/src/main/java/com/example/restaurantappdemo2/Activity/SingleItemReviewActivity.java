package com.example.restaurantappdemo2.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.restaurantappdemo2.Methods.MyDatabaseSource;
import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.R;

public class SingleItemReviewActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView textView,textView2;
    String getItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_review);

        ratingBar = findViewById(R.id.ratingBarId);
        textView = findViewById(R.id.ratingTvId);
        textView2 = findViewById(R.id.getItemNameTvId);

        getItemName = getIntent().getStringExtra("Item_Name");
        textView2.setText(getItemName);

        //textView.setText("Value : "+ratingBar.getProgress());
        ratingBar.setRating(3);
        textView.setText("Value : "+ratingBar.getProgress());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {


                String itemNameStr = getIntent().getStringExtra("Item_Name");

                MyDatabaseSource myDatabaseSource = new MyDatabaseSource(getApplicationContext());
                ItemAndPriceModel itemAndPriceModel = new ItemAndPriceModel();

                int value = (int) v;
                itemAndPriceModel.setRatings(value);

                myDatabaseSource.updateTable1(itemAndPriceModel,itemNameStr);

                textView.setText("Value : "+v);

                Intent intent = new Intent(SingleItemReviewActivity.this,ItemReviewActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }
}

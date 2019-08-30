package com.example.restaurantappdemo2.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.restaurantappdemo2.Methods.MyDatabaseSource;
import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.R;

public class AddItemActivity extends AppCompatActivity {

    String[] activeStatus;
    Spinner spinner;

    EditText etItemName , etPrice;
    Button addItemBtn;
    MyDatabaseSource myDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        activeStatus = getResources().getStringArray(R.array.active_status);
        spinner = findViewById(R.id.activeStatusSpinnerId);

        etItemName = findViewById(R.id.etItemName);
        etPrice = findViewById(R.id.etPrices);
        addItemBtn = findViewById(R.id.addItem);

        myDatabaseSource = new MyDatabaseSource(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.tvSampleId,activeStatus);
        spinner.setAdapter(adapter);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemAndPriceModel itemAndPriceModel = new ItemAndPriceModel(etItemName.getText().toString(),Integer.valueOf(etPrice.getText().toString()));
                Boolean status = myDatabaseSource.addItemWithPrice(itemAndPriceModel);

                if (status){
                    Toast.makeText(AddItemActivity.this, "Saved Successfully.......",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddItemActivity.this, "Failed to Save.......",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}

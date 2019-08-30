package com.example.restaurantappdemo2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.R;

import java.util.ArrayList;



public class MyCustomAdapter extends ArrayAdapter<ItemAndPriceModel> {

    Context context;
    ArrayList<ItemAndPriceModel> arrayList;

    public MyCustomAdapter(@NonNull Context context, ArrayList<ItemAndPriceModel>arrayList) {
        super(context, R.layout.single_row_item_price,arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.single_row_item_price,parent,false);
        TextView nameTV = v.findViewById(R.id.itemNameSingleId);
        TextView priceTV = v.findViewById(R.id.priceSingleId);

        nameTV.setText(arrayList.get(position).getItemName());
        priceTV.setText(String.valueOf(arrayList.get(position).getPrice()));


        return v;
    }
}

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
import com.example.restaurantappdemo2.Model.ItemAndQuantityModel;
import com.example.restaurantappdemo2.R;
import java.util.ArrayList;


public class MyCustomAdapterForItemWithQuantity extends ArrayAdapter<ItemAndQuantityModel> {

    Context context;
    ArrayList<ItemAndQuantityModel> arrayList2;
    ArrayList<ItemAndPriceModel> arrayList;

    public MyCustomAdapterForItemWithQuantity(@NonNull Context context, ArrayList<ItemAndQuantityModel>arrayList2) {
        super(context, R.layout.single_row_sn_item_quantity_price,arrayList2);
        this.context = context;
        this.arrayList2 = arrayList2;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.single_row_sn_item_quantity_price,parent,false);
        TextView nameTV = v.findViewById(R.id.itemNameSingleId2);
        TextView quantityTV = v.findViewById(R.id.quantityTvSingle2);
        TextView priceTV = v.findViewById(R.id.priceSingleId2);
        TextView snTv = v.findViewById(R.id.snTvIdSingle2);

        nameTV.setText(arrayList2.get(position).getItemName().trim());
        quantityTV.setText(String.valueOf(arrayList2.get(position).getQuantity()));
        priceTV.setText(String.valueOf(arrayList2.get(position).getPrice()));
        snTv.setText(String.valueOf(arrayList2.get(position).getId()));


        return v;
    }
}


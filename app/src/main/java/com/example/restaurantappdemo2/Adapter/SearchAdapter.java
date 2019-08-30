package com.example.restaurantappdemo2.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.R;

import java.util.ArrayList;

class SearchViewHolder extends RecyclerView.ViewHolder{

    public TextView searchItemName , searchPrice;
    public SearchViewHolder(View itemView) {
        super(itemView);

        searchItemName = itemView.findViewById(R.id.searchItemImageViewId);
        searchPrice = itemView.findViewById(R.id.priceSingleId);
    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    Context context;
    ArrayList<ItemAndPriceModel> arrayList;

    public SearchAdapter(Context context, ArrayList<ItemAndPriceModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.searching_single_layout,parent,false);

        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        holder.searchItemName.setText(arrayList.get(position).getItemName());
        holder.searchPrice.setText(arrayList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

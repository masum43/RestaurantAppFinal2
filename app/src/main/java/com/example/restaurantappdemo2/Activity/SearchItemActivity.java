package com.example.restaurantappdemo2.Activity;

import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.restaurantappdemo2.Methods.MyDatabaseHelper;
import com.example.restaurantappdemo2.R;
import java.util.ArrayList;


public class SearchItemActivity extends AppCompatActivity {


    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        listView = findViewById(R.id.Listview);

        arrayList = new ArrayList<>();
        myDatabaseHelper = new MyDatabaseHelper(this);

        Cursor cursor = myDatabaseHelper.Showitem(myDatabaseHelper);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No item is found", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                arrayList.add((cursor.getString(cursor.getColumnIndex(myDatabaseHelper.NAME)) + "\t\t\t\t\t\t\t\t") + "\t\t\t\t\t\t\t\t" + cursor.getString(cursor.getColumnIndex(myDatabaseHelper.COL_PRICE)));
            }
            arrayAdapter = new ArrayAdapter(this, R.layout.sample_view, R.id.tvSampleId, arrayList);
            listView.setAdapter(arrayAdapter);
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        MenuItem menuItem=menu.findItem(R.id.item_search);
        android.support.v7.widget.SearchView searchView=(android.support.v7.widget.SearchView)MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> list=new ArrayList<>();
                for(String user:arrayList){
                    if(user.toLowerCase().contains(newText.toLowerCase())){
                        list.add(user);
                    }
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchItemActivity.this,R.layout.sample_view,R.id.tvSampleId,list);

                listView.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}

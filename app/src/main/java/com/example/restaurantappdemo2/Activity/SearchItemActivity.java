package com.example.restaurantappdemo2.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurantappdemo2.Methods.MyDatabaseSource;
import com.example.restaurantappdemo2.Model.ItemAndPriceModel;
import com.example.restaurantappdemo2.Adapter.MyCustomAdapter;
import com.example.restaurantappdemo2.MyDatabaseHelper;
import com.example.restaurantappdemo2.R;

import java.util.ArrayList;

import static com.example.restaurantappdemo2.MyDatabaseHelper.COL_NAME;
import static com.example.restaurantappdemo2.MyDatabaseHelper.TABLE_NAME;

public class SearchItemActivity extends AppCompatActivity {

    //ListView listView;
    AutoCompleteTextView autoCompleteTextView;
    EditText editText;
    SQLiteDatabase sqLiteDatabase;

    //ArrayAdapter<ItemAndPriceModel>arrayAdapter;
    MyCustomAdapter myCustomAdapter;
    MyDatabaseSource source;

    //new process
    //ArrayList<String> listItem;
    //ArrayAdapter adapter;


    //newm
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        listView = findViewById(R.id.Listview);

        /*source = new MyDatabaseSource(this);
        myCustomAdapter = new MyCustomAdapter(this,source.getAllItemWithPrice());
        listView.setAdapter(myCustomAdapter); */

        //newm
        arrayList=new ArrayList<>();
        myDatabaseHelper=new MyDatabaseHelper(this);

        Cursor cursor=myDatabaseHelper.Showitem(myDatabaseHelper);
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No item is found",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(cursor.getColumnIndex(myDatabaseHelper.NAME))+"\t\t\t\t"+cursor.getString(cursor.getColumnIndex(myDatabaseHelper.COL_PRICE)));
            }
            arrayAdapter=new ArrayAdapter(this,R.layout.sample_view,R.id.tvSampleId,arrayList);
            listView.setAdapter(arrayAdapter);
        }








        //new process

        //listItem = new ArrayList<>();
        //viewData();

        /*Cursor cursor;
        cursor = sqLiteDatabase.query("SELECT* FROM "+ TABLE_NAME + "WHERE "+ COL_NAME " = "+editText.getText().toString()); */

       /* autoCompleteTextView = findViewById(R.id.autoCompleteTextviewId);

        //arrayAdapter = new ArrayAdapter(this, R.layout.single_row_item_price,source.getAllItemWithPrice());

        autoCompleteTextView.setAdapter(myCustomAdapter);
        autoCompleteTextView.setThreshold(1); */
    }

    /*private void viewData() {

        MyDatabaseSource source = new MyDatabaseSource(this);
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        Cursor cursor = source.displayAllItem(myDatabaseHelper);

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(cursor.getColumnIndex(myDatabaseHelper.NAME)));
            }

            adapter = new ArrayAdapter<>(this,R.layout.single_row_item_price,listItem);
            listView.setAdapter(adapter);
        }
    }  */

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<String> usersList = new ArrayList<>();

                for(String user : source.getAllItemWithPrice()){

                    if(user.toLowerCase().contains(s.toLowerCase()))
                    {
                        usersList.add(user);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchItemActivity.this,
                        R.layout.single_row_item_price,usersList);

                listView.setAdapter(adapter);

                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    } */

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

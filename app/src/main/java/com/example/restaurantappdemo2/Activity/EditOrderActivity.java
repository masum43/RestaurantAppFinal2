package com.example.restaurantappdemo2.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurantappdemo2.Methods.MyDatabaseSource;
import com.example.restaurantappdemo2.Model.OrderListModel;
import com.example.restaurantappdemo2.Methods.MyDatabaseHelper;
import com.example.restaurantappdemo2.R;

import java.util.ArrayList;

public class EditOrderActivity extends AppCompatActivity {

    EditText mTableNoEt,mOrderNoEt;
    Button mEditBtn;
    ListView mEditListview;
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    ArrayList<OrderListModel>deleteArrayList;
    MyDatabaseSource myDatabaseSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        mTableNoEt = findViewById(R.id.tableNoEtId);
        mOrderNoEt = findViewById(R.id.orderNoEtId);

        mEditListview = findViewById(R.id.editListViewId);


        mEditBtn = findViewById(R.id.editBtnId);
        myDatabaseHelper = new MyDatabaseHelper(this);
        myDatabaseSource = new MyDatabaseSource(this);

        //To delete single item now need to register listview with context menu
        registerForContextMenu(mEditListview);

        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList=new ArrayList<>();
                myDatabaseHelper=new MyDatabaseHelper(getApplicationContext());
                String table_no_str = mTableNoEt.getText().toString();
                String order_no_str = mOrderNoEt.getText().toString();

                Log.d("Tab No",table_no_str);

                Cursor cursor=myDatabaseHelper.searchForEditOrder(table_no_str,order_no_str,sqLiteDatabase,myDatabaseHelper);
                if(cursor.getCount()==0){
                    Toast.makeText(getApplicationContext(),"No item is found",Toast.LENGTH_LONG).show();
                }else {
                    while (cursor.moveToNext()){
                        arrayList.add(cursor.getString(cursor.getColumnIndex(myDatabaseHelper.COL_NAME_ORDER_LIST))+"\t\t\t\t"+cursor.getString(cursor.getColumnIndex(myDatabaseHelper.COL_PRICE_ORDER_LIST)));
                    }
                    arrayAdapter=new ArrayAdapter(getApplicationContext(),R.layout.sample_view,R.id.tvSampleId,arrayList);
                    mEditListview.setAdapter(arrayAdapter);
                }
            }
        });
    }




    //to delete single item now need to override onCreateContextMenu method and onContextItemSelected method


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu2,menu);
        //menu.setHeaderTitle("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        //To get the selected position... listview ba gridview k context er sathe reg na korle listview.getselectedItemPosion(); method er maddhome o position get kora jeto
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.deleteItemId){
            deleteArrayList = myDatabaseSource.getAllFromStaticOrderListTable3();
            //Toast.makeText(this, "Error :  "+deleteArrayList, Toast.LENGTH_LONG).show();


                Boolean status = myDatabaseSource.deleteItem(deleteArrayList.get(adapterContextMenuInfo.position));



                if(status){
                    Toast.makeText(this, "Deleted Successfully.....", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "To see the change type Show Button again.....", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(EditOrderActivity.this,EditOrderActivity.class);
                    //startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Failed to delete.......", Toast.LENGTH_SHORT).show();
                }

            }

        return super.onContextItemSelected(item);

    }












}

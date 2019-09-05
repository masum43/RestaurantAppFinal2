package com.example.restaurantappdemo2.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantappdemo2.R;

class AccountActiity2 extends AppCompatActivity {

    String total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_actiity);


        total = getIntent().getStringExtra("Total_Amount");



    }
}

public class AccountActiity extends AppCompatDialogFragment {
    private EditText other_charge,discount,grandEt;
    TextView totalEt;
    AccountActiity2 accountActiity2 = new AccountActiity2();
    //   private AccountClassListener accountClassListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();

        View view=layoutInflater.inflate(R.layout.account_layout,null);
        totalEt = view.findViewById(R.id.total_id);


        totalEt.setText(accountActiity2.total);

        builder.setView(view).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"Order is not successfully",Toast.LENGTH_LONG).show();
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String other_char=other_charge.getText().toString();
                String discount_char=discount.getText().toString();
                totalEt.setText(accountActiity2.total);
                // accountClassListener.sureorder(other_char,discount_char);
                Toast.makeText(getContext(),"Order is successfully"+ accountActiity2.total,Toast.LENGTH_LONG).show();

            }
        });
        other_charge=view.findViewById(R.id.other_charge_id);
        discount=view.findViewById(R.id.discount_id);
        totalEt = view.findViewById(R.id.total_id);


        totalEt.setText(accountActiity2.total);
        return builder.create();
    }

    public String getTotal(String totalGetFromIntent) {


        return totalGetFromIntent;



    }
}

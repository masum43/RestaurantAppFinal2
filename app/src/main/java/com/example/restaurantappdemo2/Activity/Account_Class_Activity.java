package com.example.restaurantappdemo2.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.restaurantappdemo2.R;

public class Account_Class_Activity extends AppCompatDialogFragment {
    private EditText other_charge,discount;
    TextView totalTv,grandTv;
 //   private AccountClassListener accountClassListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();

        View view=layoutInflater.inflate(R.layout.account_layout,null);



        other_charge=view.findViewById(R.id.other_charge_id);
        discount=view.findViewById(R.id.discount_id);
        totalTv = view.findViewById(R.id.total_id);
        grandTv = view.findViewById(R.id.grand_id);

        Bundle bundle = getArguments();
        String total = bundle.getString("TOTAL");

        String other_char=other_charge.getText().toString();
        String discount_char=discount.getText().toString();


        grandTv.setText(String.valueOf(total));

        /*if(other_char.equals(null) && discount_char.equals(null))
            grandTv.setText(String.valueOf(total));
        else {

            int other_chargeInt = Integer.parseInt(other_char);
            int discount_chargeInt = Integer.parseInt(discount_char);
            int totalInt = Integer.parseInt(total);

            totalInt = totalInt+other_chargeInt-discount_chargeInt;
            grandTv.setText(String.valueOf(totalInt));
        } */




        totalTv.setText(total);


        builder.setView(view).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"Order is not successfully",Toast.LENGTH_LONG).show();
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

               // accountClassListener.sureorder(other_char,discount_char);
                Toast.makeText(getContext(),"Order is successfully",Toast.LENGTH_LONG).show();
            }
        });


        //totalEt.setText();
        return builder.create();
    }

    public String getTotal(String totalGetFromIntent) {


        return totalGetFromIntent;



    }
}



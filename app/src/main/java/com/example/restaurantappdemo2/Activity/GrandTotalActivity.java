package com.example.restaurantappdemo2.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.restaurantappdemo2.R;

public class GrandTotalActivity extends AppCompatDialogFragment {
    private EditText other_charge,discount;
    TextView totalTv,grandTv;
    int finalTotalInt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();

        View view=layoutInflater.inflate(R.layout.account_grand_total,null);



        other_charge=view.findViewById(R.id.other_charge_id);
        discount=view.findViewById(R.id.discount_id);
        totalTv = view.findViewById(R.id.total_id);
        grandTv = view.findViewById(R.id.grand_id);

        Bundle bundle = getArguments();
        String total = bundle.getString("TOTAL");

        String other_char=other_charge.getText().toString();
        String discount_char=discount.getText().toString();


            grandTv.setText(String.valueOf(total));

            int other_chargeInt = Integer.parseInt(other_char);
            int discount_chargeInt = Integer.parseInt(discount_char);
            int totalInt = Integer.parseInt(total);

            finalTotalInt = totalInt+other_chargeInt-discount_chargeInt;
            grandTv.setText(String.valueOf(finalTotalInt));

            totalTv.setText(total);



        builder.setView(view).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"Order is not successfully",Toast.LENGTH_LONG).show();
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getContext(),"Order is successfully, You have to pay only "+ grandTv.getText().toString()+" Tk",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), ServiceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);



            }
        });

        return builder.create();
    }

}



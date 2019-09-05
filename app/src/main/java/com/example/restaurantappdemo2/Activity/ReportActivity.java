package com.example.restaurantappdemo2.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.restaurantappdemo2.R;

public class ReportActivity extends AppCompatActivity {

    EditText mEtTo,mEtSub,mEtMessage;
    Button mBtSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //mEtTo = findViewById(R.id.et_to_id);
        mEtSub = findViewById(R.id.et_sub_id);
        mEtMessage = findViewById(R.id.et_message_id);
        mBtSend = findViewById(R.id.bt_send);

        mBtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"masum.abir43@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT,mEtSub.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,mEtMessage.getText().toString());
                startActivity(intent);
            }
        });
    }
}

package com.automatic.telling.machine.transactions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.Toast;

import com.automatic.telling.machine.CashDetails;
import com.automatic.telling.machine.Operator_Login;
import com.automatic.telling.machine.R;

public class Enquiry extends AppCompatActivity {
Button receiptBtn,updatePassbookBtn;
    String userPin,userAccount,atmCash,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userPin = extras.getString("userPIN");
            userAccount=extras.getString("userAccount");
            atmCash = extras.getString("atmCash");
            amount=extras.getString("amount");
        }
        receiptBtn = (Button)findViewById(R.id.receiptbtn);
        updatePassbookBtn = (Button)findViewById(R.id.updatePassbookbtn);
        receiptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Enquiry.this,"Receipt Printed"+"User Pin:"+userPin+"Amount:",Toast.LENGTH_LONG);
            }
        });
        updatePassbookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Enquiry.this,UpdatePassbook.class);
                Toast.makeText(Enquiry.this,"Enter Your Passbook",Toast.LENGTH_LONG);
                i.putExtra("userPIN",userPin);
                i.putExtra("userAccount",userAccount);
                startActivity(i);



            }
        });




    }
}

package com.automatic.telling.machine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserLogin extends AppCompatActivity {
Button userloginbtn;
    String atmCash;
    int atmCashint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            atmCash = extras.getString("atmCash");

        }



        Toast.makeText(UserLogin.this,"Please Insert Your Card",Toast.LENGTH_SHORT).show();

        userloginbtn = (Button) findViewById(R.id.userlogin);
        userloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserLogin.this,UserPinCheck.class);
                i.putExtra("atmCash",atmCash);
                startActivity(i);
            }
        });
    }
}
/*
 if (atmCash != null && !atmCash.isEmpty()) {
            atmCash = atmCash.replaceAll(" ", "_");
            atmCashint = Integer.parseInt(atmCash);
            DatabaseReference mDatabaseReference;
            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("OPERATOR").child("OPERATORDETAILS").child("key").child("atmCash");
            mDatabaseReference.setValue(atmCashint);

        } else {
            Toast.makeText(UserLogin.this, "amount cannot be empty", Toast.LENGTH_SHORT).show();

        }
 */
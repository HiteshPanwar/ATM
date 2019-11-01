package com.automatic.telling.machine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


import com.automatic.telling.machine.adapter.RecyclerAdapterUpdatePassbook;
import com.automatic.telling.machine.models.User;
import com.automatic.telling.machine.transactions.Deposit;
import com.automatic.telling.machine.transactions.Enquiry;
import com.automatic.telling.machine.transactions.PinChange;
import com.automatic.telling.machine.transactions.Transfer;
import com.automatic.telling.machine.transactions.Withdrawl;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    Button withdrawl,deposit,transfer,enquiry,pinChange;
    String userPIN,userAccount,atmCash,amount,amount1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userPIN = extras.getString("userPIN");
            userAccount=extras.getString("userAccount");
            atmCash = extras.getString("atmCash");


        }

        mDatabaseReference=  database.getReference("ATM").child("USER").child(userAccount);

        mDatabaseReference.limitToFirst(1)
                .orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // StringBuffer stringbuffer = new StringBuffer();
                        for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){


                             amount = dataSnapshot1.child("amount").getValue().toString();

                            // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });



        /*
        DatabaseReference mDatabaseReference;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("OPERATOR").child("OPERATORDETAILS").child("key").child("atmCash");
        mDatabaseReference.setValue(atmCash);*/
        withdrawl = (Button) findViewById(R.id.withdrawl);
        withdrawl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,Withdrawl.class);
                intent.putExtra("userPIN",userPIN);
                intent.putExtra("userAccount",userAccount);
                intent.putExtra("atmCash",atmCash);

                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });
        deposit = (Button) findViewById(R.id.deposit);
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,Deposit.class);
                intent.putExtra("userPIN",userPIN);
                intent.putExtra("userAccount",userAccount);
                intent.putExtra("atmCash",atmCash);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });
        transfer = (Button) findViewById(R.id.tranfer);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,Transfer.class);
                intent.putExtra("userPIN",userPIN);
                intent.putExtra("userAccount",userAccount);
                intent.putExtra("atmCash",atmCash);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });
        enquiry = (Button) findViewById(R.id.enquiry);
        enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,Enquiry.class);
                intent.putExtra("userPIN",userPIN);
                intent.putExtra("userAccount",userAccount);
                intent.putExtra("atmCash",atmCash);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });
        pinChange = (Button) findViewById(R.id.pinChange);
        pinChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,PinChange.class);
                intent.putExtra("userPIN",userPIN);
                intent.putExtra("userAccount",userAccount);
                intent.putExtra("atmCash",atmCash);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });


    }
}

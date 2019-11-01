package com.automatic.telling.machine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserPinCheck extends AppCompatActivity {

    List<String> list;
    int number=0,temp=0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();
    String   userAccount,atmCash,amount;
    Button userPinCheck;
    EditText userPIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pin_check);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            atmCash = extras.getString("atmCash");

        }
        Toast.makeText(UserPinCheck.this,"Please Enter Your Pin",Toast.LENGTH_SHORT).show();

        userPIN=(EditText)findViewById(R.id.userPIN);


        userPinCheck = (Button)findViewById(R.id.pinCheckBtn);

        userPinCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String userPIN1 = userPIN.getText().toString().trim();
                if (userPIN1.length() != 5)
                {
                    try {
                        int num = Integer.parseInt(userPIN1);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(UserPinCheck.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",userPIN1+" is not a number");

                    }
                }
                else{
                    Toast.makeText(UserPinCheck.this, "Value should be of 5 digits", Toast.LENGTH_SHORT).show();

                }

                mDatabaseReference=  database.getReference("USER").child("USERDETAILS");


                mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        list = new ArrayList<>();
                        // StringBuffer stringbuffer = new StringBuffer();
                        for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){


                            String   userPINdb = dataSnapshot1.child("userPIN").getValue().toString();
                                   userAccount = dataSnapshot1.child("userAccount").getValue().toString();


                            // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                            if(userPIN1.equals(userPINdb)){
                                temp=1;
                                Toast.makeText(UserPinCheck.this, "log In successfull", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(UserPinCheck.this,HomeScreen.class);
                                i.putExtra("userPIN",userPIN1);
                                i.putExtra("atmCash",atmCash);
                                i.putExtra("userAccount",userAccount);


                                startActivity(i);
                            }


                            }
                        if(temp==0) {
                            number++;
                            if (number < 3) {

                                Toast.makeText(UserPinCheck.this, "Invalid PIN :" + number + "times", Toast.LENGTH_SHORT).show();
                            } else {
                                number++;
                                Toast.makeText(UserPinCheck.this, "You have entered wrong PIN :" + number + "times" + "Your card has been blocked!Please contact the bank", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(UserPinCheck.this, UserLogin.class);
                                startActivity(i);
                            }
                        }

                        }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        });

       }

     }



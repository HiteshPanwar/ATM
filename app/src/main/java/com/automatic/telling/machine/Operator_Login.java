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

public class Operator_Login extends AppCompatActivity {
    private DatabaseReference mdatabase;
  //  FirebaseDatabase database;
    List<String> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();
    Button loginButton;
    EditText operatorId,operatorPassword;

  //  String adminNamedb,adminIddb,adminPassworddb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_login);
        operatorId=(EditText)findViewById(R.id.operatorId);
        operatorPassword=(EditText)findViewById(R.id.operatorPassword);


        loginButton = (Button)findViewById(R.id.loginbtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String operatorId1 = operatorId.getText().toString().trim();
                final String operatorPassword1 = operatorPassword.getText().toString().trim();

                if (operatorId1.length() != 6)
                {
                    try {
                        int num = Integer.parseInt(operatorId1);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(Operator_Login.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",operatorId1+" is not a number");

                    }
                }
                else{
                    Toast.makeText(Operator_Login.this, "Value should be of 6 digits", Toast.LENGTH_SHORT).show();

                }




                mDatabaseReference=  database.getReference("OPERATOR").child("OPERATORDETAILS");

                mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        list = new ArrayList<>();
                        // StringBuffer stringbuffer = new StringBuffer();
                        for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                            
                            String operatorIddb = dataSnapshot1.child("operatorId").getValue().toString();
                          String   operatorPassworddb = dataSnapshot1.child("operatorPassword").getValue().toString();


                            // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                            if(operatorId1.equals(operatorIddb)&&operatorPassword1.equals(operatorPassworddb)){

                                Toast.makeText(Operator_Login.this, "log In successfull", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Operator_Login.this,CashDetails.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(Operator_Login.this, "Invalid/details", Toast.LENGTH_SHORT).show();
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

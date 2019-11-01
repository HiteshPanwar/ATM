package com.automatic.telling.machine.transactions;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.automatic.telling.machine.HomeScreen;
import com.automatic.telling.machine.R;
import com.automatic.telling.machine.UserPinCheck;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Deposit extends AppCompatActivity {
    private DatabaseReference mdatabase;
    FirebaseDatabase database;
    private FirebaseAuth auth;
    EditText userPIN, withdrawl,deposit, transfer,amount;
    Button depositbtn,home;
    String userPin,userAccount,atmCash,userAmount;
    String combinelink;
    int netAmount;
    int prevAmount, depositAmountInt,atmCashUpdated;
    private DatabaseReference mdatabase2;
    FirebaseDatabase database2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userPin = extras.getString("userPIN");
            userAccount=extras.getString("userAccount");
            atmCash = extras.getString("atmCash");
            userAmount=extras.getString("amount");

        }
        prevAmount = Integer.parseInt(userAmount);
        combinelink = "ATM/USER/"+userAccount+"/";
        System.out.println(combinelink);
        //userPIN =(EditText)findViewById(R.id.txt1);
        deposit =(EditText)findViewById(R.id.depositamount);
        //deposit =(EditText)findViewById(R.id.txt3);
        //transfer =(EditText)findViewById(R.id.txt4);
        //amount=(EditText)findViewById(R.id.txt5);
        //e6=(EditText)findViewById(R.id.txt7);
        depositbtn = (Button)findViewById(R.id.depositbtn);
        depositbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  String ed1 = userPIN.getText().toString().trim();
                String depositAmount = deposit.getText().toString().trim();
                // String ed3 = deposit.getText().toString().trim();
                // String ed4 = transfer.getText().toString().trim();
                //  String ed5 = combinelink;
                if (depositAmount.length() != 9)
                {
                    try {
                        int num = Integer.parseInt(depositAmount);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(Deposit.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",depositAmount+" is not a number");

                    }
                }
                else{
                    Toast.makeText(Deposit.this, "Value should be of 6 digits", Toast.LENGTH_SHORT).show();

                }

                database = FirebaseDatabase.getInstance();

                mdatabase = database.getReference(combinelink);
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = "-"+tsLong.toString();
                int flag = 1;
                HashMap<String,Integer> usermap = new HashMap<String, Integer>();

                if (depositAmount != null && !depositAmount.isEmpty()) {
                    depositAmount = depositAmount.replaceAll(" ", "_");
                    depositAmountInt = Integer.parseInt(depositAmount);
                    usermap.put("deposit", depositAmountInt);
                } else {
                    Toast.makeText(Deposit.this, "amount cannot be empty", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }

                if (flag == 1){
                    netAmount = prevAmount+depositAmountInt;
                    atmCashUpdated =Integer.parseInt(atmCash) + depositAmountInt;
                    if (atmCashUpdated>=0) {
                        if (netAmount <= 0) {

                            Toast.makeText(Deposit.this, "Insufficient balance in Account", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Deposit.this, HomeScreen.class);
                            i.putExtra("userPIN", userPin);
                            i.putExtra("userAccount", userAccount);
                            i.putExtra("atmCash", atmCashUpdated);
                            startActivity(i);

                        } else {

                            usermap.put("userAccount", Integer.parseInt(userAccount));
                            usermap.put("withdraw", 0);
                            usermap.put("transfer", 0);
                            usermap.put("transferTo", 0);
                            usermap.put("amount", netAmount);
                            database2 = FirebaseDatabase.getInstance();

                            mdatabase2 = database2.getReference().child("OPERATOR").child("OPERATORDETAILS").child("key");

                            atmCashUpdated = Integer.parseInt(atmCash) + depositAmountInt;
                            mdatabase2.child("atmCash").setValue(atmCashUpdated).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Deposit.this, " successfully uploaded", Toast.LENGTH_SHORT).show();
                                }
                            });


                            mdatabase.child(ts).setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Deposit.this, " successfully uploaded", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Deposit.this, HomeScreen.class);
                                    i.putExtra("userPIN", userPin);
                                    i.putExtra("userAccount", userAccount);
                                    i.putExtra("atmCash", atmCashUpdated);
                                    startActivity(i);
                                }
                            });
                        }

                    }
                }

            }
        });




        home = (Button)findViewById(R.id.homebtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Deposit.this, HomeScreen.class);
                i.putExtra("userPIN",userPin);
                i.putExtra("userAccount",userAccount);
                i.putExtra("atmCash",atmCashUpdated);
                startActivity(i);


            }
        });

    }
}

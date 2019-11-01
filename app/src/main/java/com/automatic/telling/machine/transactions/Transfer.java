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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Transfer extends AppCompatActivity {
    private DatabaseReference mdatabase;
    FirebaseDatabase database;
    private FirebaseAuth auth;
    EditText userPIN, withdrawl,deposit, transfer,transferTo,amount;
    Button transferbtn,home;
    String userPin,userAccount,atmCash,userAmount;
    String combinelink;
    int netAmount;
    int prevAmount, transferAmountInt,transferToInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
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
        transfer =(EditText)findViewById(R.id.transferAmount);
        transferTo =(EditText)findViewById(R.id.transferTo);
        //deposit =(EditText)findViewById(R.id.txt3);
        //transfer =(EditText)findViewById(R.id.txt4);
        //amount=(EditText)findViewById(R.id.txt5);
        //e6=(EditText)findViewById(R.id.txt7);
        transferbtn = (Button)findViewById(R.id.transferbtn);
        transferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  String ed1 = userPIN.getText().toString().trim();
                String transferAmount = transfer.getText().toString().trim();
                String transferTo1= transferTo.getText().toString().trim();
                // String ed3 = deposit.getText().toString().trim();
                // String ed4 = transfer.getText().toString().trim();
                //  String ed5 = combinelink;
                if (transferAmount.length() <= 9)
                {
                    try {
                        int num = Integer.parseInt(transferAmount);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(Transfer.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",transferAmount+" is not a number");

                    }
                }
                else{
                    Toast.makeText(Transfer.this, "Value should be of 6 digits", Toast.LENGTH_SHORT).show();

                }
                if (transferTo1.length() != 9)
                {
                    try {
                        int num = Integer.parseInt(transferTo1);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(Transfer.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",transferTo1+" is not a number");

                    }
                }
                else{
                    Toast.makeText(Transfer.this, "Value should be of 6 digits", Toast.LENGTH_SHORT).show();

                }



                database = FirebaseDatabase.getInstance();

                mdatabase = database.getReference(combinelink);
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = "-"+tsLong.toString();
                int flag = 1;
                HashMap<String,Integer> usermap = new HashMap<String, Integer>();

                if (transferAmount != null && !transferAmount.isEmpty()) {
                    transferAmount = transferAmount.replaceAll(" ", "_");
                    transferAmountInt = Integer.parseInt(transferAmount);
                    usermap.put("transfer", transferAmountInt);
                } else {
                    Toast.makeText(Transfer.this, "amount cannot be empty", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (transferTo1 != null && !transferTo1.isEmpty()) {
                    transferTo1 = transferTo1.replaceAll(" ", "_");
                    transferToInt = Integer.parseInt(transferTo1);
                    usermap.put("transferTo", transferToInt);
                } else {
                    Toast.makeText(Transfer.this, "account cannot be empty", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                if (flag == 1) {
                    netAmount = (prevAmount - transferAmountInt);


                    if (netAmount <= 0) {

                        Toast.makeText(Transfer.this, "Insufficient balance in Account", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Transfer.this, HomeScreen.class);
                        i.putExtra("userPIN", userPin);
                        i.putExtra("userAccount", userAccount);
                        i.putExtra("atmCash", atmCash);
                        startActivity(i);

                    } else {


                        usermap.put("userAccount", Integer.parseInt(userAccount));
                        usermap.put("withdraw", 0);
                        usermap.put("deposit", 0);
                        usermap.put("amount", netAmount);


                        mdatabase.child(ts).setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Transfer.this, " successfully uploaded", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Transfer.this, HomeScreen.class);
                                i.putExtra("userPIN", userPin);
                                i.putExtra("userAccount", userAccount);
                                i.putExtra("atmCash", atmCash);
                                startActivity(i);
                            }
                        });
                    }

                }

            }
        });




        home = (Button)findViewById(R.id.homebtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Transfer.this, HomeScreen.class);
                i.putExtra("userPIN",userPin);
                i.putExtra("userAccount",userAccount);
                i.putExtra("atmCash",atmCash);
                startActivity(i);


            }
        });




    }
}

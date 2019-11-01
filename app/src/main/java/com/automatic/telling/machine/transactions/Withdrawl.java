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

public class Withdrawl extends AppCompatActivity {
    private DatabaseReference mdatabase;
    FirebaseDatabase database;
    private FirebaseAuth auth;
    EditText userPIN, withdrawl,deposit,transfer,amount;
    Button withdrawbtn,home;
   String userPin,userAccount,atmCash,userAmount;
    String combinelink;
    int netAmount;
    int prevAmount,withdrawlAmountInt,atmCashUpdated;
    private DatabaseReference mdatabase2;
    FirebaseDatabase database2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawl);
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
        withdrawl =(EditText)findViewById(R.id.withdrawAmount);
        //deposit =(EditText)findViewById(R.id.txt3);
        //transfer =(EditText)findViewById(R.id.txt4);
        //amount=(EditText)findViewById(R.id.txt5);
        //e6=(EditText)findViewById(R.id.txt7);
        withdrawbtn = (Button)findViewById(R.id.withdrawbtn);
        withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  String ed1 = userPIN.getText().toString().trim();
                String withdrawlAmount = withdrawl.getText().toString().trim();
                // String ed3 = deposit.getText().toString().trim();
                // String ed4 = transfer.getText().toString().trim();
                //  String ed5 = combinelink;

                if (withdrawlAmount.length() != 9)
                {
                    try {
                        int num = Integer.parseInt(withdrawlAmount);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(Withdrawl.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",withdrawlAmount+" is not a number");

                    }
                }
                else{
                    Toast.makeText(Withdrawl.this, "Value should be of 6 digits", Toast.LENGTH_SHORT).show();

                }



                database = FirebaseDatabase.getInstance();

                mdatabase = database.getReference(combinelink);
                Long tsLong = System.currentTimeMillis() / 1000;
                String ts = "-" + tsLong.toString();
                int flag = 1;
                HashMap<String, Integer> usermap = new HashMap<String, Integer>();
                withdrawlAmountInt = Integer.parseInt(withdrawlAmount);

                if (withdrawlAmount != null && !withdrawlAmount.isEmpty()) {
                    withdrawlAmount = withdrawlAmount.replaceAll(" ", "_");
                    withdrawlAmountInt = Integer.parseInt(withdrawlAmount);
                    usermap.put("withdraw", withdrawlAmountInt);
                } else {
                    Toast.makeText(Withdrawl.this, "amount cannot be empty", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }




                if (flag == 1) {
                    netAmount = (prevAmount - withdrawlAmountInt);
                    atmCashUpdated =Integer.parseInt(atmCash) - withdrawlAmountInt;
                    if (atmCashUpdated>=0){
                    if(netAmount<=0){

                        Toast.makeText(Withdrawl.this, "Insufficient balance in Account", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Withdrawl.this, HomeScreen.class);
                        i.putExtra("userPIN", userPin);
                        i.putExtra("userAccount", userAccount);
                        i.putExtra("atmCash",atmCashUpdated);
                        startActivity(i);

                    }
                    else{



                    usermap.put("userAccount", Integer.parseInt(userAccount));
                    usermap.put("deposit", 0);
                    usermap.put("transfer", 0);
                    usermap.put("transferTo", 0);
                    usermap.put("amount", netAmount);

                    database2 = FirebaseDatabase.getInstance();

                    mdatabase2 = database2.getReference().child("OPERATOR").child("OPERATORDETAILS").child("key");
                    atmCashUpdated =Integer.parseInt(atmCash) - withdrawlAmountInt;


                    mdatabase2.child("atmCash").setValue(atmCashUpdated).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Withdrawl.this, " successfully uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });


                    mdatabase.child(ts).setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Withdrawl.this, " successfully uploaded", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(Withdrawl.this, HomeScreen.class);
                            i.putExtra("userPIN", userPin);
                            i.putExtra("userAccount", userAccount);
                            i.putExtra("atmCash",atmCashUpdated);
                            startActivity(i);


                        }
                    });

                }}
                }
            }
        });




    }
}

package com.automatic.telling.machine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.automatic.telling.machine.transactions.Deposit;
import com.automatic.telling.machine.transactions.PinChange;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CashDetails extends AppCompatActivity {
  Button cashEntered;
    EditText atmCashfield;
    int atmCashieldInt;
    String atmCash;




    private DatabaseReference mdatabase;
    FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_details);
       atmCashfield=(EditText)findViewById(R.id.atmCashfield);
    cashEntered = (Button)findViewById(R.id.cashdetailsbtn);
    cashEntered.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           final String atmCashfield1 = atmCashfield.getText().toString().trim();

            if (atmCashfield1.length() <= 9)
            {
                try {
                    int num = Integer.parseInt(atmCashfield1);
                    Log.i("",num+" is a number");
                } catch (NumberFormatException e) {
                    Toast.makeText(CashDetails.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                    Log.i("",atmCashfield1+" is not a number");

                }

                Toast.makeText(CashDetails.this, "Cash Entered successfully", Toast.LENGTH_SHORT).show();


                database = FirebaseDatabase.getInstance();

                mdatabase = database.getReference().child("OPERATOR").child("OPERATORDETAILS").child("key");


                mdatabase.child("atmCash").setValue(atmCashfield1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CashDetails.this, " successfully uploaded", Toast.LENGTH_SHORT).show();
                        Intent i1 = new Intent(CashDetails.this,UserLogin.class);
                        i1.putExtra("atmCash",atmCashfield1);
                        startActivity(i1);

                    }
                });

            }
            else{
                Toast.makeText(CashDetails.this, "Value should be minimum than 9 digits", Toast.LENGTH_SHORT).show();

            }







        }
    });

    }

}
/*
if (atmCashfield1 != null && !atmCashfield1.isEmpty()) {
                atmCashfield1 = atmCashfield1.replaceAll(" ", "_");
                atmCashieldInt = Integer.parseInt(atmCashfield1);
                DatabaseReference mDatabaseReference;
                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("OPERATOR").child("OPERATORDETAILS").child("key").child("atmCash");
                mDatabaseReference.setValue(atmCashieldInt);

            } else {
                Toast.makeText(CashDetails.this, "amount cannot be empty", Toast.LENGTH_SHORT).show();

            }
 */
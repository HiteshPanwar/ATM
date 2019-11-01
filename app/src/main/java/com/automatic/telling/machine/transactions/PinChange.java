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

import com.automatic.telling.machine.CashDetails;
import com.automatic.telling.machine.HomeScreen;
import com.automatic.telling.machine.R;
import com.automatic.telling.machine.UserLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PinChange extends AppCompatActivity {
String userPin,userAccount,atmCash,amount;
    String changedValue;
    EditText newPinField,oldPinField,confirmNewPinField;
    Button changePinbtn;
    int changedPinInt;
    private DatabaseReference changePinDatabaseRef;
    FirebaseDatabase changePinFireDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_change);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userPin = extras.getString("userPIN");
            userAccount=extras.getString("userAccount");
            atmCash = extras.getString("atmCash");
            amount=extras.getString("amount");

        }

        changePinbtn = (Button) findViewById(R.id.changePinbtn);
        newPinField =(EditText)findViewById(R.id.newPinField);
        oldPinField =(EditText)findViewById(R.id.oldPinField);
        confirmNewPinField =(EditText)findViewById(R.id.confirmNewPinField);
        changePinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newPin = newPinField.getText().toString().trim();
                String confirmNewPin = confirmNewPinField.getText().toString().trim();
                String oldPin = oldPinField.getText().toString().trim();


                if (newPin.length() != 5)
                {
                    try {
                        int num = Integer.parseInt(newPin);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(PinChange.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",newPin+" is not a number");

                    }
                }
                else{
                    Toast.makeText(PinChange.this, "Value should be of 6 digits", Toast.LENGTH_SHORT).show();

                }
                if (confirmNewPin.length() != 5)
                {
                    try {
                        int num = Integer.parseInt(confirmNewPin);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(PinChange.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",confirmNewPin+" is not a number");

                    }
                }
                else{
                    Toast.makeText(PinChange.this, "Value should be of 6 digits", Toast.LENGTH_SHORT).show();

                }
                if (oldPin.length() != 5)
                {
                    try {
                        int num = Integer.parseInt(oldPin);
                        Log.i("",num+" is a number");
                    } catch (NumberFormatException e) {
                        Toast.makeText(PinChange.this, "Please enter numeric value", Toast.LENGTH_SHORT).show();
                        Log.i("",oldPin+" is not a number");

                    }
                }
                else{
                    Toast.makeText(PinChange.this, "Value should be of 6 digits", Toast.LENGTH_SHORT).show();

                }


                if(newPin.equals(confirmNewPin)&&oldPin.equals(userPin)) {

                    if (newPin != null && !newPin.isEmpty()) {
                        newPin = newPin.replaceAll(" ", "_");
                        changedPinInt = Integer.parseInt(newPin);
                        DatabaseReference mDatabaseReference;
                        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("USER").child("USERDETAILS");

                        final String finalNewPin = newPin;
                        mDatabaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                for (DataSnapshot objSnapshot: snapshot.getChildren()) {

                                    String obj = objSnapshot.getKey();
                                   String userPINdb = objSnapshot.child("userPIN").getValue().toString();
                                    if(userPINdb.equals(userPin)){

                                        changePinFireDatabase = FirebaseDatabase.getInstance();

                                        changePinDatabaseRef = changePinFireDatabase.getReference().child("USER").child("USERDETAILS").child(obj);


                                        changePinDatabaseRef.child("userPIN").setValue(finalNewPin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(PinChange.this, " successfully uploaded", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(PinChange.this,HomeScreen.class);
                                                i.putExtra("userPIN",userPin);
                                                i.putExtra("userAccount",userAccount);
                                                startActivity(i);


                                            }
                                        });




                                    }
                                    //We have found key1 and key2


                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError firebaseError) {
                                Log.e("Read failed", firebaseError.getMessage());
                            }
                        });
                      //  mDatabaseReference.setValue(changedPinInt);
                    } else {
                        Toast.makeText(PinChange.this, "amount cannot be empty", Toast.LENGTH_SHORT).show();

                    }

                }
                else{
                    Toast.makeText(PinChange.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }





            }
        });




    }
}

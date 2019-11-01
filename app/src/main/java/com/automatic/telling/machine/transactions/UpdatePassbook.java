package com.automatic.telling.machine.transactions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.automatic.telling.machine.HomeScreen;
import com.automatic.telling.machine.R;
import com.automatic.telling.machine.adapter.RecyclerAdapterUpdatePassbook;
import com.automatic.telling.machine.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdatePassbook extends AppCompatActivity {
    List<User> list;

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;

    String link,defaultcId,userPin,userAccount,atmCash,amount;

    RecyclerAdapterUpdatePassbook recycler;
    Button updatePassbookbtn;


    //Getting reference to Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_passbook);
        updatePassbookbtn = (Button)findViewById(R.id.updatepassbookbtn2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userPin = extras.getString("userPIN");
            userAccount=extras.getString("userAccount");
            atmCash = extras.getString("atmCash");
            amount=extras.getString("amount");

        }

             link = "ATM/USER/"+userAccount+"/";
        //Initializing our Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.my_category_recycler_view);




        if (mRecyclerView != null) {
            //to enable optimization of recyclerview
            mRecyclerView.setHasFixedSize(true);
        }
        //using staggered grid pattern in recyclerview.child(USER_ID).child("movies")
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        System.out.println("tttttttttttttthhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiissssssssssssssssssssssssss:"+defaultcId+"[][][]"+link);

        mDatabaseReference=  database.getReference(link);

        mDatabaseReference.limitToFirst(5)
                .orderByKey()
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                list = new ArrayList<>();
                // StringBuffer stringbuffer = new StringBuffer();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    String userAccount = dataSnapshot1.child("userAccount").getValue().toString();
                    String  deposit = dataSnapshot1.child("deposit").getValue().toString();
                    String transfer = dataSnapshot1.child("transfer").getValue().toString();
                    String transferTo = dataSnapshot1.child("transferTo").getValue().toString();
                    String withdraw = dataSnapshot1.child("withdraw").getValue().toString();
                    String amount = dataSnapshot1.child("amount").getValue().toString();
                    User listdata = new User(userAccount,deposit,transfer,transferTo,withdraw,amount);

                    listdata.setUserAccount(userAccount);
                    listdata.setAmount(amount);
                    listdata.setDeposit(deposit);
                    listdata.setTransfer(transfer);
                    listdata.setTransferTo(transferTo);
                    listdata.setWithdraw(withdraw);


                    list.add(listdata);
                    // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                }

                recycler = new RecyclerAdapterUpdatePassbook(list,getApplicationContext());
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(layoutmanager);
                mRecyclerView.setItemAnimator( new DefaultItemAnimator());
                mRecyclerView.setAdapter(recycler);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        updatePassbookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdatePassbook.this,"Passbook Updated",Toast.LENGTH_LONG).show();
                Intent i = new Intent(UpdatePassbook.this,HomeScreen.class);
                i.putExtra("userPIN", userPin);
                i.putExtra("userAccount", userAccount);
                i.putExtra("atmCash",atmCash);
                startActivity(i);
            }
        });

    }
}

package com.automatic.telling.machine.models;

/**
 * Created by HITESH on 9/14/2017.
 */

public class xtra {
}
/*mDatabaseReference=  database.getReference(link);

        mDatabaseReference.limitToFirst(1)
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
        }) */
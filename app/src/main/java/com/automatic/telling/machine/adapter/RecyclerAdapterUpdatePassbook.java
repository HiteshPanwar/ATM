package com.automatic.telling.machine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.automatic.telling.machine.R;
import com.automatic.telling.machine.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.List;

/**
 * Created by HITESH on 11/4/2017.
 */
public class RecyclerAdapterUpdatePassbook extends RecyclerView.Adapter<RecyclerAdapterUpdatePassbook.MyHolder>  {

    String link1,linkToObjectNode;
    List<User> listdata;
    Context context;

    private LayoutInflater inflater;
    FirebaseStorage storage;
    StorageReference pathReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();

    File localFile;
    File dest;

    //File dest =new File(Environment.getExternalStoragePublicDirectory
    //       (Environment.DIRECTORY_DOWNLOADS), "audio1.mp3");

    StorageReference pathRef;
    String mystring1[];
    String a;

    public RecyclerAdapterUpdatePassbook(List<User> listdata,Context context  ) {
        this.listdata = listdata;
        this.context= context;
        inflater=LayoutInflater.from(context);
        //this.storageReference=storageReference;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.update_passbook_card, parent, false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    public void onBindViewHolder(MyHolder holder, int position) {
        User data = listdata.get(position);
        holder.setData(data , position);
        holder.setListeners();

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView userAccount,deposit,transfer,transferTo,withdraw,amount;
        ImageView imageView;
        User data;
        int pos;

        public MyHolder(View itemView) {
            super(itemView);
            userAccount = (TextView) itemView.findViewById(R.id.userAccount);
            deposit = (TextView) itemView.findViewById(R.id.deposit);
            transfer = (TextView) itemView.findViewById(R.id.transfer);
            transferTo = (TextView) itemView.findViewById(R.id.transferTo);
            withdraw = (TextView) itemView.findViewById(R.id.withdraw);
            amount = (TextView) itemView.findViewById(R.id.amount);


        }
        public void setData(User data ,int postion){
            this.userAccount.setText(data.getUserAccount());
            this.deposit.setText(data.getDeposit());
            this.transfer.setText(data.getTransfer());
            this.transferTo.setText(data.getTransferTo());
            this.withdraw.setText(data.getWithdraw());
            this.amount.setText(data.getAmount());
            this.data=data;
            this.pos=postion;

        }
        public void setListeners() {

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
            }
        }
    }

}

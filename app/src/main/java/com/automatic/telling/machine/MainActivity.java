package com.automatic.telling.machine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//hellogithub
public class MainActivity extends AppCompatActivity {
//Button trendingBtn,newBtn,kidsBtn,greatestBtn,famousBtn,dailyBtn,browseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




            Intent intent = new Intent(MainActivity.this,Operator_Login.class);
            startActivity(intent);
       }
    }


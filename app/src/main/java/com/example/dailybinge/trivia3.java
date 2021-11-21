package com.example.dailybinge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class trivia3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia3);
        getSupportActionBar().hide();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void choice(View v){
        String b= (String) v.getTooltipText();
        Toast.makeText(this, b, Toast.LENGTH_SHORT).show();
        Intent i=getIntent() ;
        String x=i.getStringExtra("email");
        i=new Intent(this,dashboard.class);
        i.putExtra("email",x);
        startActivity(i);
    }
}
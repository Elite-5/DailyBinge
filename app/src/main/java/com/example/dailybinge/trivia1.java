package com.example.dailybinge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class trivia1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia1);
        getSupportActionBar().hide();
    }
    public void choice(View v){
        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        Intent i=getIntent() ;
        String x=i.getStringExtra("email");
        i=new Intent(this,trivia2.class);
        i.putExtra("email",x);
        startActivity(i);
    }
}
package com.example.dailybinge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class front extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        getSupportActionBar().hide();
    }

    public void NewUs(View v){
        Toast.makeText(this, "Going for Sign up", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,new_user.class);
        startActivity(i);

    }
    public void Login_btn(View v){
        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,login.class);
        startActivity(i);
    }
}
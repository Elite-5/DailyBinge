package com.example.dailybinge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class profile_edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        getSupportActionBar().hide();
    }
}
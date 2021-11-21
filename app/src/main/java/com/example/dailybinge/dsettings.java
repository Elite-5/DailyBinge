package com.example.dailybinge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class dsettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsettings);
        getSupportActionBar().hide();
    }
    public void about(View v) {
        Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, aboutus.class);
        startActivity(i);
    }
    public void contact(View v) {
        Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, contact_us.class);
        startActivity(i);
    }
    public void privacy(View v) {
        Toast.makeText(this, "Privacy", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, privacy.class);
        startActivity(i);
    }
    }
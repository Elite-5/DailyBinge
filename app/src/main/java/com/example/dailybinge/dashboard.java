package com.example.dailybinge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();

    }
    public void recipe(View v){
        Toast.makeText(this, "MyRecipe", Toast.LENGTH_SHORT).show();
        Intent i=getIntent();
        String x=i.getStringExtra("email");
        i=new Intent(this,MyrecipesActivity.class);
        i.putExtra("email",x);
        startActivity(i);
    }
    public void searc(View v){
//        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
//        Intent i=new Intent(this,finding.class);
//        startActivity(i);
    }

    public void recipe1(View v1){
        Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
        Intent i=getIntent();
        String x=i.getStringExtra("email");
        i=new Intent(this,AccountActivity.class);
        i.putExtra("email",x);
        startActivity(i);
    }
    public void commun(View v1){
        Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
        Intent i=getIntent();
        String x=i.getStringExtra("email");
        i=new Intent(this,community.class);
        i.putExtra("email",x);
        startActivity(i);
    }
    public void start(View v){
        Toast.makeText(this, "Timer", Toast.LENGTH_SHORT).show();
        Intent i=getIntent() ;
        String x=i.getStringExtra("email");
        i=new Intent(this,recipe.class);
        i.putExtra("count","0");
        i.putExtra("dish",v.getContentDescription());
        i.putExtra("email",x);
        startActivity(i);
    }


}
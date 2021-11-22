package com.example.dailybinge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MyrecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecipes);
        getSupportActionBar().hide();
    }

    public void recipe(View v){
        Toast.makeText(this, "MyRecipe", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,MyrecipesActivity.class);
        startActivity(i);
    }

    public void recipe1(View v1){
        Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,AccountActivity.class);
        startActivity(i);
    }
    public void new_dish(View v1){
        Toast.makeText(this, "New Dish", Toast.LENGTH_SHORT).show();
        Intent i= getIntent();
        String email=i.getStringExtra("email");
        i=new Intent(this,dishfront.class);
        i.putExtra("email",email);
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
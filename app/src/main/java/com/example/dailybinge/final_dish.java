package com.example.dailybinge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class final_dish extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_dish);
        Intent i=getIntent();
        String step=i.getStringExtra("step");
        String step1=step.replace("##stepdef##","\n");
        step1=step1.replace("##titleline##","\n");
        step1=step1.replace("##T##",",Time:");
        text=findViewById(R.id.textView12);
        text.setText(step1);
        String[] x=step.split("##titleline##");
        String recipe=x[0];
        step=x[1];
        x=step.split("##stepdef##");
        HashMap<String,Object> map=new HashMap<>();
        for(int k=0;k<x.length;k++){
            map.put("Step"+(k+1),x[k].replace(",","\n"));
        }
        FirebaseDatabase.getInstance().getReference().child("Recipe").child(recipe).updateChildren(map);
    }
    public void backtodash(View v1){
        Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
        Intent i= getIntent();
        String email=i.getStringExtra("email");
        i=new Intent(this,dashboard.class);
        i.putExtra("email",email);
        startActivity(i);
    }
}
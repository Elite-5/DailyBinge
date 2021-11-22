package com.example.dailybinge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class dishfront extends AppCompatActivity {
    private EditText ti,in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishfront);
        getSupportActionBar().hide();
        ti=findViewById(R.id.editTextTextPersonName4);
        in=findViewById(R.id.editTextTextPersonName5);
    }
    public void stepping(View v1){
        Toast.makeText(this, "New Dish", Toast.LENGTH_SHORT).show();
        Intent i= getIntent();
        String title=ti.getText().toString();
        String ingre=in.getText().toString();
        String email=i.getStringExtra("email");
        i=new Intent(this,new_dish.class);
        i.putExtra("email",email);
        i.putExtra("step",title+"##titleline##"+ingre+"##T##0");
        startActivity(i);
    }
}
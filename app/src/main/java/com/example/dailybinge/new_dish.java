package com.example.dailybinge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class new_dish extends AppCompatActivity {
    private EditText ste,tim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);
        ste=findViewById(R.id.editTextTextPersonName2);
        tim=findViewById(R.id.editTextTextPersonName3);
        getSupportActionBar().hide();
    }
    public void finish(View v1){
        Toast.makeText(this, "Finishing Dish", Toast.LENGTH_SHORT).show();
        Intent i= getIntent();
        String text=ste.getText().toString();
        String time=tim.getText().toString();
        String email=i.getStringExtra("email");
        String prev=i.getStringExtra("step");
        i=new Intent(this,final_dish.class);
        i.putExtra("email",email);
        i.putExtra("step",prev+"##stepdef##"+text+"##T##"+time);
        startActivity(i);
    }
    public void nextstep(View v1){
        Toast.makeText(this, "Next Step", Toast.LENGTH_SHORT).show();
        Intent i= getIntent();
        String email=i.getStringExtra("email");
        String prev=i.getStringExtra("step");
        String text=ste.getText().toString();
        String time=tim.getText().toString();
        i=new Intent(this,new_dish.class);
        i.putExtra("email",email);
        if(time.compareTo("")==0)
        {
            time="0";
        }
        if(text.compareTo("")==0)
        {
            text="  ";
        }
        i.putExtra("step",prev+"##stepdef##"+text+"##T##"+time);
        startActivity(i);
    }
}
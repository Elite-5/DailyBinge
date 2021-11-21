package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class login extends AppCompatActivity {
    private EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        email=findViewById(R.id.editText);
        password=findViewById(R.id.textView5);
    }
    public void trivia(View v){
        ArrayList<Object> l=new ArrayList<>();
        DatabaseReference df= FirebaseDatabase.getInstance().getReference().child("UserData");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()){
                    l.add(x.getValue());
                }
                int f=0;
                for(Object x:l){
                    String r=x.toString();
                    r=r.substring(1,r.length()-1);
                    String[] k=r.split(", ");
                    String z=k[0].substring(6);
                    z=z.replace("**dot**","\\.");
                    if(z.compareTo(email.getText().toString())==0){
                        f=1;
                        if(k[6].substring(9).compareTo(password.getText().toString())==0){
                       log();
                        }
                        else{
                        notsuccess2();}
                    }
                }
                if(f==0)
                notsuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void forget(View v){
        Toast.makeText(this, "Forget Password", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,forgotpassword.class);
        startActivity(i);
    }
    public void notsuccess(){
        Toast.makeText(this, "No user", Toast.LENGTH_SHORT).show();
    }
    public void notsuccess2(){
        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
    }
    public void log(){
        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,trivia1.class);
        i.putExtra("email",email.getText().toString());
        startActivity(i);
            }


}
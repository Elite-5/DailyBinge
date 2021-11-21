package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Properties;

public class forgotpassword extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        getSupportActionBar().hide();
    }
    public void login(View v){
        ArrayList<Object> l=new ArrayList<>();
        editText=findViewById(R.id.editText);
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
                    if(k[0].substring(6).compareTo(editText.getText().toString())==0) {
                        f = 1;
                    }
                }
                if(f==0){
                    editText.setError("Email Does Not Exist.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,login.class);
        startActivity(i);
    }
}
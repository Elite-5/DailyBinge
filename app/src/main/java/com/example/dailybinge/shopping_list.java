package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class shopping_list extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Intent i=getIntent();
        text=findViewById(R.id.textView12);
        String email=i.getStringExtra("email");
        ArrayList<Object> l1=new ArrayList<>();
        DatabaseReference df1= FirebaseDatabase.getInstance().getReference().child("UserData");
        df1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()){
                    l1.add(x.getValue());
                }
                for(Object x:l1) {
                    String r = x.toString();
                    r = r.substring(1, r.length() - 1);
                    String[] k = r.split(", ");
                    String y=k[0].substring(6).replace("**dot**","\\.");
                    if (y.compareTo(email) == 0) {
                        text.setText(k[1].substring(12));
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
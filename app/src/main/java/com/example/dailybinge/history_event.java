package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class history_event extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_event);
        Intent i=getIntent();
        text=findViewById(R.id.textView12);
        String email=i.getStringExtra("email");
        ArrayList<Object> l1=new ArrayList<>();
        email=email.replace(".","**dot**");
        DatabaseReference df1= FirebaseDatabase.getInstance().getReference().child("Event").child(email);
        df1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()){
                    l1.add(x.getValue());
                }
                for(Object x:l1) {

                    String r = x.toString();
                    text.setText(text.getText().toString()+"\n"+r);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
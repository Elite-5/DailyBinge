package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    private String user;
    private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().hide();
        username=findViewById(R.id.textView18);
        Intent i=getIntent();
        user=i.getStringExtra("email");
        ArrayList<Object> l=new ArrayList<>();
        DatabaseReference df= FirebaseDatabase.getInstance().getReference().child("UserData");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot x : dataSnapshot.getChildren()) {
                    l.add(x.getValue());
                }
                int f = 0;
                for (Object x : l) {
                    String r = x.toString();
                    r = r.substring(1, r.length() - 1);
                    String[] k = r.split(", ");
                    if(k[0].substring(6).compareTo(user)==0){
                        username.setText(k[5].substring(5));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void dset(View v){
        Toast.makeText(this, "Going for Dashboard Settings", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,dsettings.class);
        startActivity(i);

    }

    public void profileset(View v) {
        Toast.makeText(this, "Profile Settings", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, profile_edit.class);
        startActivity(i);

    }
    public void shopping(View v) {
        Toast.makeText(this, "List", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, shopping_list.class);
        i.putExtra("email",user);
        startActivity(i);

    }
    public void logout(View v) {
        Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, front.class);
        startActivity(i);

    }
    public void meal(View v1){
        Toast.makeText(this, "Meal Planner", Toast.LENGTH_SHORT).show();
        Intent i=getIntent();
        String x=i.getStringExtra("email");
        i=new Intent(this,meal_planner.class);
        i.putExtra("email",x);
        startActivity(i);
    }
}
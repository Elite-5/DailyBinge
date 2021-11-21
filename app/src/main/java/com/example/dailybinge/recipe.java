package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

class timer extends Thread{
    String time;
    TextView tv;
    int i=60;
    timer(String time,TextView tv){
      this.time=time;
      this.tv=tv;
      i=Integer.parseInt(time);
      this.start();
    }
    public void run(){
        try{
            while(i!=0){
                tv.setText((i/60)+":"+(i-((i/60)*60)));
                i--;
                Thread.sleep(1000);
            }
            tv.setText("0:0");
            Thread.sleep(1000);
        }
        catch(Exception e){}
    }
}
public class recipe extends AppCompatActivity {
    public EditText tex;
    public TextView tv;
    public TextView step;
    private int count;
    private String[] k;
    private String r,x1,email,previn;
    private timer t;
    private int exit=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        getSupportActionBar().hide();
        Intent i=getIntent() ;
        String x=i.getStringExtra("count");
        x1=i.getStringExtra("dish");
        email=i.getStringExtra("email");
        count=Integer.parseInt(x);
        count+=1;
        tv=findViewById(R.id.textView);
        ArrayList<Object> l=new ArrayList<>();
        DatabaseReference df= FirebaseDatabase.getInstance().getReference().child("Recipe").child(x1);
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int f=0;
                for(DataSnapshot x:dataSnapshot.getChildren()){
                    l.add(x.getValue());
                    f+=1;
                }
                int co=0;
                for(Object x:l){
                    co+=1;
                    if(co==count) {
                        r= x.toString();
                        k = r.split("##T##");
                        break;
                    }
                }
                if(f==co){
                    exit=1;
                }
                step.setText(k[0]);
                int i=Integer.parseInt(k[1]);
                tv.setText((i/60)+":"+(i-((i/60)*60)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        step=findViewById(R.id.textView10);
        step.setText(x);
        if(count==1){
            exit=2;
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
                            previn = k[1].substring(12);
                        }
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
    public void start(View v){
        if(exit==2){
            HashMap<String,Object> map=new HashMap<>();
            map.put("Ingredients",previn+step.getText());
            String y=email;
            y=y.replace(".","**dot**");
            FirebaseDatabase.getInstance().getReference().child("UserData").child(y).child("Ingredients").setValue(previn+"\n"+step.getText());
        }
        String time=k[1];
        t=new timer(time,tv);
    }
    public void next(View v){
        if(exit==0 || exit==2){
        Toast.makeText(this, "Next Step", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,recipe.class);
        String p=Integer.toString(count);
        i.putExtra("count",p);
        i.putExtra("email",email);
        i.putExtra("dish",x1);
        startActivity(i);}
        else{
            Toast.makeText(this, "Back to Dashboard", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,dashboard.class);
            i.putExtra("email",email);
            startActivity(i);
        }
    }
}
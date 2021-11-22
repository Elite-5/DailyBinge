package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class finding extends AppCompatActivity {
    private SearchView sv;
    private ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> adda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finding);
        getSupportActionBar().hide();
        sv=findViewById(R.id.search_bar);
        lv=findViewById(R.id.mylist);
        list=new ArrayList<>();
        ArrayList<Object> l=new ArrayList<>();
        DatabaseReference df= FirebaseDatabase.getInstance().getReference().child("Recipe");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()){
                    l.add(x.getKey());
                }
                for(Object x:l){
                    String r=x.toString();
                    list.add(r);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adda=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adda);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sv.setQuery(((TextView)view).getText(),true);
            }
        });
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent i=getIntent() ;
                String x=i.getStringExtra("email");
                i=new Intent(getApplicationContext(),recipe.class);
                i.putExtra("count","0");
                i.putExtra("dish",query);
                i.putExtra("email",x);
                startActivity(i);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adda.getFilter().filter(newText);
                return false;
            }
        });
    }
}
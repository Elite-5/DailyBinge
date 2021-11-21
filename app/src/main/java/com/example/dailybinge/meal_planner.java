package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class meal_planner extends AppCompatActivity {
private String x;
private EditText et;
private TextView tv;
private CalendarView calendarView;
private TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_planner);
        getSupportActionBar().hide();
        Intent i=getIntent();
        x=i.getStringExtra("email");
        calendarView = (CalendarView) findViewById(R.id.cal1);
        tv = (TextView) findViewById(R.id.date);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date1 = (month + 1) + "/" + day + "/" + year;
                tv.setText(date1);
            }
        });
    }
    public void history(View v1){
        Toast.makeText(this, "Your Events", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,history_event.class);
        i.putExtra("email",x);
        startActivity(i);
    }
    public void save(View v1) {
        et=findViewById(R.id.editText);
        x=x.replace(".","**dot**");
        String z=tv.getText().toString().replace("/","**sep**");
        FirebaseDatabase.getInstance().getReference().child("Event").child(x).child(z).setValue(tv.getText().toString()+":"+et.getText().toString());
        Toast.makeText(this, "Saving the Event", Toast.LENGTH_SHORT).show();
    }
    }
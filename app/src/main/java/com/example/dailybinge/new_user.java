package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class new_user extends AppCompatActivity {
    //Initialise Variable
    EditText etName,etMobile,etEmail,etPassword,etConfirmPassword;
    Button btSubmit;
    AwesomeValidation awesomeValidation;
    TextView tvDate;
    Button btPickDate;
    DatePickerDialog.OnDateSetListener setListener;
    int gender=0;
    String genderch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        getSupportActionBar().hide();
        //assign variable
        etName=findViewById(R.id.editTextTextPersonName);
        etMobile=findViewById(R.id.editTextPhone);
        etEmail=findViewById(R.id.editTextTextEmailAddress);
        etPassword=findViewById(R.id.editTextTextPassword);
        etConfirmPassword=findViewById(R.id.editTextTextPassword2);
        btSubmit=findViewById(R.id.button3);
        tvDate = findViewById(R.id.editTextDate);
        btPickDate = findViewById(R.id.btPickDate);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        new_user.this,android.R.style.Theme_Holo_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        setListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view,int year,int month,int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                tvDate.setText(date);
            }
        };
        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        new_user.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view,int year,int month,int dayOfMonth) {
                        month=month+1;
                        String date=dayOfMonth+"/"+month+"/"+year;
                        tvDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        // initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // add validation for name
        awesomeValidation.addValidation(this,R.id.editTextTextPersonName,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        // for mobile no
        awesomeValidation.addValidation(this,R.id.editTextPhone,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);

        // for email
        awesomeValidation.addValidation(this,R.id.editTextTextEmailAddress, Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        // for password
        awesomeValidation.addValidation(this, R.id.editTextTextPassword, "^" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{6,}" + "$", R.string.InvalidPassword);

        // for confirm password
        awesomeValidation.addValidation(this,R.id.editTextTextPassword2,R.id.editTextTextPassword,R.string.invalid_confirm_password);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check validation
                if(awesomeValidation.validate()) {
                    if (gender == 1) {
                        ArrayList<Object> l = new ArrayList<>();
                        DatabaseReference df = FirebaseDatabase.getInstance().getReference().child("UserData");
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
                                    r = k[0].substring(6);
                                    if (r.compareTo(etEmail.getText().toString()) == 0) {
                                        f = 1;
                                        break;
                                    }
                                }
                                if (f == 0) {
                                    Toast.makeText(getApplicationContext(), "Form Validate Successfully....", Toast.LENGTH_SHORT).show();
                                    log(view);
                                } else {
                                    etEmail.setError("This email is already registered.");
//                                Toast.makeText(getApplicationContext(),"This email is already registered....",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Select Gender", Toast.LENGTH_SHORT).show();
                    }
                } else {
                        Toast.makeText(getApplicationContext(), "Validation failed..", Toast.LENGTH_SHORT).show();
                    }
            }
        });


    }
    public void gch(View v){
        genderch= (String)v.getContentDescription();
        gender=1;
    }
    public void log(View v){
        HashMap<String,Object> map=new HashMap<>();
        map.put("Name",etName.getText().toString());
        map.put("Mobile",etMobile.getText().toString());
        map.put("Email",etEmail.getText().toString());
        map.put("Password",etPassword.getText().toString());
        map.put("Date",tvDate.getText().toString());
        map.put("Gender",genderch);
        map.put("Ingredients","");
        String[] x=etEmail.getText().toString().split("\\.");
        String y=x[0]+"**dot**"+x[1];
        FirebaseDatabase.getInstance().getReference().child("UserData").child(y).updateChildren(map);
        Intent i=new Intent(this,login.class);
        startActivity(i);
    }
}
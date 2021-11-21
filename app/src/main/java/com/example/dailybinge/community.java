package com.example.dailybinge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class update extends Thread{
    TextView tx;
    update(TextView tx){
        this.tx=tx;
        this.start();
    }
    public void run(){

    }
}
public class community<button> extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private EditText msg;
    private TextView tx;
    private String x,user="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        Intent i1=getIntent();
        x=i1.getStringExtra("email");
        msg=findViewById(R.id.editTextTextPersonName6);
        tx=findViewById(R.id.textView39);
        update t=new update(tx);
        ArrayList<Object> l=new ArrayList<>();
        DatabaseReference df= FirebaseDatabase.getInstance().getReference().child("UserData");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot x1:dataSnapshot.getChildren()){
                    l.add(x1.getValue());
                }
                for(Object x1:l){
                    String r=x1.toString();
                    r=r.substring(1,r.length()-1);
                    String[] k=r.split(", ");
                    String z=k[0].substring(6);
                    z=z.replace("**dot**","\\.");
                    if(z.compareTo(x)==0){
                        user=k[5].substring(5);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        ArrayList<Object> l1=new ArrayList<>();
//        DatabaseReference df1= FirebaseDatabase.getInstance().getReference().child("Community");
//        df1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot x1:dataSnapshot.getChildren()){
//                    l1.add(x1.getValue());
//                }
//                String r1="";
//                for(Object x1:l1){
//                    String r=x1.toString();
//                    String[] k=r.split(", ");
//                    r1=r1+"\n"+k[1];
//                }
//                tx.setText(r1);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        Button buttonLoadImage = findViewById(R.id.button27);
//        buttonLoadImage.setOnClickListener(arg0 -> {
//
//            Intent i = new Intent(
//                    Intent.ACTION_PICK,
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//            startActivityForResult(i, RESULT_LOAD_IMAGE);
//        });
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//
//            ImageView imageView =findViewById(R.id.imageView2);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//
//        }


    }
    public void send(View v1){
        Toast.makeText(this, "Sending message", Toast.LENGTH_SHORT).show();
        String r=msg.getText().toString();
        tx.setText(tx.getText().toString()+"\n"+user+":"+r);
        msg.setText("");
        String currentTime = Calendar.getInstance().getTime().toString();
        FirebaseDatabase.getInstance().getReference().child("Community").child(currentTime).setValue(user+":"+r);
    }
}

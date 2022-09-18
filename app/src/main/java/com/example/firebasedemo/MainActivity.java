package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
       TextView textView1,textView2,tv3,tv4;
//       Calendar calendar;
//       SimpleDateFormat simpleDateFormat;
//       String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.tv1);
        textView2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv3.setText(getCurrentTime());
        tv4.setText(getCurrentDate());

//       calendar=Calendar.getInstance();
//       simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm");
//       date=simpleDateFormat.format(calendar.getTime());
//       getDateAndTime.setText(date);



       DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // for (DataSnapshot ds: snapshot.getChildren()){
//                    String a=""+ds.child("date").getValue();
//                    String ab=""+ds.child("time").getValue();
//                    textView1.setText(a);
//                    textView2.setText(ab);

                    String a=snapshot.child("date").getValue().toString();
                    String ab=snapshot.child("time").getValue().toString();
                    textView1.setText(a);
                    textView2.setText(ab);

                    if (a.equals(tv3) && ab.equals(tv4)){
                        startActivity(new Intent(MainActivity.this,Lock.class));
                        finish();
                    }

                //}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @SuppressLint("SimpleDateFormat")
    private String getCurrentTime(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }
    @SuppressLint("SimpleDateFormat")
    private String getCurrentDate(){
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
}
package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
       TextView textView1,textView2,tv3,tv4;
       String currentDate,currentTime,serverTime,serverDate;
    Date date1,date2,time1,time2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.tv1);
        textView2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);


        currentTime=getCurrentTime();
        currentDate=getCurrentDate();

        tv3.setText(currentTime);
        tv4.setText(currentDate);

        puData();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 serverTime=snapshot.child("time").getValue().toString();
                 serverDate=snapshot.child("date").getValue().toString();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                     date1 = sdf.parse(serverDate);
                     date2 = sdf.parse(currentDate);

                    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a") ;
                     time1 = timeFormat.parse(serverTime);
                     time2=timeFormat.parse(currentTime);
                    if (date2.after(date1)) {
                        startActivity(new Intent(MainActivity.this,Lock.class));
                    } else {
                        if (time2.after(time1))
                        {
                            startActivity(new Intent(MainActivity.this,Lock.class));
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                textView1.setText(serverTime);
                textView2.setText(serverDate);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void puData() {
        DatabaseReference dref= FirebaseDatabase.getInstance().getReference("Users");
        dref.child("date").setValue("20-09-2022");
        dref.child("time").setValue("11:50 AM");}

    private String getCurrentTime(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());}
    private String getCurrentDate(){
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());}
}
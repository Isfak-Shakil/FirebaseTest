package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
       TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.tv1);
        textView2=findViewById(R.id.tv2);


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
                //}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
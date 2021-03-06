package com.ossovita.mydoesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskActivity extends AppCompatActivity {

    TextView titlepage,addtitle,adddesc,adddate;
    EditText titledoes,descdoes,datedoes;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        makeStyles();

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send data to database
                reference = FirebaseDatabase.getInstance().getReference().child("BoxDoese")
                        .child("Does"+doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        snapshot.getRef().child("titleDoes").setValue(titledoes.getText().toString());
                        snapshot.getRef().child("descDoes").setValue(descdoes.getText().toString());
                        snapshot.getRef().child("dateDoes").setValue(datedoes.getText().toString());

                        startActivity(new Intent(NewTaskActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void makeStyles() {
        titlepage = findViewById(R.id.titlepage);
        addtitle = findViewById(R.id.addTitle);
        titledoes = findViewById(R.id.titleDoes);
        adddesc = findViewById(R.id.adddesc);
        adddate = findViewById(R.id.adddate);
        titledoes = findViewById(R.id.titleDoes);
        descdoes = findViewById(R.id.descdoes);
        datedoes = findViewById(R.id.datedoes);
        //fonts
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/ML.otf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MM.otf");

        titlepage.setTypeface(MMedium);
        addtitle.setTypeface(MLight);
        titledoes.setTypeface(MMedium);

        adddesc.setTypeface(MLight);
        descdoes.setTypeface(MMedium);

        adddate.setTypeface(MLight);
        datedoes.setTypeface(MMedium);

    }
}
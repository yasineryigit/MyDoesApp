package com.ossovita.mydoesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlePage, subtitlePage,endPage;
    Button btnAddNew;
    DatabaseReference reference;
    ArrayList<MyDoes> list;
    RecyclerView recyclerView;
    DoesAdapter doesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeStyles();

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.ourdoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doesAdapter = new DoesAdapter(MainActivity.this,list);
        recyclerView.setAdapter(doesAdapter);


        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewTaskActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {

        //DoesApp adındaki database'e referans verdik ve üzerindeki değişiklikleri takip ediyoruz
        reference = FirebaseDatabase.getInstance().getReference().child("BoxDoese");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    //Database'deki MyDoes classına uyan objeleri al onlarla MyDoes objesi oluştur
                    MyDoes p = ds.getValue(MyDoes.class);
                    list.add(p);
                }
                doesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void makeStyles() {

        titlePage = findViewById(R.id.titlepage);
        subtitlePage = findViewById(R.id.subtitlepage);
        endPage = findViewById(R.id.endpage);
        btnAddNew = findViewById(R.id.btnAddNew);

        //fonts
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/ML.otf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MM.otf");

        titlePage.setTypeface(MMedium);
        subtitlePage.setTypeface(MLight);
        endPage.setTypeface(MLight);
        btnAddNew.setTypeface(MLight);
    }
}
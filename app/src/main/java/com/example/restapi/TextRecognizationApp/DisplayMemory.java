package com.example.restapi.TextRecognizationApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restapi.Adapter.RecyclerAdapter;
import com.example.restapi.Database.DatabaseHelper;
import com.example.restapi.Entity.Info;
import com.example.restapi.R;

import java.util.List;

public class DisplayMemory extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Info> listInfo;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_memory);
        listInfo = databaseHelper.getAllInfo();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter(listInfo, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}

package com.example.p04_colors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.vv_rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] data = new String[32];
        for (int i = 0; i < 32; i++) {
            data[i] = i+"th Element";
        }
        recyclerView.setAdapter(new RecyclerViewAdapter(data));
    }
}
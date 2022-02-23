package com.example.a10rv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.a10rv.databinding.ActivityMainBinding;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

// RecyclerView List Example ver01 -- simplest data, cell, adapter
// https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example jim lin


// RecyclerView List Example ver01 -- simplest data, cell, adapter
// https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example jim lin

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        RecyclerView lv_recyclerView = findViewById(R.id.vv_rvList);
//
//        lv_recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        String[] data = new String[32];
//        for (int i = 0; i < 32; i++) {
//            data[i] = i+"th Element";
//        }
//
//        lv_recyclerView.setAdapter(new MyRecyclerViewAdapter(data));
//    }
//}
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList lv_data;
    ArrayList lv_data01, lv_data02;
    MyRecyclerViewAdapter lv_adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            View view = binding.getRoot();
            setContentView(view);
            RecyclerView lv_recyclerView = findViewById(R.id.vv_rvList);
            // ##### small issue
            lv_recyclerView.setLayoutManager(new LinearLayoutManager(this));
            //String[] lv_data = new String[32]; this is from version 3
            String [] lv_model= {"iPad", "Xoom", "Playbook", "TouchPad"};
            String [] lv_OS= {"Apple Inc.", "Motorola", "Motion in Research", "HP"};
            lv_data01= new ArrayList<>();
            lv_data02= new ArrayList<>();
            Collections.addAll(lv_data01, lv_model);
            Collections.addAll(lv_data02, lv_OS);// #### major issue -- NEED an Adapter (user defined) together
            lv_adapter = new MyRecyclerViewAdapter(lv_data01, lv_data02, this);
            lv_recyclerView.setAdapter(lv_adapter);
            lv_recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            lv_recyclerView.setAdapter(lv_adapter);
            //lv_recyclerView.setAdapter(new MyRecyclerViewAdapter(lv_data)); version 3
            lv_data = new ArrayList<>();
            for(int i=0; i<32; i++){
                lv_data.add(i+"th Element");
            }FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.vv_rvFab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // lv_data.add(1,"New Item");
                    lv_data01.add(1, "New ITem");
                    lv_data02.add(1,"NEW ITEM");

                    lv_adapter.notifyDataSetChanged();
                }
            });fab.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //lv_data.remove(2);
                    lv_data01.remove(2);
                    lv_data02.remove(2);
                    lv_adapter.notifyDataSetChanged();
                    return true;
                }
            });


        }
}


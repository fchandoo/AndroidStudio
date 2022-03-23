package com.example.p05_scr2;

import android.app.Activity;
import android.os.Bundle;

import com.example.p05_scr2.databinding.ActivitySecondBinding;

public class MyThirdActivity extends Activity {
    private ActivitySecondBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Bundle extras = getIntent().getExtras();
        String lv_str = extras != null ? extras.getString("StrArg") : "Empty";
        binding.vvTvOut2.setText("Input - " + lv_str);
    }

    
}

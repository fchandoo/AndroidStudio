package com.example.a05_cb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import com.example.a05_cb.databinding.ActivityMainBinding;
import android.preference.TwoStatePreference;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

// callback #4
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    View rootView;
    Button redbutton, greenbutton, bluebutton;
    CheckBox checkBox;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        rootView = getWindow().getDecorView();

        redbutton = (Button) binding.redbutton;
        redbutton.setOnClickListener(this);
        greenbutton = (Button) binding.greenbutton;
        greenbutton.setOnClickListener(this);
        bluebutton = (Button) binding.bluebutton;
        bluebutton.setOnClickListener(this);

//        binding.redbutton.setOnClickListener(this);
//        binding.greenbutton.setOnClickListener(this);
//        binding.bluebutton.setOnClickListener(this);

        //// HERE
        checkBox = (CheckBox) binding.checkBox;
        checkBox.setOnClickListener(this);

        switch1 = (Switch) binding.switch1;
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.textView.setText("Switch turned On ");
            }
        });


        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // PLACEHOLDER
                //change to binding
                binding.textView.setText("FAB");
            }
        });
    }

    @Override
    public void onClick(View v) {
        //binding.textView();
        //TextView textView = (TextView) findViewById(R.id.textView);

        if (v.getId() == R.id.redbutton) {
            ////HERE
            if (checkBox.isChecked() && checkBox!=null) {
                v.setBackgroundColor(Color.WHITE);
                rootView.setBackgroundColor(Color.parseColor("#FF6200EE"));
                binding.textView.setTextColor(Color.BLACK);
            } else {
                v.setBackgroundColor(Color.RED);
                rootView.setBackgroundColor(Color.WHITE);
                binding.textView.setTextColor(Color.RED);
            }
        } else if (v.getId() == R.id.greenbutton) {
            if(checkBox.isChecked()&& checkBox!=null) {
                v.setBackgroundColor(Color.WHITE);
                rootView.setBackgroundColor(Color.GREEN);
                binding.textView.setTextColor(Color.BLACK);
            } else {
                v.setBackgroundColor(Color.GREEN);
                rootView.setBackgroundColor(Color.WHITE);
                binding.textView.setTextColor(Color.GREEN);
            }
        } else if (v.getId() == R.id.bluebutton) {
            if(checkBox.isChecked()&& checkBox!=null) {
                v.setBackgroundColor(Color.WHITE);
                rootView.setBackgroundColor(getColor(R.color.purple_500));
                binding.textView.setTextColor(Color.BLACK);
            } else {
                v.setBackgroundColor(Color.BLUE);
                rootView.setBackgroundColor(Color.WHITE);
                binding.textView.setTextColor(Color.BLUE);
            }
        }
        else if(v.getId() == R.id.checkBox) {
            binding.textView.setText("CHECKBOX");
        }
    }
}
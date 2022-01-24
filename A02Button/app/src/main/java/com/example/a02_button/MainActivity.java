package com.example.a02_button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.a02_button.databinding.ActivityMainBinding;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        //Button button = (Button)findViewById(R.id.button);
        binding.button.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        //Implement event handling
                        //Context ctx =  MainActivity.this;
                       // Toast.makeText(ctx, "Hello", Toast.LENGTH_LONG).show();
                        binding.textview.setText("Hello, Fatema Chandoo");
                    }
                });

    }
}
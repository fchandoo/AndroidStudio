package com.example.p05_scr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.p05_scr2.databinding.ActivitySecondBinding;


public class MySecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setTitle("Back to First");

            binding.vvBtnTo1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent lv_it = new Intent(MySecondActivity.this, MainActivity.class);
                            startActivity(lv_it);
                        }
                    });
            Bundle extras = getIntent().getExtras();
            String lv_str = extras != null ? extras.getString("StrArg") : "Empty";
            binding.vvTvOut2.setText("You Typed - " + lv_str);

        }
    }
}

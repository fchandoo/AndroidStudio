package com.example.a06_rgb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.a06_rgb.databinding.ActivityMainBinding;
import android.view.View;
import android.widget.SeekBar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ActivityMainBinding binding;
    ////HERE
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textViewRed.setText("255");
        binding.textViewGreen.setText("255");
        binding.textViewBlue.setText("255");

        //// HERE
        rootView = getWindow().getDecorView();
        binding.seekBarRed.setOnSeekBarChangeListener(this);
        binding.seekBarGreen.setOnSeekBarChangeListener(this);
        binding.seekBarBlue.setOnSeekBarChangeListener(this);

        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// HERE
                int rgb = 255;
                binding.seekBarRed.setProgress(rgb);
                binding.seekBarGreen.setProgress(rgb);
                binding.seekBarBlue.setProgress(rgb);

                binding.textViewRed.setText(""+rgb);
                binding.textViewGreen.setText(""+rgb);
                binding.textViewBlue.setText(""+rgb);

                //// HERE
                rootView.setBackgroundColor(Color.rgb(rgb, rgb, rgb));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ////Toast.makeText(this, "You chose : " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        switch (menuItem.getItemId()) {
            case R.id.menu_first: binding.textView.setText("First Selected");
                return true;
            case R.id.menu_second: binding.textView.setText("Second Selected");
                return true;
            case R.id.menu_third: binding.textView.setText("Third Selected");
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
    //// HERE
    private int r=255, g=255, b=255;
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBarRed:
                r = progress;
                binding.textViewRed.setText(""+progress);
                break;
            case R.id.seekBarGreen:
                g = progress;
                binding.textViewGreen.setText(""+progress);
                break;
            case R.id.seekBarBlue:
                b = progress;
                binding.textViewBlue.setText(""+progress);
                break;
        }
        rootView.setBackgroundColor(Color.rgb(r, g, b));
    }
}

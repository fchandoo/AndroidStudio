package com.example.p02_fontsize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.p02_fontsize.databinding.ActivityMainBinding;
import android.view.View;
import android.content.Context;
import android.widget.SeekBar;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Implement event handling
                        Context ctx =  MainActivity.this;
                        ////Toast.makeText(ctx, "Hello", Toast.LENGTH_LONG).show();
                        binding.textview.setText("Hello android!");
                    }
                });
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "FAB pressed", Toast.LENGTH_LONG).show();
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
            case R.id.menu_first: binding.textview.setText("First Selected");
                return true;
            case R.id.menu_second: binding.textview.setText("Second Selected");
                return true;
            case R.id.menu_third: binding.textview.setText("Third Selected");
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }


    private int r=96;
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBar:
                r = progress;
                binding.textview2.setText(""+progress);
                break;
        }
        //rootView.setBackgroundColor(Color.rgb(r, g, b));
    }

}
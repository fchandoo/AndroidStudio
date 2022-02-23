package com.example.a10rv;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a10rv.databinding.ActivityMainBinding;

public class MyListDetailsActivity extends AppCompatActivity {


    protected void onCreate (Bundle savedInstanceState) {
        //Shows the back buttons

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_details);
        if (getIntent() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Back");

            Bundle extras = getIntent().getExtras();
            String lv_str = extras != null ? extras.getString("OSName") : "Empty";
            TextView lv_tv = (TextView) findViewById(R.id.vv_tvSub);
            lv_tv.setText("You Selected - " + lv_str);
        }
    }


        @Override
        public boolean onSupportNavigateUp() {
            Intent lv_it = new Intent(MyListDetailsActivity.this, MainActivity.class);
            this.startActivity(lv_it);
            this.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            return true;
        }


}
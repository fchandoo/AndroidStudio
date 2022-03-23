package com.example.a13_frag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.a13_frag.databinding.ActivityMainBinding;
//ver01 - add statically
//ver02 - add dynamically
//https://riptutorial.com/android-fragments

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    FragmentOne frag01;
    FragmentTwo frag02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        frag01 = new FragmentOne();
        frag02 = new FragmentTwo();

        //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.replace(R.id.fragment1, new FragmentOne());
        //ft.commit();
        loadFragment(this, R.id.fragment1, new FragmentOne(), "fragment1");
        loadFragment(this, R.id.fragment2, new FragmentTwo(), "fragment2");

        binding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().remove(frag01).commit();
                fm.beginTransaction().remove(frag02).commit();
                fm.executePendingTransactions();
                loadFragment(MainActivity.this, R.id.fragment1, frag01, "fragment1");
                loadFragment(MainActivity.this, R.id.fragment2, frag02, "fragment2");
            }

        });
        binding.buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(frag02);
                ft.commit();

                fm.beginTransaction().remove(frag01).commit();
                fm.executePendingTransactions();

                loadFragment(MainActivity.this, R.id.fragment1, frag02, "fragment2");
                loadFragment(MainActivity.this, R.id.fragment2, frag01, "fragment1");
            }
        });


    }

    // HERE
    public static void loadFragment(AppCompatActivity activity, int containerId, Fragment fragment, String tag) {
        activity.getSupportFragmentManager().beginTransaction().
                replace(containerId, fragment,tag).commitAllowingStateLoss();
    }
}
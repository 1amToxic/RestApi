package com.example.restapi.TextRecognizationApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.restapi.Adapter.FlashAppAdapter;
import com.example.restapi.R;
import com.google.android.material.tabs.TabLayout;

public class MainScreen extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        viewPager = findViewById(R.id.mViewPager);
        tabLayout = findViewById(R.id.mTabLayout);
        FlashAppAdapter adapter = new FlashAppAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setSelectedTabIndicator(0);
    }
}
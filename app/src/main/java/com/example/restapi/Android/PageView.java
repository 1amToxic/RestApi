package com.example.restapi.Android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.restapi.Adapter.ExFragmentPagerAdapter;
import com.example.restapi.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PageView extends AppCompatActivity {
    List<String> listName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        listName.add("General Frags");
        listName.add("Places Frags");
        listName.add("Food Frags");
        ExFragmentPagerAdapter adapter = new ExFragmentPagerAdapter(getSupportFragmentManager(), listName, this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

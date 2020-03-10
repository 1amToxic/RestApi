package com.example.restapi.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.restapi.Fragment.MemoryFragment;
import com.example.restapi.Fragment.RecognizationFragment;

public class FlashAppAdapter extends FragmentPagerAdapter {
    Context context;
    public FlashAppAdapter(@NonNull FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new RecognizationFragment();
        }
        else{
            return new MemoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Text Recognization";
        }
        else{
            return "Save Information";
        }
    }
}

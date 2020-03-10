package com.example.restapi.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.restapi.Fragment.FoodFrag;
import com.example.restapi.Fragment.GeneralFrag;
import com.example.restapi.Fragment.PlacesFrag;

import java.util.List;

public class ExFragmentPagerAdapter extends FragmentPagerAdapter {
    List<String> listName;
    Context context;
    public ExFragmentPagerAdapter(@NonNull FragmentManager fm, List<String> list, Context context) {
        super(fm);
        this.listName = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new GeneralFrag();
        }
        else if(position==1){
            return new PlacesFrag();
        }
        else{
            return new FoodFrag();
        }
    }

    @Override
    public int getCount() {
        return listName.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listName.get(position);
    }
}

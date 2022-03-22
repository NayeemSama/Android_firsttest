package com.xpressy.firsttest.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.xpressy.firsttest.Fragment.DynamicTabFragment;

public class DynamicTabAdapter extends FragmentStatePagerAdapter {

    int numofTab;

    public DynamicTabAdapter(@NonNull FragmentManager fm, int numofTab) {
        super(fm);
        this.numofTab = numofTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle b = new Bundle();
        b.putInt("position", position);
        Fragment frag = DynamicTabFragment.newInstance();
        frag.setArguments(b);

        return frag;
    }

    @Override
    public int getCount() {
        return numofTab;
    }
}

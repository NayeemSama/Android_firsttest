package com.xpressy.firsttest.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xpressy.firsttest.Activity.BaseActivity;
import com.xpressy.firsttest.Adapter.DynamicTabAdapter;
import com.xpressy.firsttest.R;

public class DynamicTabActivity extends BaseActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamictab);
        setupActionBar("DynamicTab", true);

        viewPager = findViewById(R.id.vpDynamic);
        tabLayout = findViewById(R.id.tlDynamic);

        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        setDynamicTablayout();
        }

    private void setDynamicTablayout() {

        for (int i = 0; i < 10; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("Page: " + i));
        }

        DynamicTabAdapter tabAdapter = new DynamicTabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabAdapter);
        viewPager.setCurrentItem(0);

    }


}


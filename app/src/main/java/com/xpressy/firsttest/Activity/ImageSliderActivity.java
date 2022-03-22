package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.xpressy.firsttest.Activity.BaseActivity;
import com.xpressy.firsttest.Adapter.ImageSliderAdapter;
import com.xpressy.firsttest.R;
import com.xpressy.secondtest.SecondTest;

public class ImageSliderActivity extends BaseActivity {

    ViewPager viewPager;
    ImageSliderAdapter adapter;
    BottomNavigationView bottomNavigationView;
    int position;
    ViewGroup viewGroup;
    Object object;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageslider);


        setupActionBar("ImageSlider", true);
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);


        Log.d("MODULE",SecondTest.MESSAGE);



        viewPager = findViewById(R.id.viewPager);
        adapter = new ImageSliderAdapter();
        viewPager.setAdapter(adapter);

//        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.india){
//                    Toast.makeText(ImageSliderActivity.this, "INDIA", Toast.LENGTH_SHORT).show();
//                    item.setIcon(R.drawable.ic_star);
//                }
//
//                if (item.getItemId() == R.id.usa){
//                    item.setIcon(R.drawable.ic_star);
//                }
//
//                if (item.getItemId() == R.id.china){
//                    item.setIcon(R.drawable.ic_star);
//                }
//
//                if (item.getItemId() == R.id.japan){
//                    item.setIcon(R.drawable.ic_star);
//                }
//
//                if (item.getItemId() == R.id.other){
//                    item.setIcon(R.drawable.ic_star);
//                }
//            }
//        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                for (int i=0; i<5;i++){
                    if (bottomNavigationView.getMenu().getItem(i).getItemId()==id){
                        bottomNavigationView.getMenu().getItem(i).setIcon(R.drawable.ic_star);
                        Toast.makeText(ImageSliderActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
//                        adapter.setPrimaryItem();
//                        Log.d("OBJECTGROUP", String.valueOf(object));
//                        Log.d("VIEWGROUP", String.valueOf(viewGroup));
                    }
                    else {
                        bottomNavigationView.getMenu().getItem(i).setIcon(R.drawable.ic_star_border);
                    }
                }
                return true;
            }
        });

    }
}

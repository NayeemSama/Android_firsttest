package com.xpressy.firsttest.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageSliderAdapter extends PagerAdapter {

//    Context context;
    String[] names = {"India", "USA", "China", "Japan", "Other"};
//    onItemSelect itemSelect;

//    public ImageSliderAdapter(Context context, onItemSelect itemSelect) {
//        this.context = context;
//        this.itemSelect = itemSelect;
//    }

//    public interface onItemSelect{
//        void onSelect(ViewGroup c, Object o);
//    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o==view;
    }

//    130311565 - 1
//    228483906 - 2
//    78288331 - 3
//    206033320 - 4
//    207987905 - 5

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        Log.d("OBJECT", String.valueOf(object.hashCode()));
//        Log.d("POSITION", String.valueOf(position));
//        itemSelect.onSelect(container, object);
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.d("VIEW", String.valueOf(container.getChildAt(position)));
        LinearLayout layout = new LinearLayout(container.getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(container.getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setText(names[position]);
        textView.setTextColor(Color.parseColor("#111111"));
        textView.setTextSize(30);

        layout.addView(textView);

        container.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("CHILD", String.valueOf(container.getChildCount()));
        container.removeView((LinearLayout)object);
    }
}

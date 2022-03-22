package com.xpressy.firsttest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xpressy.firsttest.R;

public class DynamicTabFragment extends Fragment {

    public static DynamicTabFragment newInstance(){
        return new DynamicTabFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dynamictab, container, false);
        connectView(v);
        return v;
    }

    private void connectView(View v) {
        TextView name = v.findViewById(R.id.tvFragName);
        name.setText(String.valueOf("Category :  " + getArguments().getInt("position")));
    }
}

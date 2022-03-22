package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.xpressy.firsttest.R;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity {

    SearchView searchView;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupActionBar("Search", true);

        searchView = findViewById(R.id.srchView);
        listView = findViewById(R.id.listSEARCH);

        arrayList.add("Form");
        arrayList.add("List");
        arrayList.add("About");
        arrayList.add("Alert");
        arrayList.add("AsyncTask");
        arrayList.add("Image Slider");
        arrayList.add("Dynamic Tab");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (arrayList.contains(s)){
                    arrayAdapter.getFilter().filter(s);
                }
                else {
                    Toast.makeText(SearchActivity.this, "No Match Found", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });

    }
}

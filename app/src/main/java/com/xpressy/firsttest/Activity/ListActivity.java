package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.xpressy.firsttest.Activity.BaseActivity;
import com.xpressy.firsttest.Adapter.MainAdapter;
import com.xpressy.firsttest.Database.Tbl_User;
import com.xpressy.firsttest.Model.UserModel;
import com.xpressy.firsttest.R;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    ArrayList<UserModel> arrayList = new ArrayList<>();
    MaterialToolbar materialToolbar;

    private ActionMode actionMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        materialToolbar = findViewById(R.id.materialtoolBar);

//        toolbar = findViewById(R.id.materialtoolBar);

        materialToolbar.setTitle("List");
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search){
                    Toast.makeText(ListActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId()==R.id.more){
                    Toast.makeText(ListActivity.this, "MORE", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        setupActionBar("List", true);
        recyclerView = findViewById(R.id.rcvList);
        arrayList = new Tbl_User(ListActivity.this).getTable();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter = new MainAdapter(this, arrayList, new MainAdapter.onHoldListner() {
            @Override
            public void onLongClickListner(int position) {
                if (actionMode==null){
                }
                Toast.makeText(ListActivity.this, arrayList.get(position).getUsername(), Toast.LENGTH_SHORT).show();
                actionMode = startSupportActionMode(actionModeCallback);

            }
        });
        recyclerView.setAdapter(mainAdapter);

    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            materialToolbar.setVisibility(View.GONE);
            mode.getMenuInflater().inflate(R.menu.context_menu, menu);
            mode.setTitle("Option");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            materialToolbar.setVisibility(View.VISIBLE);
            mode = null;
        }

    };


}

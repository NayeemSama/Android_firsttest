package com.xpressy.firsttest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.xpressy.firsttest.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends BaseActivity {

    ListView listView;
    List<String> arrayList;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setupActionBar("Home", true);

        listView = findViewById(R.id.lvDash);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Login:
                        Intent i10 = new Intent(DashboardActivity.this, LoginActivity.class);
                        startActivity(i10);
                        break;

                    case R.id.Form:
                        Intent i1 = new Intent(DashboardActivity.this, FormActivity.class);
                        startActivity(i1);
                        break;

                    case R.id.List:
                        Intent i2 = new Intent(DashboardActivity.this, ListActivity.class);
                        startActivity(i2);
                        break;

                    case R.id.About:
                        Intent i3 = new Intent(DashboardActivity.this, XMLActivity.class);
                        startActivity(i3);
                        break;

                    case R.id.Alert:
                        Intent i4 = new Intent(DashboardActivity.this, AlertDialogActivity.class);
                        startActivity(i4);
                        break;

                    case R.id.AsyncTask:
                        Intent i5 = new Intent(DashboardActivity.this, AsynctaskActivity.class);
                        startActivity(i5);
                        break;

                    case R.id.ImageSlider:
                        Intent i6 = new Intent(DashboardActivity.this, ImageSliderActivity.class);
                        startActivity(i6);
                        break;

                    case R.id.DynamicTab:
                        Intent i7 = new Intent(DashboardActivity.this, DynamicTabActivity.class);
                        startActivity(i7);
                        break;

                    case R.id.Search:
                        Intent i8 = new Intent(DashboardActivity.this, SearchActivity.class);
                        startActivity(i8);
                        break;

                    case R.id.RetroFit:
                        Intent i9 = new Intent(DashboardActivity.this, RetroFitActivity.class);
                        startActivity(i9);
                        break;

                    case R.id.Volley:
                        Intent i11 = new Intent(DashboardActivity.this, VolleyActivity.class);
                        startActivity(i11);
                        break;

                    case R.id.OkHttp:
                        Intent i13 = new Intent(DashboardActivity.this, OkHttpActivity.class);
                        startActivity(i13);
                        break;

                    case R.id.MovieList:
                        Intent i14 = new Intent(DashboardActivity.this, MovieListActivity.class);
                        startActivity(i14);
                        break;

                    case R.id.GoogleSignIn:
                        Intent i15 = new Intent(DashboardActivity.this, GoogleSignInActivity.class);
                        startActivity(i15);
                        break;

                    case R.id.FaceBookSignIn:
                        Intent i16 = new Intent(DashboardActivity.this, FaceBookActivity.class);
                        startActivity(i16);
                        break;

                    case R.id.OtpSignIn:
                        Intent i17 = new Intent(DashboardActivity.this, OtpAuthActivity.class);
                        startActivity(i17);
                        break;

                    case R.id.EmailSignIn:
                        Intent i18 = new Intent(DashboardActivity.this, EmailVerifyActivity.class);
                        startActivity(i18);
                        break;

                    case R.id.Notification:
                        Intent i19 = new Intent(DashboardActivity.this, NotificationActivity.class);
                        startActivity(i19);
                        break;

                    case R.id.Maps:
                        Intent i20 = new Intent(DashboardActivity.this, MapsActivity.class);
                        startActivity(i20);
                        break;
                }
                return false;
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayList = new ArrayList<>();
        arrayList.add("Register");
        arrayList.add("Login");
        arrayList.add("List");
        arrayList.add("About");
        arrayList.add("Alert");
        arrayList.add("AsyncTask");
        arrayList.add("Image Slider");
        arrayList.add("Dynamic Tab");
        arrayList.add("Search");
        arrayList.add("Form");
        arrayList.add("RetroFit");
        arrayList.add("Volley");
        arrayList.add("OkHttp");
        arrayList.add("MovieList");
        arrayList.add("GoogleSignIn");
        arrayList.add("FaceBookSignIn");
        arrayList.add("OtpSignIn");
        arrayList.add("EmailSignIn");
        arrayList.add("Notification");
        arrayList.add("Maps");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_item,R.id.tvnameDASH, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arrayList.get(i)){
                    case "Form":
                        Intent i1 = new Intent(DashboardActivity.this, RegisterActivity.class);
                        startActivity(i1);
                        break;

                    case "Login":
                        Intent i10 = new Intent(DashboardActivity.this, LoginActivity.class);
                        startActivity(i10);
                        break;

                    case "List":
                        Intent i2 = new Intent(DashboardActivity.this, ListActivity.class);
                        startActivity(i2);
                        break;

                    case "About":
                        Intent i3 = new Intent(DashboardActivity.this, AboutActivity.class);
                        startActivity(i3);
                        break;

                    case "Alert":
                        Intent i4 = new Intent(DashboardActivity.this, AlertDialogActivity.class);
                        startActivity(i4);
                        break;

                    case "AsyncTask":
                        Intent i5 = new Intent(DashboardActivity.this, AsynctaskActivity.class);
                        startActivity(i5);
                        break;

                    case "Image Slider":
                        Intent i6 = new Intent(DashboardActivity.this, ImageSliderActivity.class);
                        startActivity(i6);
                        break;

                    case "Dynamic Tab":
                        Intent i7 = new Intent(DashboardActivity.this, DynamicTabActivity.class);
                        startActivity(i7);
                        break;

                    case "Search":
                        Intent i8 = new Intent(DashboardActivity.this, SearchActivity.class);
                        startActivity(i8);
                        break;

                    case "Register":
                        Intent i9 = new Intent(DashboardActivity.this, FormActivity.class);
                        startActivity(i9);
                        break;

                    case "RetroFit":
                        Intent i11 = new Intent(DashboardActivity.this, RetroFitActivity.class);
                        startActivity(i11);
                        break;

                    case "Volley":
                        Intent i12 = new Intent(DashboardActivity.this, VolleyActivity.class);
                        startActivity(i12);
                        break;

                    case "OkHttp":
                        Intent i13 = new Intent(DashboardActivity.this, OkHttpActivity.class);
                        startActivity(i13);
                        break;

                    case "MovieList":
                        Intent i14 = new Intent(DashboardActivity.this, MovieListActivity.class);
                        startActivity(i14);
                        break;

                    case "GoogleSignIn":
                        Intent i15 = new Intent(DashboardActivity.this, GoogleSignInActivity.class);
                        startActivity(i15);
                        break;

                    case "FaceBookSignIn":
                        Intent i16 = new Intent(DashboardActivity.this, FaceBookActivity.class);
                        startActivity(i16);
                        break;

                    case "OtpSignIn":
                        Intent i17 = new Intent(DashboardActivity.this, OtpAuthActivity.class);
                        startActivity(i17);
                        break;

                    case "EmailSignIn":
                        Intent i18 = new Intent(DashboardActivity.this, EmailVerifyActivity.class);
                        startActivity(i18);
                        break;

                    case "Notification":
                        Intent i19 = new Intent(DashboardActivity.this, NotificationActivity.class);
                        startActivity(i19);
                        break;

                    case "Maps":
                        Intent i20 = new Intent(DashboardActivity.this, MapsActivity.class);
                        startActivity(i20);
                        break;
                }
            }
        });


    }


}

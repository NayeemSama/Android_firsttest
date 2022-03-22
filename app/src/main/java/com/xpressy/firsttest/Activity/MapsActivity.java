package com.xpressy.firsttest.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.xpressy.firsttest.Interface.Api;
import com.xpressy.firsttest.R;
import com.xpressy.firsttest.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
                    , GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private ActivityMapsBinding binding;
    FusedLocationProviderClient fusedLocationProviderClient;
    final static int CODE = 101;
    Location currentLocation;
    private GoogleMap mMap;
    int m = 0;
    Api api;
    LatLng marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://maps.googleapis.com/")
                .build();

        api = retrofit.create(Api.class);

//        getMap("41.38506"+","+"42.173403", "40.416775"+"," +"-3.70379");
    }

    private void getMap(String origin, String destination) {

        api.get("driving", "less",
                origin, destination,getString(R.string.google_maps_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("SUB", d.toString());

                    }

                    @Override
                    public void onSuccess(retrofit2.adapter.rxjava2.Result value) {
                        Log.d("SUC", value.toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ERR", e.toString());

                    }
                });

    }


    private void fetchLocation() {
        Log.d("LOCATION", "FETCH");

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, CODE);
        }

        @SuppressLint("MissingPermission")
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            currentLocation = location;

                            SupportMapFragment mapFragment = (SupportMapFragment)
                                    getSupportFragmentManager()
                                            .findFragmentById(R.id.map);
                            mapFragment.getMapAsync(MapsActivity.this);
                            View view = mapFragment.getView();
                            view.setClickable(false);
                        } else {
                            requestNewLocationData();
                        }
                    }
                }

        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });

    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());


    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            currentLocation = locationResult.getLastLocation();
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("LOCATION", "READY");
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        LatLng marker = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newLatLng(marker));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 5));
        mMap.addMarker(new MarkerOptions().position(marker).title("Marker"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {

        mMap.clear();
//        m++;
        marker = new LatLng(latLng.latitude, latLng.longitude);
//        mMap.addMarker(new MarkerOptions().position(marker).title("Marker"+m));

        getAddress(latLng);
    }

    private void getAddress(LatLng latLng) {

        Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
        String result = "";
        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            mMap.addMarker(new MarkerOptions().position(marker).title(addressList.get(0).getAddressLine(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        Log.d("LONG CLICK", latLng.toString());

    }
}
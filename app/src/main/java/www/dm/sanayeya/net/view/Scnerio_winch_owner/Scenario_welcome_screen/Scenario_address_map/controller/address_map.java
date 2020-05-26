package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_welcome_screen.Scenario_address_map.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_map_location.controller.choose_map_location;

public class address_map extends AppCompatActivity implements OnMapReadyCallback, PlaceSelectionListener, OnCompleteListener, View.OnClickListener, GoogleMap.OnCameraChangeListener {

    @BindView(R.id.confirm)
    Button confirm;
    private GoogleMap mGoogleMap;
    AutocompleteSupportFragment autoCompleteSupportFragment;
    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    String place_name, place_lat, place_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_map_location);
        ButterKnife.bind(this);
        //SUPPORT MAP
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        //INITIALIZE PLACES
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyAFZWqZ7it3SPq_vuGpWM7qPx1ZrLAYB74", Locale.US);
        }

        //SET UP COMPLETE SUPPORT FRAGMENT
        setAutoCompleteSupportFragment();

        //SET ON CLICK LISTNERS
        confirm.setOnClickListener(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;

        //MAP PREMISSION OPEN SETTINGS
        getLocationPermission();

        //GET DEVICE LOCATION
        getloca();

        //SET ON CLICK LISTNERS
        mGoogleMap.setOnCameraChangeListener(this);

    }

    void setAutoCompleteSupportFragment() {
        autoCompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autoCompleteSupportFragment.setHint("search place");
        autoCompleteSupportFragment.setPlaceFields(fields);
        autoCompleteSupportFragment.setCountry("EG");
        autoCompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG));

        //PLACE SELECTED
        autoCompleteSupportFragment.setOnPlaceSelectedListener(this);

    }

    @Override
    public void onPlaceSelected(@NonNull Place place) {

        LatLng latLng = place.getLatLng();
        place_name = place.getName();

        //MOVE CAMERA
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //GET PLACE NAME
        getAddress(latLng.latitude, latLng.longitude);

        //MARKER ANIMATION
        View map = findViewById(R.id.map);
        new utils().marker_animation(latLng.latitude, latLng.longitude, map, mGoogleMap);


    }

    @Override
    public void onError(@NonNull Status status) {
        Toast.makeText(this, "" + status.getStatusMessage(), Toast.LENGTH_SHORT).show();
    }

    //IF GRANTED PREMISSION DONE GET CURRENT LOCATION
    public void getloca() {
        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    //GET CURRENT LOCATION
    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {

            final Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(this);


        } catch (SecurityException e) {

        }
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful() && task.getResult() != null) {
            Location currentLocation = (Location) task.getResult();
            double current_lat = currentLocation.getLatitude();
            double current_lng = currentLocation.getLongitude();

            //MOVE CAMERA
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(current_lat, current_lng)));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            //SET LOCATION ADDRESS
            getAddress(current_lat, current_lng);

            mGoogleMap.clear();

        }
    }


    //GET PREMISSION IF YES OR NO
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.confirm) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", place_name);
            returnIntent.putExtra("place_lat", place_lat);
            returnIntent.putExtra("place_lng", place_lng);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    //GET ALL OF THINGS ABOUT MY LOCATION INFORMATION
    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(address_map.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);

            if (addresses.size() != 0) {
                Address obj = addresses.get(0);
                String address_name = obj.getAddressLine(0);

                autoCompleteSupportFragment.setText(address_name);

                place_name = address_name; //GET PLACE NAME
                place_lat = ""+lat; //GET PLACE LAT
                place_lng = ""+lng; //GET PLACE LNG

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        double lat = cameraPosition.target.latitude;
        double lng = cameraPosition.target.longitude;
        getAddress(cameraPosition.target.latitude, cameraPosition.target.longitude);
    }


}

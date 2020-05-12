package www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.model.workshop_list;
import www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.pattern.workshop_adapter;

public class map_repair_location extends AppCompatActivity implements OnMapReadyCallback, OnCompleteListener {

    GoogleMap mGoogleMap;
    @BindView(R.id.workshop_list)
    RecyclerView workshopList;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_repair_location);
        ButterKnife.bind(this);

        //SUPPORT MAP
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        //LOCATION PREMISSION
        getLocationPermission();

        //GET DATA
        getData();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;

        //GET MY LOCATION
        getloca();
    }

    //IF GRANTED PREMISSION DONE GET CURRENT LOCATION
    public void getloca() {
        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

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

            //MARKER ANIMATION
            View map = findViewById(R.id.map);
            new utils().marker_animation(current_lat, current_lng, map, mGoogleMap);

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

    //GET DATA
    void  getData()
    {
        ArrayList<workshop_list> arrayList = new ArrayList<>();
        arrayList.add(new workshop_list("1","ahmed mohamed","42 el kalefa almamoun",5,"25 review"));
        arrayList.add(new workshop_list("1","ibraheem mohamed","42 el kalefa almamoun",3,"21 review"));
        arrayList.add(new workshop_list("1","ahmed othman","42 el kalefa almamoun",1,"40 review"));

        new utils_adapter().Horozintal(workshopList,new workshop_adapter(map_repair_location.this,arrayList),map_repair_location.this);
    }


}

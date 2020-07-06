package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_rate.controller.industerial_rate;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_service_details.controller.home_service_details;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.model.twenty_fourDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.model.workshop_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.pattern.workshop_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.model.twenty_fourRootClass;

public class map_repair_location extends AppCompatActivity implements OnMapReadyCallback, OnCompleteListener, NetworkInterface {

    GoogleMap mGoogleMap;
    @BindView(R.id.workshop_list)
    RecyclerView workshopList;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    twenty_fourRootClass twenty_fourRootClass;
    twenty_fourDatum[] data;

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

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(map_repair_location.this);


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

            //CALL API
            new Apicalls(this, this).twenty_four("112.3333333", "12.26548488");

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
    void getData(JSONObject jsonObject) {

        ArrayList<workshop_list> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        twenty_fourRootClass = gson.fromJson("" + jsonObject, twenty_fourRootClass.class);

        if (twenty_fourRootClass.getStatus() == 0) {
            Toasty.error(map_repair_location.this, twenty_fourRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {
            data = twenty_fourRootClass.getData();
            for (int index = 0; index < data.length; index++) {
                arrayList.add(new workshop_list("" + data[index].getId(),
                        data[index].getName(), data[index].getAddress(),
                        data[index].getRate(), "" + data[index].getRatesCount()+" review",
                        data[index].getImage()));
            }

            new utils_adapter().Horozintal(workshopList, new workshop_adapter(map_repair_location.this, arrayList), map_repair_location.this);

        }
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        getData(model.getJsonObject());

        //CHECK IF DATA IS NULL
        if(data.length == 0)
        {
            Toasty.warning(map_repair_location.this, getString(R.string.no_work_shop_nearby), Toasty.LENGTH_LONG).show();
        }

    }

    @Override
    public void OnError(VolleyError error) {

    }
}

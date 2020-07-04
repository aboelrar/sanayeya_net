package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_track_user_location.cotroller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.directionhelpers.FetchURL;
import www.dm.sanayeya.net.utils.directionhelpers.TaskLoadedCallback;

import static com.google.android.gms.maps.model.JointType.ROUND;

/**
 * Created by Vishal on 10/20/2018.
 */

public class track_user_location extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback, OnCompleteListener {
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    Double address_lat, address_lng, winch_lat, winch_lng;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    String request_id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Marker marker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_user_location);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        address_lat = intent.getDoubleExtra("address_location_lat", 0.0);
        address_lng = intent.getDoubleExtra("address_location_lng", 0.0);
        winch_lat = intent.getDoubleExtra("winch_location_lat", 0.0);
        winch_lng = intent.getDoubleExtra("winch_location_lng", 0.0);

        place1 = new MarkerOptions().position(new LatLng(address_lat, address_lng)).title("Location 1");


        //CHANGE LOCATION LISTNERS
        on_changed_location();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");

        //SET FIRST LOCATION
        MarkerOptions marker_winch = new MarkerOptions().position(new LatLng(0.0, 0.0)).title("Location 2");
        marker = mMap.addMarker(marker_winch);
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.winch_location));

        //MAP PREMISSION OPEN SETTINGS
        getLocationPermission();

        //GET LOCATION
        getloca();


    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyAO-t7QFPoXpT27RJPn-FMn1I6gRHHYYPM";
        return url;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
        currentPolyline.setWidth(35);
        currentPolyline.setColor(getColor(R.color.f_color));


    }


    //IF GRANTED PREMISSION DONE GET CURRENT LOCATION
    public void getloca() {
        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
//            mMap.setMyLocationEnabled(true);
//            mMap.getUiSettings().setMyLocationButtonEnabled(false);
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



            mMap.clear();

            add_lat_lng(current_lat, current_lng);

        }
    }

    //ADD WINCH LAT AND LNG ON FIREBASE
    void add_lat_lng(double winch_lat, double winch_lng) {

        Map<String, String> data = new HashMap<>();
        data.put("lat", "" + winch_lat);
        data.put("lng", "" + winch_lng);
        data.put("status", "" + "1");

        db.collection("winch").document(request_id)
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("yreee", e.getLocalizedMessage());
                    }
                });

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


    //ON CHANGED LOCATION
    void on_changed_location() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    marker.remove();

                    add_lat_lng(location.getLatitude(), location.getLongitude());
                    place2 = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Location 2");

                    new FetchURL(track_user_location.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

                    //MOVE CAMERA
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude()))
                            .bearing(location.getBearing() - 80)
                            .zoom(35)
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    //SET FIRST LOCATION
                    MarkerOptions marker_winch = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Location 2");
                    marker = mMap.addMarker(marker_winch);
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.winch_location));

                    mMap.addMarker(place1);


                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    marker.remove();

                    add_lat_lng(location.getLatitude(), location.getLongitude());
                    place1 = new MarkerOptions().position(new LatLng(address_lat, address_lng)).title("Location 1");
                    place2 = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Location 2");

                    new FetchURL(track_user_location.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

                    //MOVE CAMERA
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude()))
                            .bearing(location.getBearing() - 80)
                            .zoom(35)
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    //SET FIRST LOCATION
                    MarkerOptions marker_winch = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Location 2");
                    marker = mMap.addMarker(marker_winch);
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.winch_location));

                    mMap.addMarker(place1);

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }

    }

}
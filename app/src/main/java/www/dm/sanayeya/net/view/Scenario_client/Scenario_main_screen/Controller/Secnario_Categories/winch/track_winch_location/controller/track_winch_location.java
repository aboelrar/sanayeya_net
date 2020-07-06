package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.track_winch_location.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.utils.directionhelpers.FetchURL;
import www.dm.sanayeya.net.utils.directionhelpers.TaskLoadedCallback;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_track_user_location.cotroller.track_user_location;

public class track_winch_location extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    Double address_lat, address_lng;
    Double winch_lat = 0.0;
    Double winch_lng = 0.0;
    Marker marker;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String request_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_winch_location);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        address_lat = new saved_data().order_lat(track_winch_location.this);
        address_lng = new saved_data().order_lng(track_winch_location.this);
        request_id = new saved_data().order_id(track_winch_location.this);


        //ALL FUNCTIONS DONE HERE
        all_finc();

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(track_winch_location.this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        //SET WINCH LOCATION THAT IS COMING FROM SERVER
        MarkerOptions marker_winch = new MarkerOptions().position(new LatLng(0.0, 0.0)).title("Location 2");
        marker = mMap.addMarker(marker_winch);
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.winch_location));


        //ADD MARKER FOR MY LOCATION
        place1 = new MarkerOptions().position(new LatLng(address_lat, address_lng)).title("Location 1");
        mMap.addMarker(place1);

        //MOVE CAMERA
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(address_lat, address_lng)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));


    }


    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
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

    //ALL FUCTIONS HERE
    void all_finc() {
        DocumentReference docRef = db.collection("winch").document(request_id);

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("tesseee", "Listen failed.", e);
                    return;
                }

                //CLEAR MAP
                marker.remove();

                String winch_lat_f = (String) snapshot.get("lat");
                winch_lat = Double.valueOf(winch_lat_f);

                //LNG IS
                String winch_lng_f = (String) snapshot.get("lng");
                winch_lng = Double.valueOf(winch_lng_f);

                //ADD ROUTING DRAWING
                place2 = new MarkerOptions().position(new LatLng(winch_lat, winch_lng)).title("Location 2");

                //ADD CUSTOM MARKERS
                marker = mMap.addMarker(place2);
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.winch_location));


                //ADD DILAOG FINSIHED
                String status = (String) snapshot.get("status");
                if (status.equals("0")) {
                    loading loading = new loading();
                    loading.dialog(track_winch_location.this, R.layout.request_finished, .80);
                }
            }

        });

    }

}

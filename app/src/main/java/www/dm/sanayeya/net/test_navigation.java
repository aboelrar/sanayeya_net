package www.dm.sanayeya.net;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.local_data.send_data;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.winch_main_screen;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

public class test_navigation extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener, View.OnClickListener {
    @BindView(R.id.startButton)
    Button startButton;
    @BindView(R.id.finish)
    Button finish;
    private MapView mapView;
    private MapboxMap mapboxMap;
    LocationComponent locationComponent;
    PermissionsManager permissionsManager;
    DirectionsRoute currentRoute;
    NavigationMapRoute navigationMapRoute;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Double address_lat, address_lng;
    String request_id;
    LocationManager locationManager;
    Boolean btn_status = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));

        setContentView(R.layout.test_navigation);
        ButterKnife.bind(this);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        address_lat = new saved_data().get_winch_owner_lat(test_navigation.this);
        address_lng = new saved_data().get_winch_owner_lng(test_navigation.this);
        request_id = new saved_data().get_winch_request_id(test_navigation.this);

        Log.e("latis", address_lat + "..." + address_lng);

        mapView.getMapAsync(test_navigation.this);

        startButton.setOnClickListener(this);
        finish.setOnClickListener(this);

        on_changed_location();


    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        this.mapboxMap.setMinZoomPreference(15);

        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enable_location(style);
                addDestinationIconLayer(style);
                get_both_directions();

            }
        });

    }

    private void addDestinationIconLayer(Style style) {
        style.addImage("destination-icon-id", BitmapFactory.decodeResource(this.
                getResources(), R.drawable.mapbox_marker_icon_default));

        GeoJsonSource jsonSource = new GeoJsonSource("destination-source-id");
        style.addSource(jsonSource);

        SymbolLayer symbolLayer = new SymbolLayer("destination-symbol-layer-id",
                "destination-source-id");
        symbolLayer.withProperties(iconImage("destination-icon-id"), iconAllowOverlap(true),
                iconIgnorePlacement(true));

        style.addLayer(symbolLayer);
    }


    private void enable_location(@NonNull Style loadedmapStyle) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            locationComponent = mapboxMap.getLocationComponent();


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            locationComponent.activateLocationComponent(this, loadedmapStyle);
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);


        } else {
            Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();

            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);

        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enable_location(mapboxMap.getStyle());
        } else {
            Toast.makeText(this, "" + getString(R.string.user_location_permission_not_granted), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

//    @Override
//    public boolean onMapClick(@NonNull LatLng point) {
//        Point point1 = Point.fromLngLat(point.getLongitude(), point.getLatitude());
//        Point point2 = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
//                locationComponent.getLastKnownLocation().getLatitude());
//
//        GeoJsonSource geoJsonSource = mapboxMap.getStyle().getSourceAs("destination-source-id");
//        if (geoJsonSource != null) {
//            geoJsonSource.setGeoJson(Feature.fromGeometry(point1));
//        }
//
//        getRoute(point2, point1);
//
//        return false;
//    }

    private void getRoute(Point point2, Point point1) {

        NavigationRoute.builder(test_navigation.this).accessToken(Mapbox.getAccessToken())
                .origin(point2).destination(point1).build().getRoute(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                if (response.body() != null) {
                    currentRoute = response.body().routes().get(0);

                    if (navigationMapRoute != null) {
                        navigationMapRoute.removeRoute();

                    } else {
                        navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap, R.style.NavigationMapRoute);
                    }
                    navigationMapRoute.addRoute(currentRoute);
                }
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.startButton) {

            boolean simulateRoute = true;
            NavigationLauncherOptions options = NavigationLauncherOptions.builder().
                    directionsRoute(currentRoute).build();

            NavigationLauncher.startNavigation(test_navigation.this, options);
        } else if (view.getId() == R.id.finish) {

            btn_status = true;

            new AlertDialog.Builder(test_navigation.this)
                    .setTitle("Finish Request")
                    .setMessage("Are you sure you want to finish request?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            send_data.request_id(test_navigation.this, "0");

                            //FINISH ACTIVITY
                            startActivity(new Intent(test_navigation.this,winch_main_screen.class));
                            finish();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }
    }

    //ON CHANGED LOCATION
    void on_changed_location() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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

                    if (btn_status == false) {
                        add_lat_lng(location.getLatitude(), location.getLongitude());
                        Log.e("aaaaaaaa", location.getLatitude() + "");
                    } else {
                        update_status();
                    }


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
                    if (btn_status == false) {
                        add_lat_lng(location.getLatitude(), location.getLongitude());
                        Log.e("aaaaaaaa", location.getLatitude() + "");
                    } else {
                        update_status();
                    }

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

    void get_both_directions() {

        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(this);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    Point point1 = Point.fromLngLat(address_lng, address_lat);
                    Point point2 = Point.fromLngLat(location.getLongitude(), location.getLatitude());

                    GeoJsonSource geoJsonSource = mapboxMap.getStyle().getSourceAs("destination-source-id");
                    if (geoJsonSource != null) {
                        geoJsonSource.setGeoJson(Feature.fromGeometry(point1));
                    }

                    getRoute(point2, point1);
                }
            }
        });

    }

    //SET STATUS ZERO TO FINSIH REQUEST BY EDIT FIREBASE
    void update_status() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "" + "0");

        db.collection("winch")
                .document(request_id)
                .update(data);
    }

}
package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.VolleyError;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_chnage_password.controller.change_pass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_help.controller.help;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_terms.terms;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.categories;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_map_location.controller.choose_map_location;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_location.controller.winch_location;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.track_winch_location.controller.track_winch_location;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_choose_language.controller.choose_language;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_edit_profile.controller.edit_profile;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;


public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks, View.OnClickListener, NetworkInterface {

    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static int check_location = 0;
    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //CHECK GPS
        GpsCheck();

        //SET UP DRAWER
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, drawer);

        //SET ON CLICK LISTNER
        menu.setOnClickListener(this);

        if (new saved_data().order_id(MainActivity.this).equals("0")) {

            //ADD ALL CATEGORIES
            new utils().Replace_Fragment(new categories(), R.id.frag, this);
        } else {
            db.collection("winch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(MainActivity.this, track_winch_location.class));
                    } else {
                        //ADD ALL CATEGORIES
                        new utils().Replace_Fragment(new categories(), R.id.frag, MainActivity.this);
                    }
                }
            });


        }

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(MainActivity.this);

        new Apicalls(MainActivity.this, MainActivity.this)
                .change_language(new saved_data().get_lan(MainActivity.this));


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 0) {
            new utils().Replace_Fragment(new categories(), R.id.frag, this); //MAIN SCREEN
        } else if (position == 1) {
            new utils().Replace_Fragment(new change_pass(), R.id.frag, this); //CHANGE PASSWORD
        } else if (position == 2) {
            new utils().Replace_Fragment(new edit_profile(), R.id.frag, this); //HELP
        } else if (position == 3) {
            new utils().Replace_Fragment(new help(), R.id.frag, this); //HELP
        } else if (position == 4) {
            new utils().Replace_Fragment(new terms(), R.id.frag, this); //HELP
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.menu) {
            mNavigationDrawerFragment.openDrawer(); //MENU OPEN DRAWER
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigationDrawerFragment.closeDrawer();
    }

    @Override
    public void onBackPressed() {

        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {

            moveTaskToBack(true);
        }

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("conncetion", "no Connextion");
    }

    //GPS CHECK
    public void GpsCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }

    }

    //GPS
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.cant_detect))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.Accept), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                    }
                })
                .setNegativeButton(getString(R.string.reject), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        check_location = 1;
                        Toasty.error(MainActivity.this, getString(R.string.cant_detect)).show();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    //UPDATE LOCATION AFTER OPEN GBS
    protected void startLocationUpdates() {

        // Create the location request to start receiving updates
        final LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
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

        getFusedLocationProviderClient(MainActivity.this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {

                    }
                },
                Looper.myLooper());
    }



    @Override
    protected void onRestart() {
        super.onRestart();

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            startLocationUpdates();
            check_location = 0;
        } else {
            buildAlertMessageNoGps();
        }
    }

}
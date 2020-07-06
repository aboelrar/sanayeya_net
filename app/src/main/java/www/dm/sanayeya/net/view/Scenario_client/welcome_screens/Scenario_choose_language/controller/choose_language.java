package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_choose_language.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login;

public class choose_language extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.language)
    Spinner language;
    @BindView(R.id.apply)
    Button apply;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    Boolean mLocationPermissionsGranted = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_language);
        ButterKnife.bind(this);

        getLocationPermission(); //Location Permission

        add_spinner_data();  //SPINNER DATA

        //SET ON CLICK ITEM
        apply.setOnClickListener(this);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(choose_language.this);
    }

    //ADD DATA TO SPINNER
    void add_spinner_data()
    {
        //ADD DATA TO LIST
        List<String> arraylist = new ArrayList<>();
        arraylist.add("English");
        arraylist.add("Arabic");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arraylist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.apply)
        {
          startActivity(new Intent(choose_language.this, login.class));
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
}

package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_choose_language.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.VolleyError;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.local_data.send_data;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login;

public class choose_language extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

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
    void add_spinner_data() {
        //ADD DATA TO LIST
        List<String> arraylist = new ArrayList<>();
        arraylist.add("English");
        arraylist.add("العربية");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arraylist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(spinnerArrayAdapter);

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    //CHANGE LANGUAGE

                    send_data.send_lan(choose_language.this, "en");
                    new utils().set_language(new saved_data().get_lan(choose_language.this),
                            choose_language.this);
                } else if (i == 1) {
                    //CHANGE LANGUAGE

                    send_data.send_lan(choose_language.this, "ar");

                    new utils().set_language(new saved_data().get_lan(choose_language.this),
                            choose_language.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.apply) {
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

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        try {
            Toasty.success(choose_language.this, "" + model.getJsonObject().getString("message"), Toasty.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnError(VolleyError error) {
        Toasty.error(choose_language.this, "No Internet Connection", Toasty.LENGTH_LONG).show();
    }
}

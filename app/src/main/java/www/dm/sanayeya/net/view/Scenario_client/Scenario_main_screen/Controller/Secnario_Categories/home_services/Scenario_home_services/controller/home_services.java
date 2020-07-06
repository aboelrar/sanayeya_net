package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services.model.home_serviceDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services.model.home_serviceRootClass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.controller.home_services_compaines;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login;

public class home_services extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.service_type)
    Spinner serviceType;
    @BindView(R.id.find)
    Button find;
    TextView title;
    @BindView(R.id.back)
    ImageView back;
    home_serviceRootClass home_serviceRootClass;
    home_serviceDatum[] data;
    String home_service_id = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_services);
        ButterKnife.bind(this);

        //BAR TITLE
        title = findViewById(R.id.title);
        title.setText(R.string.home_ser_title);

        //CALL API
        new Apicalls(this, this).choose_home_service();

        //SET ON CLICK LISTNERS
        find.setOnClickListener(this);
        back.setOnClickListener(this);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(home_services.this);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.find) {

            if (!home_service_id.equals("0")) {
                Intent intent = new Intent(home_services.this, home_services_compaines.class);
                intent.putExtra("service_id", home_service_id);
                startActivity(intent);
            }

        } else if (view.getId() == R.id.back) {
            finish();
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {


        Gson gson = new Gson();
        home_serviceRootClass = gson.fromJson("" + model.getJsonObject(), home_serviceRootClass.class);

        if (home_serviceRootClass.getStatus() == 0) {
            Toasty.error(home_services.this, home_serviceRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {

            //ADD DATA TO ARRAY LIST
            List<String> arraylist = new ArrayList<>();
            List<String> arraylist_id = new ArrayList<>();

            data = home_serviceRootClass.getData();

            for (int index = 0; index < data.length; index++) {
                arraylist.add(data[index].getName());
                arraylist_id.add("" + data[index].getId());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, arraylist); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            serviceType.setAdapter(spinnerArrayAdapter);

            //SET ON CLICK LISTNERS SPINNER
            serviceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    home_service_id = "" + arraylist_id.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }


    }

    @Override
    public void OnError(VolleyError error) {

    }
}

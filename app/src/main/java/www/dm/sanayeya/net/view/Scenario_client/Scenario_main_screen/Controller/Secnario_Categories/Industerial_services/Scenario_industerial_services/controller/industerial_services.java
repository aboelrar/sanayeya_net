package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.controller;

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
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.controller.industerial_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.model.cars_modelDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.model.cars_modelRootClass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.model.provinceDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.model.provinceRootClass;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup;

public class industerial_services extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.choose_your_province)
    Spinner chooseYourProvince;
    @BindView(R.id.choose_the_car_model)
    Spinner chooseTheCarModel;
    @BindView(R.id.choose_the_workshop_type)
    Spinner chooseTheWorkshopType;
    @BindView(R.id.find)
    Button find;
    @BindView(R.id.back)
    ImageView back;
    provinceRootClass provinceRootClass;
    provinceDatum[] provinceData;
    cars_modelRootClass cars_modelRootClass;
    cars_modelDatum[] cars_modelData;
    String province_id_is = "x";
    String car_model_id_is = "x";
    String workshop_id_is = "x";
    int check_api = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.industerial_services);
        ButterKnife.bind(this);

        //SET TITLE NAME
        title.setText(R.string.industerial_ser);

        //CALL PROVINCE API
        new Apicalls(this, this).province();

        //SET ON CLICK LISTNERS
        find.setOnClickListener(this);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.find) {
            if (province_id_is.equals("x") && (car_model_id_is.equals("x") && workshop_id_is.equals("x"))) {
                Toasty.warning(industerial_services.this, "Please wait until data loading...", Toasty.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, industerial_list.class);
                intent.putExtra("province_id", province_id_is);
                intent.putExtra("car_model_id", car_model_id_is);
                intent.putExtra("work_shop_id", workshop_id_is);
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


        if (check_api == 0) {

            //GET PROVINCE DATA
            province_data(gson, model.getJsonObject());

            //CALL CARS_MODEL API
            new Apicalls(this, this).car_models();

        } else if (check_api == 1) {

            //GET CARS MODEL
            get_cars_model(gson, model.getJsonObject());

            //CALL CARS_MODEL API
            new Apicalls(this, this).work_shops_types();

        } else if (check_api == 2) {
            //GET WORK SHOPS
            get_work_shops(gson, model.getJsonObject());

        }


    }

    @Override
    public void OnError(VolleyError error) {

    }

    //GET PROVINCE DATA
    void province_data(Gson gson, JSONObject object) {

        provinceRootClass = gson.fromJson("" + object, provinceRootClass.class);

        if (provinceRootClass.getStatus() == 0) {
            Toasty.error(industerial_services.this, provinceRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {
            //ADD DATA TO CHOOSE YOUR PROVINCE
            List<String> province_list = new ArrayList<>();
            List<String> province_id = new ArrayList<>();

            provinceData = provinceRootClass.getData();
            for (int index = 0; index < provinceData.length; index++) {
                province_list.add(provinceData[index].getName());
                province_id.add("" + provinceData[index].getId());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, province_list); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            chooseYourProvince.setAdapter(spinnerArrayAdapter);

            check_api = 1; //CHANGE TO GET CAR MODELS

            //SET ON CLICK SPINNER
            chooseYourProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    province_id_is = province_id.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }

    }


    //GET CARS MODEL
    void get_cars_model(Gson gson, JSONObject jsonObject) {
        cars_modelRootClass = gson.fromJson("" + jsonObject, cars_modelRootClass.class);

        if (cars_modelRootClass.getStatus() == 0) {
            Toasty.error(industerial_services.this, provinceRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {
            ArrayList<String> car_model_list = new ArrayList<>();
            ArrayList<String> car_model_id = new ArrayList<>();

            cars_modelData = cars_modelRootClass.getData();

            for (int index = 0; index < cars_modelData.length; index++) {
                car_model_list.add(cars_modelData[index].getName());
                car_model_id.add("" + cars_modelData[index].getId());
            }

            ArrayAdapter<String> spinner1ArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, car_model_list); //selected item will look like a spinner set from XML
            spinner1ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            chooseTheCarModel.setAdapter(spinner1ArrayAdapter);

            check_api = 2; //CHANGE TO GET CAR MODELS

            //SET ON CLICK LISTNERS
            chooseTheCarModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    car_model_id_is = car_model_id.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }
    }


    //GET WORK_SHOP_TYPES
    void get_work_shops(Gson gson, JSONObject jsonObject) {
        cars_modelRootClass = gson.fromJson("" + jsonObject, cars_modelRootClass.class);

        if (cars_modelRootClass.getStatus() == 0) {
            Toasty.error(industerial_services.this, provinceRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {
            ArrayList<String> workshop_list = new ArrayList<>();
            ArrayList<String> workshop_id = new ArrayList<>();

            cars_modelData = cars_modelRootClass.getData();

            for (int index = 0; index < cars_modelData.length; index++) {
                workshop_list.add(cars_modelData[index].getName());
                workshop_id.add("" + cars_modelData[index].getId());
            }

            ArrayAdapter<String> spinner2ArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, workshop_list); //selected item will look like a spinner set from XML
            spinner2ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            chooseTheWorkshopType.setAdapter(spinner2ArrayAdapter);

            //SET ON CLICK LISTNERS
            chooseTheWorkshopType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    workshop_id_is = workshop_id.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

}

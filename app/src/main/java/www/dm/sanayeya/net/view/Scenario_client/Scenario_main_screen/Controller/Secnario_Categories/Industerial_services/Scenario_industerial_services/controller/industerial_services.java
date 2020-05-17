package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.controller.industerial_list;

public class industerial_services extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.industerial_services);
        ButterKnife.bind(this);

        //SET TITLE NAME
        title.setText(R.string.industerial_ser);

        //PUT DATA TO SNIPPER
        add_spinner_data();

        //SET ON CLICK LISTNERS
        find.setOnClickListener(this);
        back.setOnClickListener(this);
    }


    //ADD DATA TO SPINNER
    void add_spinner_data() {
        //ADD DATA TO CHOOSE YOUR PROVINCE
        List<String> arraylist = new ArrayList<>();
        arraylist.add("Cairo");
        arraylist.add("Giza");
        arraylist.add("Ismalia");
        arraylist.add("Suez");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arraylist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseYourProvince.setAdapter(spinnerArrayAdapter);


        //ADD DATA TO choose The CarModel
        List<String> arraylist1 = new ArrayList<>();
        arraylist1.add("korean");
        arraylist1.add("china");
        arraylist1.add("USA");

        ArrayAdapter<String> spinner1ArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arraylist1); //selected item will look like a spinner set from XML
        spinner1ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseTheCarModel.setAdapter(spinner1ArrayAdapter);


        //ADD DATA TO choose The WorkshopType
        List<String> arraylist2 = new ArrayList<>();
        arraylist2.add("Car electraion");
        arraylist2.add("Car wash");
        arraylist2.add("The Switch");

        ArrayAdapter<String> spinner2ArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arraylist2); //selected item will look like a spinner set from XML
        spinner2ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseTheWorkshopType.setAdapter(spinner2ArrayAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.find) {
            startActivity(new Intent(this, industerial_list.class));
        }
        else if(view.getId() == R.id.back)
        {
            finish();
        }
    }
}

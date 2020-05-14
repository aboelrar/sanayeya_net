package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.controller.home_services_compaines;

public class home_services extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.service_type)
    Spinner serviceType;
    @BindView(R.id.find)
    Button find;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_services);
        ButterKnife.bind(this);

        //BAR TITLE
        title = findViewById(R.id.title);
        title.setText(R.string.home_ser_title);

        //SPINNER DATA
        add_spinner_data();

        //SET ON CLICK LISTNERS
        find.setOnClickListener(this);


    }

    //ADD DATA TO SPINNER
    void add_spinner_data()
    {
        //ADD DATA TO LIST
        List<String> arraylist = new ArrayList<>();
        arraylist.add("Car electration");
        arraylist.add("Car wash");
        arraylist.add("The switch");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arraylist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceType.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.find)
        {
          startActivity(new Intent(home_services.this, home_services_compaines.class));
        }
    }
}

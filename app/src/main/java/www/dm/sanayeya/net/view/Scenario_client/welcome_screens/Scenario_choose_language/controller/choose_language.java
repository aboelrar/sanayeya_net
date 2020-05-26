package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_choose_language.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login;

public class choose_language extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.language)
    Spinner language;
    @BindView(R.id.apply)
    Button apply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_language);
        ButterKnife.bind(this);

        add_spinner_data();  //SPINNER DATA

        //SET ON CLICK ITEM
        apply.setOnClickListener(this);
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
}

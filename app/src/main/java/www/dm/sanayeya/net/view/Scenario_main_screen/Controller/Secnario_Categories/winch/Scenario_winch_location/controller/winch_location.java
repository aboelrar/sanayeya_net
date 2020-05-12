package www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_location.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_map_location.controller.choose_map_location;
import www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.controller.winch_price;

public class winch_location extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.find)
    Button find;
    @BindView(R.id.car_location)
    TextView carLocation;
    @BindView(R.id.car_destination)
    TextView carDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winch_location);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNER
        find.setOnClickListener(this);
        carLocation.setOnClickListener(this);
        carDestination.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.find) {
            startActivity(new Intent(winch_location.this, winch_price.class));
        }
        else if(view.getId() == R.id.car_location)
        {
            startActivityForResult(new Intent(this, choose_map_location.class),1);
        }
        else if(view.getId() == R.id.car_destination)
        {
            startActivityForResult(new Intent(this, choose_map_location.class),2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                carLocation.setText(result);
            }
        } else  if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                carDestination.setText(result);
            }
        }
    }
}

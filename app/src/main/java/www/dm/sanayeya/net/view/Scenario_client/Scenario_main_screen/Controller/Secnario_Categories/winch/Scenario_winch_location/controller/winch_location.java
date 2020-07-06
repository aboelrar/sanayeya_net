package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_location.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_map_location.controller.choose_map_location;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.controller.winch_price;

public class winch_location extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.find)
    Button find;
    @BindView(R.id.car_location)
    TextView carLocation;
    @BindView(R.id.car_destination)
    TextView carDestination;
    @BindView(R.id.title)
    TextView title;
    String location_lat, location_lng;
    String destination_lat, destination_lng;
    String first_result;
    String second_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winch_location);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNER
        find.setOnClickListener(this);
        carLocation.setOnClickListener(this);
        carDestination.setOnClickListener(this);

        //SET TITLE TEXT
        title.setText(getResources().getString(R.string.winch_request));

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(winch_location.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.find) {
            go_to_winch();
        } else if (view.getId() == R.id.car_location) {
            startActivityForResult(new Intent(this, choose_map_location.class), 1);
        } else if (view.getId() == R.id.car_destination) {
            startActivityForResult(new Intent(this, choose_map_location.class), 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                first_result = data.getStringExtra("result");
                location_lat = data.getStringExtra("lat");
                location_lng = data.getStringExtra("lng");
                carLocation.setText(first_result);
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                second_result = data.getStringExtra("result");
                destination_lat = data.getStringExtra("lat");
                destination_lng = data.getStringExtra("lng");
                carDestination.setText(second_result);
            }
        }
    }

    //GO TO WINCH PRICE
    void go_to_winch() {
        if ((location_lng == null) || (location_lat == null) || (first_result == null) || (destination_lat == null) || (destination_lng == null) || (second_result == null)) {
            Toasty.warning(this, getResources().getString(R.string.complete_all_info), Toasty.LENGTH_LONG).show();
        } else {

            Log.e("lat_is", location_lat + "..." + location_lng);

            Intent intent = new Intent(winch_location.this, winch_price.class);
            intent.putExtra("location_lat", location_lat);
            intent.putExtra("location_lng", location_lng);
            intent.putExtra("first_result", first_result);
            intent.putExtra("destination_lat", destination_lat);
            intent.putExtra("destination_lng", destination_lng);
            intent.putExtra("second_result", second_result);
            startActivity(intent);
        }
    }
}

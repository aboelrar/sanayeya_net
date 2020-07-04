package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.send_data;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.model.winch_detailsDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.model.winch_detailsRootClass;

public class winch_price extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.confirm)
    Button confirm;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.min)
    TextView min;
    @BindView(R.id.price)
    TextView price;
    winch_detailsRootClass winch_detailsRootClass;
    www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.model.winch_detailsDatum winch_detailsDatum;
    Boolean status = false;
    @BindView(R.id.loading)
    ProgressBar loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winch_price);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        confirm.setOnClickListener(this);

        //GET DATA FROM PREVIOUS PAGE
        String location_lat = getIntent().getStringExtra("location_lat");
        String location_lng = getIntent().getStringExtra("location_lng");
        String first_result = getIntent().getStringExtra("first_result");
        String destination_lat = getIntent().getStringExtra("destination_lat");
        String destination_lng = getIntent().getStringExtra("destination_lng");
        String second_result = getIntent().getStringExtra("second_result");

        //CALL API
        new Apicalls(this, this).request_winch(location_lat, location_lng, first_result,
                destination_lat, destination_lng, second_result);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.confirm) {
            new utils().set_dialog(winch_price.this);
            new Apicalls(winch_price.this, this).confirm_order("" + winch_detailsDatum.getId());
//            startActivity(new Intent(winch_price.this, track_user_location.class));
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        if (status == false) {
            loading.setVisibility(View.GONE);
            get_request_data(model.getJsonObject());
            status = true;
        } else {
            new utils().dismiss_dialog(winch_price.this);
            Toasty.success(winch_price.this, "order confirmation successfully", Toasty.LENGTH_LONG).show();

            //SAVE DATA
            send_data.set_order_id(winch_price.this, "" + winch_detailsDatum.getId()); //ADD ID
            send_data.set_order_lat(winch_price.this, winch_detailsDatum.getLocationLat()); //ADD LAT
            send_data.set_order_lng(winch_price.this, winch_detailsDatum.getLocationLng()); //ADD LNG

        }


    }

    @Override
    public void OnError(VolleyError error) {

    }


    //GET PRICE DATA
    void get_request_data(JSONObject jsonObject) {
        Gson gson = new Gson();
        winch_detailsRootClass = gson.fromJson("" + jsonObject, winch_detailsRootClass.class);

        if (winch_detailsRootClass.getStatus() == 0) {
            Toasty.warning(this, "" + winch_detailsRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {

            winch_detailsDatum = winch_detailsRootClass.getData();

            //SET DATA
            address.setText(winch_detailsDatum.getLocationAddress());
            time.setText(winch_detailsDatum.getArrivedAt());
            min.setText("in " + winch_detailsDatum.getEstiamteTime());
            price.setText(winch_detailsDatum.getCost() + "$");
        }
    }

}

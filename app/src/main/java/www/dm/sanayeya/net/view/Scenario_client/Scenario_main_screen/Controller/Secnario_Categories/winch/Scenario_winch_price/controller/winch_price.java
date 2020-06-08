package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_payment.controller.payment_method;
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
            startActivity(new Intent(winch_price.this, payment_method.class));
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Gson gson = new Gson();
        winch_detailsRootClass = gson.fromJson("" + model.getJsonObject(), winch_detailsRootClass.class);

       if(winch_detailsRootClass.getStatus() == 0)
       {
           Toasty.warning(this,""+winch_detailsRootClass.getMessage(),Toasty.LENGTH_LONG).show();
       }
       else {

           winch_detailsDatum = winch_detailsRootClass.getData();

           //SET DATA
           address.setText(winch_detailsDatum.getAddress());
           time.setText(winch_detailsDatum.getArrivedAt());
           min.setText("in " + winch_detailsDatum.getEstiamteTime());
           price.setText(winch_detailsDatum.getCost() + "$");
       }



    }

    @Override
    public void OnError(VolleyError error) {

    }
}

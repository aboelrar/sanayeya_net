package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.model.companies_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.model.home_service_dataDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.model.home_service_dataRootClass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.pattern.compaines_adapter;

public class home_services_compaines extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.companies_list)
    RecyclerView companiesList;
    @BindView(R.id.back)
    ImageView back;
    home_service_dataRootClass home_service_dataRootClass;
    home_service_dataDatum[] home_service_dataDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_services_compaines);
        ButterKnife.bind(this);

        //SET TITLE NAME
        title.setText(R.string.home_ser_title);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);

        //CALL API
        new Apicalls(this, this).home_service_data(getIntent().getStringExtra("service_id"));

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Toast.makeText(this, "" + model.getJsonObject(), Toast.LENGTH_SHORT).show();

        Gson gson = new Gson();
        home_service_dataRootClass = gson.fromJson("" + model.getJsonObject(), home_service_dataRootClass.class);

        if (home_service_dataRootClass.getStatus() == 0) {
            Toasty.error(home_services_compaines.this, home_service_dataRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {
            ArrayList<companies_list> arrayList = new ArrayList<>();

            //ADD DATA TO ADAPTER
            home_service_dataDatum = home_service_dataRootClass.getData();

            for (int index = 0; index < home_service_dataDatum.length; index++) {

                arrayList.add(new companies_list("" + home_service_dataDatum[index].getId(),
                        "" + home_service_dataDatum[index].getName(),
                        "" + home_service_dataDatum[index].getRatesCount() + " review",
                        home_service_dataDatum[index].getRate(),
                        home_service_dataDatum[index].getImage(),
                        home_service_dataDatum[index].getDesc()));
            }

            new utils_adapter().Adapter(companiesList, new compaines_adapter(this, arrayList), this);

        }

    }

    @Override
    public void OnError(VolleyError error) {

    }
}

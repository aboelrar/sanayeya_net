package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.model.industerial_model_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.model.industerial_serviceDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.model.industerial_serviceRootClass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.pattern.industerial_adapter;

public class industerial_list extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.industerial_list)
    ShimmerRecyclerView industerialList;
    @BindView(R.id.back)
    ImageView back;
    industerial_serviceRootClass industerial_serviceRootClass;
    industerial_serviceDatum[] industerial_serviceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.industerial_list);
        ButterKnife.bind(this);

        //SET TITLE NAME
        title.setText(R.string.industerial_ser);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);

        //GET DATA FROM PREVIOUS PAGE
        String province_id = getIntent().getStringExtra("province_id");
        String car_model_id = getIntent().getStringExtra("car_model_id");
        String workshop_id = getIntent().getStringExtra("work_shop_id");

        //CALL API
        new Apicalls(this,this).industerial_services_list(province_id,car_model_id,workshop_id);

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

        get_data(model.getJsonObject());
    }

    @Override
    public void OnError(VolleyError error) {

    }

    void get_data(JSONObject jsonObject) {

        Gson gson = new Gson();
        industerial_serviceRootClass = gson.fromJson("" + jsonObject, industerial_serviceRootClass.class);

        if (industerial_serviceRootClass.getStatus() == 0) {
            Toasty.error(industerial_list.this, industerial_serviceRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {

            ArrayList<industerial_model_list> arrayList = new ArrayList<>();

            industerial_serviceData = industerial_serviceRootClass.getData();
            for (int index = 0; index < industerial_serviceData.length; index++)
            {
            arrayList.add(new industerial_model_list(""+industerial_serviceData[index].getId(),
                    industerial_serviceData[index].getName(),
                    ""+industerial_serviceData[index].getRatesCount()+" review",
                    industerial_serviceData[index].getRate(),
                    industerial_serviceData[index].getDesc(),
                    industerial_serviceData[index].getImage()));
            }

            new utils_adapter().Adapter(industerialList, new industerial_adapter(this, arrayList), this);

        }
    }
}

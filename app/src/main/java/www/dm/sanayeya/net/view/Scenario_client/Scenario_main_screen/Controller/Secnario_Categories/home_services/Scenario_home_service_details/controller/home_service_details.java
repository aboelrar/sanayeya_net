package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_service_details.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
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
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_service_details.model.home_service_detailsDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_service_details.model.home_service_detailsRateUser;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_service_details.model.home_service_detailsRootClass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_service_details.pattern.reviews_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.model.review_list;


public class home_service_details extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.ratings)
    RatingBar ratings;
    @BindView(R.id.review_num)
    TextView reviewNum;
    @BindView(R.id.reviews_list)
    RecyclerView reviewsList;
    @BindView(R.id.back)
    ImageView back;
    home_service_detailsRootClass home_service_detailsRootClass;
    home_service_detailsDatum data;
    home_service_detailsRateUser[] home_service_detailsRateUsers;
    @BindView(R.id.com_name)
    ImageView com_image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.desc)
    ReadMoreTextView desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_service_details);
        ButterKnife.bind(this);

        new Apicalls(this, this).home_service_details(getIntent().getStringExtra("company_id"));

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);

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

        Gson gson = new Gson();
        home_service_detailsRootClass = gson.fromJson("" + model.getJsonObject(), home_service_detailsRootClass.class);

        if (home_service_detailsRootClass.getStatus() == 0) {
            Toasty.error(home_service_details.this, home_service_detailsRootClass.getMessage(), Toasty.LENGTH_LONG).show();

        } else {
            //GET DATA
            data = home_service_detailsRootClass.getData();

            //SET DATA
            name.setText(data.getName());
            desc.setText(data.getDesc());
            ratings.setRating(data.getRate());
            reviewNum.setText("" + data.getRatesCount());
            Glide.with(this).load(data.getImage()).into(com_image);

            //GET RATE
            ArrayList<review_list> rate_List = new ArrayList<>();
            home_service_detailsRateUsers = data.getRateUsers();


            for (int index = 0; index < home_service_detailsRateUsers.length; index++) {
                rate_List.add(new review_list("" + home_service_detailsRateUsers[index].getId(),
                        home_service_detailsRateUsers[index].getName(),
                        home_service_detailsRateUsers[index].getComment(),
                        home_service_detailsRateUsers[index].getCreatedAt(),
                        home_service_detailsRateUsers[index].getRate()));
            }

            new utils_adapter().Adapter(reviewsList,new reviews_adapter(this,rate_List),this);

        }
    }

    @Override
    public void OnError(VolleyError error) {

    }
}

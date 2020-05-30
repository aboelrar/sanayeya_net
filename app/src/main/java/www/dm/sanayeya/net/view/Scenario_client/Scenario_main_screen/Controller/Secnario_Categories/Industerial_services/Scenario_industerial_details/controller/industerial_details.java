package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
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
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.model.industerial_service_detailsDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.model.industerial_service_detailsRateUser;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.model.industerial_service_detailsRootClass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.model.review_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.pattern.reviews_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_rate.controller.industerial_rate;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.controller.workshop_details;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_rate.controller.workshop_rate;

public class industerial_details extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.ratings)
    RatingBar ratings;
    @BindView(R.id.review_num)
    TextView reviewNum;
    @BindView(R.id.reviews_list)
    RecyclerView reviewsList;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.industerial_img)
    ImageView industerialImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.desc)
    ReadMoreTextView desc;
    industerial_service_detailsRootClass industerial_service_detailsRootClass;
    industerial_service_detailsDatum industerial_service_detailsData;
    industerial_service_detailsRateUser[] industerial_service_detailsRateUsers;
    @BindView(R.id.check_review)
    TextView checkReview;
    @BindView(R.id.go_rate)
    TextView goRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.industerial_details);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        } else if (view.getId() == R.id.go_rate) {
            Intent intent = new Intent(industerial_details.this, industerial_rate.class);
            intent.putExtra("shop_id", getIntent().getStringExtra("industerial_id"));
            intent.putExtra("name", industerial_service_detailsData.getName());
            intent.putExtra("image", industerial_service_detailsData.getImage());
            startActivity(intent);
        }
    }

    @Override
    public void OnStart() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void OnResponse(ResponseModel model) {

        set_data(model.getJsonObject());

        //GO TO RATE
        goRate.setOnClickListener(this);

    }

    @Override
    public void OnError(VolleyError error) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void set_data(JSONObject jsonObject) {
        Gson gson = new Gson();
        industerial_service_detailsRootClass = gson.fromJson("" + jsonObject, industerial_service_detailsRootClass.class);

        if (industerial_service_detailsRootClass.getStatus() == 0) {
            Toasty.error(industerial_details.this, industerial_service_detailsRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {
            industerial_service_detailsData = industerial_service_detailsRootClass.getData();


            //SET DATA
            name.setText("" + industerial_service_detailsData.getName());
            desc.setText("" + industerial_service_detailsData.getDesc());
            ratings.setRating(industerial_service_detailsData.getRate());

            String reviews = "<font color=#808080>Based on " + industerial_service_detailsData.getRatesCount() + " </font> <font color=#F8971C> Reviews</font>"; //SET TEXT COLOR
            reviewNum.setText(Html.fromHtml(reviews, Html.FROM_HTML_MODE_LEGACY));

            Glide.with(this).load(industerial_service_detailsData.getImage()).into(industerialImg);


            //GET RATE
            ArrayList<review_list> rate_List = new ArrayList<>();
            industerial_service_detailsRateUsers = industerial_service_detailsData.getRateUsers();

            if (industerial_service_detailsRateUsers.length != 0) {

                for (int index = 0; index < industerial_service_detailsRateUsers.length; index++) {
                    rate_List.add(new review_list("" + industerial_service_detailsRateUsers[index].getId(),
                            industerial_service_detailsRateUsers[index].getName(),
                            industerial_service_detailsRateUsers[index].getComment(),
                            industerial_service_detailsRateUsers[index].getCreatedAt(),
                            industerial_service_detailsRateUsers[index].getRate()));
                }

                new utils_adapter().Adapter(reviewsList, new reviews_adapter(this, rate_List), this);
            } else {
                checkReview.setVisibility(View.VISIBLE); //SET VISBILITY
            }


        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        //CALL API
        new Apicalls(this, this).industerial_details(getIntent().getStringExtra("industerial_id"));

    }
}

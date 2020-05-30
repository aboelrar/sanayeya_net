package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.controller;

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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.controller.map_repair_location;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.model.review_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.model.twenty_four_detailsDatum;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.model.twenty_four_detailsRateUser;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.model.twenty_four_detailsRootClass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.pattern.reviews_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_rate.controller.workshop_rate;

public class workshop_details extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

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
    twenty_four_detailsRootClass twenty_four_detailsRootClass;
    twenty_four_detailsDatum twenty_four_detailsDatum;
    twenty_four_detailsRateUser[] twenty_four_detailsRateUsers;
    @BindView(R.id.check_review)
    TextView checkReview;
    @BindView(R.id.go_rate)
    TextView goRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workshop_details);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        } else if (view.getId() == R.id.go_rate) {
            Intent intent = new Intent(workshop_details.this, workshop_rate.class);
            intent.putExtra("shop_id", getIntent().getStringExtra("shop_id"));
            intent.putExtra("name", twenty_four_detailsDatum.getName());
            intent.putExtra("image", twenty_four_detailsDatum.getImage());
            startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void OnStart() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void OnResponse(ResponseModel model) {

        Gson gson = new Gson();
        twenty_four_detailsRootClass = gson.fromJson("" + model.getJsonObject(), twenty_four_detailsRootClass.class);

        if (twenty_four_detailsRootClass.getStatus() == 0) {
            Toasty.error(workshop_details.this, twenty_four_detailsRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else {
            twenty_four_detailsDatum = twenty_four_detailsRootClass.getData();

            //SET DATA
            name.setText("" + twenty_four_detailsDatum.getName());
            desc.setText("" + twenty_four_detailsDatum.getDesc());
            ratings.setRating(twenty_four_detailsDatum.getRate());

            String reviews = "<font color=#808080>Based on " + twenty_four_detailsDatum.getRatesCount() + " </font> <font color=#F8971C> Reviews</font>"; //SET TEXT COLOR
            reviewNum.setText(Html.fromHtml(reviews, Html.FROM_HTML_MODE_LEGACY));

            Glide.with(this).load(twenty_four_detailsDatum.getImage()).into(industerialImg);


            //GET RATE
            ArrayList<review_list> rate_List = new ArrayList<>();
            twenty_four_detailsRateUsers = twenty_four_detailsDatum.getRateUsers();

            if (twenty_four_detailsRateUsers.length != 0) {

                for (int index = 0; index < twenty_four_detailsRateUsers.length; index++) {
                    rate_List.add(new review_list("" + twenty_four_detailsRateUsers[index].getId(),
                            twenty_four_detailsRateUsers[index].getName(),
                            twenty_four_detailsRateUsers[index].getComment(),
                            twenty_four_detailsRateUsers[index].getCreatedAt(),
                            twenty_four_detailsRateUsers[index].getRate()));
                }

                new utils_adapter().Adapter(reviewsList, new reviews_adapter(this, rate_List), this);
            } else {
                checkReview.setVisibility(View.VISIBLE); //SET VISBILITY
            }

            //SET ON CLICK LISTNERS
            goRate.setOnClickListener(this);


        }
    }

    @Override
    public void OnError(VolleyError error) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        //CALL API
        new Apicalls(this, this).twenty_four_details(getIntent().getStringExtra("shop_id"));

    }
}

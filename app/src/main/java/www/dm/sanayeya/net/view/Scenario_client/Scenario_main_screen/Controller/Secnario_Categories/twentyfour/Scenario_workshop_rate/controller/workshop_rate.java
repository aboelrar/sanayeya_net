package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_rate.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.controller.workshop_details;

public class workshop_rate extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.shop_rate)
    ImageView shopRate;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.rating)
    RatingBar rating;
    @BindView(R.id.write_feedback)
    EditText writeFeedback;
    @BindView(R.id.submit)
    Button submit;
    String rate = "nodata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workshop_rate);
        ButterKnife.bind(this);

        //  SET DATA TEXT
        title.setText(getString(R.string.feedback));
        shopName.setText(getIntent().getStringExtra("name"));
        Glide.with(this).load(getIntent().getStringExtra("image")).into(shopRate);

        //SET RATING BAR
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating_num = Math.round(v);
                rate = "" + rating_num;
            }
        });

        //SET ON CLICK LISTNERS
        submit.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        } else if (view.getId() == R.id.submit) {
            call_api();
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        try {

            //DISMISS DIALOG
            new utils().dismiss_dialog(this);

            Toasty.success(this, "" + model.getJsonObject().getString("message")).show();
            writeFeedback.setText("");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnError(VolleyError error) {

    }

    //CALL API
    void call_api() {
        if (rate.equals("nodata")) {
            Toasty.warning(this, getString(R.string.please_rate), Toasty.LENGTH_LONG).show();
        } else {

            //CALL DIALOG
            new utils().set_dialog(this);

            new Apicalls(this, this).workshop_rate(getIntent().getStringExtra("shop_id"), rate, writeFeedback.getText().toString());
        }
    }
}

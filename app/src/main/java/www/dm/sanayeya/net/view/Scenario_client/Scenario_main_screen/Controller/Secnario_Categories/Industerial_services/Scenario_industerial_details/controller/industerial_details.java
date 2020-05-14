package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.model.review_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.pattern.reviews_adapter;

public class industerial_details extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ratings)
    RatingBar ratings;
    @BindView(R.id.review_num)
    TextView reviewNum;
    @BindView(R.id.doc_desc)
    ReadMoreTextView docDesc;
    @BindView(R.id.reviews_list)
    RecyclerView reviewsList;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.industerial_details);
        ButterKnife.bind(this);

        getData(); //GET DATA

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);

    }

    //GET DATA
    void getData() {
        ArrayList<review_list> arrayList = new ArrayList<>();

        arrayList.add(new review_list("1", "ahmed mohamed", "good one fixed my car", "4 days ago", 5));
        arrayList.add(new review_list("1", "ibraheem mohamed", "good one fixed my car", "10 days ago", 5));
        arrayList.add(new review_list("1", "ahmed ibraheem", "not good", "20 days ago", 3));
        arrayList.add(new review_list("1", "ahmed mohamed", "good one fixed my car", "4 days ago", 5));

        new utils_adapter().Adapter(reviewsList, new reviews_adapter(this, arrayList), this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        }
    }
}

package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.model.companies_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.pattern.compaines_adapter;

public class home_services_compaines extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.companies_list)
    RecyclerView companiesList;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_services_compaines);
        ButterKnife.bind(this);

        //SET TITLE NAME
        title.setText(R.string.home_ser_title);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);

        //GET DATA
        getData();

    }

    //GET DATA
    void getData() {
        ArrayList<companies_list> arrayList = new ArrayList<>();

        arrayList.add(new companies_list("1", "ahmed mohamed", "25 reviews", 5));
        arrayList.add(new companies_list("1", "ahmed mohamed", "25 reviews", 5));
        arrayList.add(new companies_list("1", "ahmed mohamed", "25 reviews", 5));
        arrayList.add(new companies_list("1", "ahmed mohamed", "25 reviews", 5));


        new utils_adapter().Adapter(companiesList, new compaines_adapter(this, arrayList), this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back)
        {
            finish();
        }
    }
}

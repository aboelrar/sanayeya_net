package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.controller;

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
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.model.industerial_model_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.pattern.industerial_adapter;

public class industerial_list extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.industerial_list)
    RecyclerView industerialList;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.industerial_list);
        ButterKnife.bind(this);

        //SET TITLE NAME
        title.setText(R.string.industerial_ser);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);

        //GET DATA
        getData();
    }

    //GET DATA
    void getData() {
        ArrayList<industerial_model_list> arrayList = new ArrayList<>();

        arrayList.add(new industerial_model_list("1", "ahmed mohamed", "25 reviews", 5));
        arrayList.add(new industerial_model_list("1", "ahmed mohamed", "25 reviews", 5));
        arrayList.add(new industerial_model_list("1", "ahmed mohamed", "25 reviews", 5));
        arrayList.add(new industerial_model_list("1", "ahmed mohamed", "25 reviews", 5));


        new utils_adapter().Adapter(industerialList, new industerial_adapter(this, arrayList), this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back)
        {
            finish();
        }
    }
}

package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.controller.industerial_services;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services.controller.home_services;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.controller.map_repair_location;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_location.controller.winch_location;

/**
 * A simple {@link Fragment} subclass.
 */
public class categories extends Fragment implements View.OnClickListener {

    View view;
    ImageView winch_img;
    ImageView twenty_four_img;
    ImageView home_service_img;
    ImageView industerial_service_img;


    public categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.categories, container, false);

        //SET WINCH IMAGE IN GLIDE
        winch_img = view.findViewById(R.id.winch_img);
        Glide.with(getContext()).load(R.drawable.winch).into(winch_img);

        //SET TWENTY FOUR IN GLIDE
        twenty_four_img = view.findViewById(R.id.twentyfour_image);
        Glide.with(getContext()).load(R.drawable.twentyfour).into(twenty_four_img);

        //SET HOME SERVICES FOUR IN GLIDE
        home_service_img = view.findViewById(R.id.home);
        Glide.with(getContext()).load(R.drawable.home_service).into(home_service_img);

        //SET HOME SERVICES FOUR IN GLIDE
        industerial_service_img = view.findViewById(R.id.industerial);
        Glide.with(getContext()).load(R.drawable.industerial_service).into(industerial_service_img);



        //GO TO WINCH PAGE
        RelativeLayout winch = view.findViewById(R.id.winch);
        winch.setOnClickListener(this);

        //GO TO TWENTY FOUR
        RelativeLayout twentyfour = view.findViewById(R.id.twentyfour);
        twentyfour.setOnClickListener(this);

        //GO TO HOME SERVICES
        RelativeLayout home_services = view.findViewById(R.id.home_services);
        home_services.setOnClickListener(this);

        //GO TO HOME SERVICES
        RelativeLayout industerial_services = view.findViewById(R.id.industerial_services);
        industerial_services.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.winch) {
            startActivity(new Intent(getContext(), winch_location.class));
        }
        else if(view.getId() == R.id.twentyfour)
        {
            startActivity(new Intent(getContext(), map_repair_location.class));
        }
        else if(view.getId() == R.id.home_services)
        {
            startActivity(new Intent(getContext(), home_services.class));
        }
        else if(view.getId() == R.id.industerial_services)
        {
            startActivity(new Intent(getContext(), industerial_services.class));
        }
    }
}

package www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.controller.map_repair_location;
import www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_location.controller.winch_location;

/**
 * A simple {@link Fragment} subclass.
 */
public class categories extends Fragment implements View.OnClickListener {

    View view;

    public categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.categories, container, false);

        //GO TO WINCH PAGE
        RelativeLayout winch = view.findViewById(R.id.winch);
        winch.setOnClickListener(this);

        //GO TO TWENTY FOUR
        RelativeLayout twentyfour = view.findViewById(R.id.twentyfour);
        twentyfour.setOnClickListener(this);

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
    }
}

package www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.dm.sanayeya.net.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class categories extends Fragment {

    public categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.categories, container, false);
    }
}

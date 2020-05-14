package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.model.client_request_list;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.pattern.client_request_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class clients_request extends Fragment {

    View view;
    RecyclerView requests_list;

    public clients_request() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.clients_request, container, false);

        //GET SERVER DATA
        get_data();

        return view;
    }

    void get_data()
    {
        ArrayList<client_request_list> arrayList = new ArrayList<>();

        arrayList.add(new client_request_list("1","Request id : 51887876","Road 45,30:00 Am","Road 45,30:00 Am"));
        arrayList.add(new client_request_list("1","Request id : 51887876","Road 45,30:00 Am","Road 45,30:00 Am"));

        requests_list = view.findViewById(R.id.requests_list);

        new utils_adapter().Adapter(requests_list,new client_request_adapter(getContext(),arrayList),getContext());

    }
}

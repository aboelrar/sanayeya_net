package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.model.client_request_list;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.model.my_ordersDatum;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.model.my_ordersRootClass;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.pattern.client_request_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class clients_request extends Fragment implements NetworkInterface {

    View view;
    RecyclerView requests_list;
    my_ordersRootClass my_ordersRootClass;
    my_ordersDatum[] my_ordersData;
    TextView requests;

    public clients_request() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.clients_request, container, false);

        //CALL API
        new Apicalls(getContext(), this).my_orders();

        //SWIPE REFRESH
        swipe_refresh();

        return view;
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        set_data(model.getJsonObject());
    }

    @Override
    public void OnError(VolleyError error) {

    }

    //GET RESPONSE DATA
    void set_data(JSONObject data) {

        ArrayList<client_request_list> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        my_ordersRootClass = gson.fromJson("" + data, my_ordersRootClass.class);
        my_ordersData = my_ordersRootClass.getData();

        for (int index = 0; index < my_ordersData.length; index++) {
            arrayList.add(new client_request_list("" + my_ordersData[index].getId(), "Request id : 51887876", my_ordersData[index].getLocationAddress(),
                    my_ordersData[index].getDestinationAddress(), my_ordersData[index].getLocationLat(), my_ordersData[index].getLocationLng(),
                    my_ordersData[index].getWinchLat(),my_ordersData[index].getWinchLng()));

        }

        requests_list = view.findViewById(R.id.requests_list);
        //SET DATA TO ADAPTER
        new utils_adapter().Adapter(requests_list, new client_request_adapter(getContext(), arrayList), getContext());

        requests = view.findViewById(R.id.requests);
        if (arrayList.size() != 0) {
            requests.setVisibility(View.GONE);
        } else {
            requests.setVisibility(View.VISIBLE);
        }
    }

    //SWIPE REFRERSH
    void swipe_refresh() {
        /// swipe Refresh
        final SwipeRefreshLayout swipNotif = view.findViewById(R.id.swipe);
        swipNotif.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //CALL API
                new Apicalls(getContext(), clients_request.this).my_orders();
                swipNotif.setRefreshing(false);
            }
        });
    }
}

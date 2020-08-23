package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.pattern;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import org.json.JSONException;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.send_data;
import www.dm.sanayeya.net.test_navigation;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_track_user_location.cotroller.track_user_location;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.controller.clients_request;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.model.client_request_list;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_welcome_screen.Scenario_winch_signup.controller.winch_signup;

import static www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.winch_main_screen.check_winch_location;

public class client_request_adapter extends RecyclerView.Adapter<client_request_adapter.view_holder> implements NetworkInterface {

    Context context;
    ArrayList<client_request_list> mylist;

    public client_request_adapter(Context context, ArrayList<client_request_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.client_request_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.from.setText(mylist.get(position).getFrom());
        holder.to.setText(mylist.get(position).getTo());

        //CALL ACCEPT AND REJECT API
        on_click(holder.apply, holder.reject, holder.go_map, mylist.get(position).getId(), position);
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        try {
            Toasty.success(context, "" + model.getJsonObject().getString("message"), Toasty.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnError(VolleyError error) {

    }

    //SET ON CLICK LISTNERS APPLY & REJECT
    void on_click(Button Accept, Button Reject, ImageView go_map, String id, int position) {


        //ACCEPT CLICK
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check_winch_location != 1) {

                    new Apicalls(context, client_request_adapter.this).accept_reject_request(id, "2");

                    //ADD WINCH OWNER DATA
                    send_data.winch_owner_lat(context, mylist.get(position).getAddress_lat()); //ADD WINCH LAT
                    send_data.winch_owner_lng(context, mylist.get(position).getAddress_lng()); //ADD WINCH LNG
                    send_data.request_id(context, mylist.get(position).getId()); //ADD WINCH ID
                    send_data.set_user_phone(context, mylist.get(position).getPhone()); //ADD WINCH PHONE

                    //CHECK IF LIST EQUAL ZERO
                    Intent intent = new Intent(context, test_navigation.class);
                    context.startActivity(intent);

                } else {
                    Toasty.error(context, context.getString(R.string.cant_detect)).show();
                }


            }
        });

        //REJECT CLICK
        Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylist.remove(position);
                notifyDataSetChanged();
                new Apicalls(context, client_request_adapter.this).accept_reject_request(id, "3");
                //CHECK IF LIST EQUAL ZERO
                list_zero();
            }
        });

        //GO TO IMAGE
        go_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, test_navigation.class);
                intent.putExtra("address_location_lat", mylist.get(position).getAddress_lat());
                intent.putExtra("address_location_lng", mylist.get(position).getAddress_lng());
                intent.putExtra("winch_location_lat", mylist.get(position).getWinch_lat());
                intent.putExtra("winch_location_lng", mylist.get(position).getWinch_lng());
                intent.putExtra("request_id", mylist.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    //REFRESH ACTIVITY IF LIST EQUAL ZETO
    void list_zero() {
        if (mylist.size() == 0) {
            //ADD REQUEST FRAGMENT
            new utils().Replace_Fragment(new clients_request(), R.id.frag, context);
        }
    }

    class view_holder extends RecyclerView.ViewHolder {
        TextView request_id, from, to;
        Button apply, reject;
        ImageView go_map;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            apply = itemView.findViewById(R.id.apply);
            reject = itemView.findViewById(R.id.reject);
            go_map = itemView.findViewById(R.id.go_map);
        }
    }
}

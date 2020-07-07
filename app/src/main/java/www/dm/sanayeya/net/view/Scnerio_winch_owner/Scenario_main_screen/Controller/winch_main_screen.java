package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.VolleyError;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.test_navigation;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_chnage_password.controller.change_pass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_help.controller.help;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_terms.terms;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.controller.clients_request;

public class winch_main_screen extends AppCompatActivity implements NavigationDrawerCallbacks, View.OnClickListener, NetworkInterface {

    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winch_main_screen);
        ButterKnife.bind(this);


        //SET UP DRAWER
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, drawer);

        //SET ON CLICK LISTNER
        menu.setOnClickListener(this);

        //CHECK IF REQUEST ID ZERO OR ONE
        if (new saved_data().get_winch_request_id(winch_main_screen.this).equals("0")) {
            //ADD REQUEST FRAGMENT
            new utils().Replace_Fragment(new clients_request(), R.id.frag, this);
        } else {
           //GO TO MAP
             startActivity(new Intent(this, test_navigation.class));
        }

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(winch_main_screen.this);

        new Apicalls(winch_main_screen.this, winch_main_screen.this)
                .change_language(new saved_data().get_lan(winch_main_screen.this));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 0) {
            new utils().Replace_Fragment(new clients_request(), R.id.frag, this); //REQUEST FRAGMENT
        } else if (position == 1) {
            new utils().Replace_Fragment(new change_pass(), R.id.frag, this); //CHANGE PASSWORD
        } else if (position == 2) {
            new utils().Replace_Fragment(new help(), R.id.frag, this); //HELP
        } else if (position == 3) {
            new utils().Replace_Fragment(new terms(), R.id.frag, this); //HELP
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.menu) {
            mNavigationDrawerFragment.openDrawer(); //MENU OPEN DRAWER
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigationDrawerFragment.closeDrawer();
    }

    @Override
    public void onBackPressed() {

        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {

            moveTaskToBack(true);
        }

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

    }

    @Override
    public void OnError(VolleyError error) {

    }
}

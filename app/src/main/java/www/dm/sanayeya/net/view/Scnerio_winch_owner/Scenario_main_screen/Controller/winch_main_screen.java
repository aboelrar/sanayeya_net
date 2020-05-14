package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_chnage_password.controller.change_pass;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_help.controller.help;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_terms.terms;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.controller.clients_request;

public class winch_main_screen extends AppCompatActivity implements NavigationDrawerCallbacks, View.OnClickListener {

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

        //ADD REQUEST FRAGMENT
        new utils().Replace_Fragment(new clients_request(), R.id.frag, this);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(position == 0)
        {
            new utils().Replace_Fragment(new clients_request(), R.id.frag, this); //REQUEST FRAGMENT
        }
        else if(position == 1)
        {
            new utils().Replace_Fragment(new change_pass(), R.id.frag, this); //CHANGE PASSWORD
        }
        else if(position == 2)
        {
            new utils().Replace_Fragment(new help(), R.id.frag, this); //HELP
        }
        else if(position == 3)
        {
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
}

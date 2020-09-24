package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_choose_login.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_welcome_screen.Scenario_winch_signup.controller.winch_signup;

public class choose_login extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.user)
    Button user;
    @BindView(R.id.winch_owner)
    Button winchOwner;
    @BindView(R.id.warsha)
    Button warsha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_login);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNER
        back.setOnClickListener(this);
        user.setOnClickListener(this);
        winchOwner.setOnClickListener(this);
        warsha.setOnClickListener(this);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(choose_login.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        } else if (view.getId() == R.id.user) {
            startActivity(new Intent(choose_login.this, signup.class));
        } else if (view.getId() == R.id.winch_owner) {
            startActivity(new Intent(choose_login.this, winch_signup.class));
        } else if (view.getId() == R.id.warsha)
        {
            warsha_popup warsha_popup = new warsha_popup();
            warsha_popup.dialog(choose_login.this,R.layout.warsha_popup,.80,"1");
        }
    }
}

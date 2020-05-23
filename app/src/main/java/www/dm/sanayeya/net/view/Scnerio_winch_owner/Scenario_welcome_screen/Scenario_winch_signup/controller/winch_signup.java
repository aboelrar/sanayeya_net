package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_welcome_screen.Scenario_winch_signup.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.winch_main_screen;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_welcome_screen.Scenario_winch_login.controller.winch_login;

public class winch_signup extends AppCompatActivity  implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.signup)
    Button signup;
    @BindView(R.id.login)
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winch_signup);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNER
        back.setOnClickListener(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back)
        {
            finish();
        }
        else if(view.getId() == R.id.login)
        {
            startActivity(new Intent(winch_signup.this, winch_login.class));
        }
        else if(view.getId() == R.id.signup)
        {
            startActivity(new Intent(winch_signup.this, winch_main_screen.class));
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, winch_login.class));
    }
}

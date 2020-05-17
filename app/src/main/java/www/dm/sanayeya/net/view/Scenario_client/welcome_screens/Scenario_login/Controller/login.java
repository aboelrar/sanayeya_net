package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_forget_password.controller.forget_password;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup;

public class login extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.signup)
    TextView signup;
    @BindView(R.id.forget_pass)
    TextView forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNER
        back.setOnClickListener(this);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        forgetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        } else if (view.getId() == R.id.signup) {
            startActivity(new Intent(login.this, www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup.class));
        } else if (view.getId() == R.id.login) {
            startActivity(new Intent(login.this, MainActivity.class));
        }else if(view.getId() == R.id.forget_pass) {
            startActivity(new Intent(login.this, forget_password.class));
        }
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}

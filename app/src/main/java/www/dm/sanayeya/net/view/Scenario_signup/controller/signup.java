package www.dm.sanayeya.net.view.Scenario_signup.controller;

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
import www.dm.sanayeya.net.view.Scenario_login.Controller.login;
import www.dm.sanayeya.net.view.Scenario_main_screen.Controller.MainActivity;

public class signup extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.signup)
    Button signup;
    @BindView(R.id.login)
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
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
            startActivity(new Intent(signup.this, www.dm.sanayeya.net.view.Scenario_login.Controller.login.class));
        }
        else if(view.getId() == R.id.signup)
        {
            startActivity(new Intent(signup.this, MainActivity.class));
        }
    }
}

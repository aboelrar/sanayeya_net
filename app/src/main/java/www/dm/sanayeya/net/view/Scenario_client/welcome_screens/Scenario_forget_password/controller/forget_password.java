package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_forget_password.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_verifcation_code.controller.verifcation_code;

public class forget_password extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.apply)
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);
        apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
     if(view.getId() == R.id.back)
     {
         finish();
     }
     else if(view.getId() == R.id.apply)
     {
        startActivity(new Intent(this, verifcation_code.class));
     }

    }
}

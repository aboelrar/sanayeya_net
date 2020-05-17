package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_verifcation_code.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;

public class verifcation_code extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.verifcation_code)
    EditText verifcationCode;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.apply)
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifcation_code);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back)
        {
            finish();
        }
    }
}

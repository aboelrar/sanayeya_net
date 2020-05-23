package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_payment.controller;

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
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_track_location.cotroller.track_winch_location;

public class payment_method extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.confirm)
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_method);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        confirm.setOnClickListener(this);
        back.setOnClickListener(this);

        //SET TITLE TEXT
        title.setText(getResources().getString(R.string.payment));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.confirm)
        {
           startActivity(new Intent(this, track_winch_location.class));
        }
        else if(view.getId() == R.id.back)
        {
           finish();
        }
    }
}

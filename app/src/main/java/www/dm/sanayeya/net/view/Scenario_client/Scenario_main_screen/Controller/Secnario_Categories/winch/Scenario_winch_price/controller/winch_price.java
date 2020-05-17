package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_payment.controller.payment_method;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_rating.controller.rating;

public class winch_price extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.confirm)
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winch_price);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       if(view.getId() == R.id.confirm)
       {
           startActivity(new Intent(winch_price.this, payment_method.class));
       }
    }
}

package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_rating.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;

public class rating extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rating)
    RatingBar rating;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        submit.setOnClickListener(this);
        back.setOnClickListener(this);

        //SET TITLE TEXT
        title.setText(getResources().getString(R.string.rate));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit) {
            startActivity(new Intent(rating.this, MainActivity.class));
        }
        else if(view.getId() ==R.id.back)
        {
            finish();
        }
    }
}

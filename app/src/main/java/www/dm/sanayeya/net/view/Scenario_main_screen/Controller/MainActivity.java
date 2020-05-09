package www.dm.sanayeya.net.view.Scenario_main_screen.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.categories;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ADD FRAGMENT
        new utils().Replace_Fragment(new categories(),R.id.frag,this);
    }
}

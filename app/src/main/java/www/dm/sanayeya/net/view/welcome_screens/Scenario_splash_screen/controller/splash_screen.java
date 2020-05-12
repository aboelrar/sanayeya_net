package www.dm.sanayeya.net.view.welcome_screens.Scenario_splash_screen.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.welcome_screens.Scenario_choose_language.controller.choose_language;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

       new utils().splash_screen(this, choose_language.class); //GO TO CHOOSE LANGUAGE
    }
}

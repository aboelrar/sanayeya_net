package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_splash_screen.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_choose_language.controller.choose_language;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.winch_main_screen;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (new saved_data().get_login_status(splash_screen.this) == true) {
            if (new saved_data().get_user_type(this).equals("user")) {
                startActivity(new Intent(this, MainActivity.class)); // GO TO USER
            } else {
                startActivity(new Intent(this, winch_main_screen.class)); // GO TO WINCH
            }
        } else {
            new utils().splash_screen(this, choose_language.class); //GO TO CHOOSE LANGUAGE
        }
    }
}

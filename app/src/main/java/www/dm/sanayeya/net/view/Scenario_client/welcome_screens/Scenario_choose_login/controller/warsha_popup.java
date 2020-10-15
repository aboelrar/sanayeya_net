package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_choose_login.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Objects;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.add_warsha.controller.add_warsha;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.winch_main_screen;


public class warsha_popup implements View.OnClickListener {
    Context context;

    public void dialog(final Context context, int resource, double widthh, String type) {
        this.context = context;
        final Dialog dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        //Industrial service
        Button industrialSer = dialog.findViewById(R.id.industerial_ser);
        industrialSer.setOnClickListener(this);

        //twenty four seven
        Button twentyFourSeven = dialog.findViewById(R.id.twentyfour_seven);
        twentyFourSeven.setOnClickListener(this);

        //home services
        Button homeServices = dialog.findViewById(R.id.home_services);
        homeServices.setOnClickListener(this);

        dialog.show();

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.industerial_ser) {
            context.startActivity(new Intent(context, add_warsha.class));
        } else if (v.getId() == R.id.twentyfour_seven) {
            context.startActivity(new Intent(context, add_warsha.class));
        } else if(v.getId() == R.id.home_services){
            context.startActivity(new Intent(context, add_warsha.class));
        }
    }
}


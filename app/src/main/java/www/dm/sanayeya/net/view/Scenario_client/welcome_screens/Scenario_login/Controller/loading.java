package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;

import java.util.Objects;

import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.winch_main_screen;


public class loading {
    public void dialog(final Context context, int resource, double widthh, String type) {
        final Dialog dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        Activity activity = (Activity) context;
        if (!activity.isFinishing()) {
            dialog.show();
        }


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                if (type.equals("user")) {
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((Activity) context).finish();
                    dialog.dismiss();
                } else {
                    context.startActivity(new Intent(context, winch_main_screen.class));
                    ((Activity) context).finish();
                    dialog.dismiss();
                }

            }
        }, 2000);
    }


}


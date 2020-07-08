package www.dm.sanayeya.net.notifcations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import www.dm.sanayeya.net.local_data.send_data;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.track_winch_location.controller.track_winch_location;


public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        send_data.set_notifcation_status(context,"1");
        Log.e("xxxxx","sxsa");

        intent = new Intent(context, track_winch_location.class);
         intent.putExtra("value",1000);
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.sendBroadcast(intent);
    }

}
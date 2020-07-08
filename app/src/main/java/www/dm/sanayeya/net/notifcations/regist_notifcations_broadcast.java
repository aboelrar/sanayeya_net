package www.dm.sanayeya.net.notifcations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import www.dm.sanayeya.net.network_check_status.NetworkChangeReceiver;


public class regist_notifcations_broadcast {
    NetworkChangeReceiver mNetworkReceiver = new NetworkChangeReceiver(); //DEFINE NETWORK RECIEVER

    public void registerNetworkBroadcastForNougat(Context context) {


        NotificationReceiver mNetworkReceiver = new NotificationReceiver(); //DEFINE NETWORK RECIEVER

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }



}

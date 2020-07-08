package www.dm.sanayeya.net.notifcations;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import www.dm.sanayeya.net.local_data.send_data;

public class notifcation_sevice extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        send_data.set_notifcation_status(getBaseContext(),"1");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package www.dm.sanayeya.net.notifcations;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.android.volley.VolleyError;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.track_winch_location.controller.track_winch_location;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.winch_main_screen;

public class Custom_Fcmlistner extends FirebaseMessagingService {

    private final String ADMIN_CHANNEL_ID = "admin_channel";
    private NotificationUtils notificationUtils;



    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        if (saved_data.get_switch_checked(this) == true) {
//
//
//            Log.e("", "notificationStoped");
//
//        }
//        else {
            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                Log.e("Notification Body: ", remoteMessage.getNotification().getBody());
                sendNotification(Objects.requireNonNull(remoteMessage.getNotification()).getTitle(), remoteMessage.getNotification().getBody());

            }

            // Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0) {
                Log.e("Data Payload: ", remoteMessage.getData().toString());

                try {
                    handleDataMessage(remoteMessage);
                } catch (Exception e) {
                    Log.e("Exception: ", e.getMessage());
                }
            }

        }
//    }


    private void sendNotification(String messageTitle, String messageBody) {

        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            RemoteViews collapseView = new RemoteViews(getPackageName(), R.layout.notification_collapsed);
            RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification_expended);

            expandedView.setTextViewText(R.id.content_title, messageTitle);
            expandedView.setTextViewText(R.id.content_text, messageBody);
            //            expandedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));


            Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            collapseView.setTextViewText(R.id.content_title, messageTitle);
            collapseView.setTextViewText(R.id.content_text, messageBody);


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int notificationID = new Random().nextInt(3000);


            Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
            broadcastIntent.putExtra("toastMessage", "message");



            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


            Intent intent2 = null;

            if(new saved_data().get_user_type(getBaseContext()).equals("user"))
            {
                intent2 = new Intent(this, track_winch_location.class);
            }
            else {
                intent2 = new Intent(this, winch_main_screen.class);
            }


            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);










      /*
        Apps targeting SDK 26 or above (Android O) must implement notification channels and add its notifications
        to at least one of them. Therefore, confirm if version is Oreo or higher, then setup notification channel
      */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setupChannels(notificationManager);
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                    R.drawable.app_logo);


            Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                    .setSmallIcon(R.drawable.app_logo)
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(collapseView)
                    .setCustomBigContentView(expandedView)
                    .setContentTitle(messageTitle)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(notificationSoundUri)
                    .setContentIntent(contentIntent)
                    .setSound(defaultSound)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setColor(Color.parseColor("#F8971C"));


            //Set notification color to match your app color template
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
            }

//            if (saved_data.get_switch_checked(this) == true) {
//
//                //  final Intent intent = new Intent(this, offers.class);
//
//                Log.e("", "notificationStoped");
//
//            } else {
                notificationManager.notify(notificationID, notificationBuilder.build());
//           }

        } else {


            RemoteViews collapseView = new RemoteViews(getPackageName(), R.layout.notification_collapsed);
            RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification_expended);


            expandedView.setTextViewText(R.id.content_title, messageTitle);
            expandedView.setTextViewText(R.id.content_text, messageBody);

            Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            collapseView.setTextViewText(R.id.content_title, messageTitle);
            collapseView.setTextViewText(R.id.content_text, messageBody);


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int notificationID = new Random().nextInt(3000);


            Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
            broadcastIntent.putExtra("toastMessage", "message");



            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


            Intent intent2 = null;

            if(new saved_data().get_user_type(getBaseContext()).equals("user"))
            {
                intent2 = new Intent(this, track_winch_location.class);
            }
            else {
                intent2 = new Intent(this, winch_main_screen.class);
            }


            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);










      /*
        Apps targeting SDK 26 or above (Android O) must implement notification channels and add its notifications
        to at least one of them. Therefore, confirm if version is Oreo or higher, then setup notification channel
      */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setupChannels(notificationManager);
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_launcher);


            Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(largeIcon)
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(remoteMessage.getNotification().getBody())
//                        .setBigContentTitle(remoteMessage.getNotification().getTitle()))
//                    .setStyle(new androidx.media.app.NotificationCompat.DecoratedMediaCustomViewStyle())
//                    .setDefaults(Notification.DEFAULT_ALL)
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(collapseView)
                    .setCustomBigContentView(expandedView)
                    .setContentTitle(messageTitle)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(notificationSoundUri)
                    .setContentIntent(contentIntent)
                    .setSound(defaultSound)
                    .setPriority(Notification.PRIORITY_MAX)
//                    .setWhen(0)
                    .setColor(Color.parseColor("#F8971C"));


            //Set notification color to match your app color template
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
            }
//            if (saved_data.get_switch_checked(this) == true) {
//
//                //  final Intent intent = new Intent(this, offers.class);
//
//                notificationManager.notify();
//            } else {
                notificationManager.notify(notificationID, notificationBuilder.build());
            }
        }
//    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(NotificationManager notificationManager) {
//        CharSequence adminChannelName = "New notification";
        CharSequence adminChannelName = "default_notification_channel_id";
        String adminChannelDescription = "default_notification_channel_id";

        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);

        if (notificationManager != null) {

            notificationManager.createNotificationChannel(adminChannel);

        }
    }


    private void handleDataMessage(final RemoteMessage remoteMessage) {
        Log.e("push json: ", remoteMessage.toString());


        Map<String, String> data = remoteMessage.getData();
        Config.title = data.get("title");
        Config.content = data.get("content");
        Config.imageUrl = data.get("imageUrl");
        Config.gameUrl = data.get("gameUrl");

        Log.e("myid_is",""+data.get("id"));

//        Intent resultIntent = new Intent(getApplicationContext(), Notification.class);
//        resultIntent.putExtra("title", Config.title);
//        resultIntent.putExtra("message", Config.content);


        // check for image attachment
        if (TextUtils.isEmpty(Config.imageUrl) || Config.imageUrl == null) {
            sendNotification(Config.title, Config.content);
        } else {
            // image is present, show notification with image
            sendNotification(Config.title, Config.content);
        }
//            }
//        } catch (JSONException e) {
//            Log.e("Json Exception: " , e.getMessage());
//        } catch (Exception e) {
//            Log.e("Exception: " , e.getMessage());
//        }
    }

    @Override
    public void onNewToken(String token) {
        Log.e("Refreshed token:", token);



    }

}

package www.dm.sanayeya.net.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import www.dm.sanayeya.net.R;


public class utils {

   static ProgressDialog  pd;


    /**
     * SPLASH SCREEN
     */
      public void splash_screen(final Context context, final Class second_class)
       {
           new Thread(new Runnable() {
               public void run() {
                   try {
                       // sleep during 800ms
                       Thread.sleep(3000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   // start HomeActivity
                   Intent intent=new Intent(context, second_class);
                   ((AppCompatActivity)context).overridePendingTransition(0, 0);
                   context.startActivity(intent);
                   ((AppCompatActivity)context).overridePendingTransition(0, 0);
                   ((AppCompatActivity)context).finish();
               }
           }).start();
       }

    /**
     * Upload Image
     */
    public void upload_image(Context context,int requestCode)
    {
        Intent i = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((AppCompatActivity)context).startActivityForResult(Intent.createChooser(i, "Select Your Photo"),requestCode);
    }

    /**
     * Upload Files
     */
    public void upload_files(Context context,int requestCode)
    {
        Intent i = new Intent( Intent.ACTION_OPEN_DOCUMENT);
        i.setType("application/pdf");
        i.addCategory(Intent.CATEGORY_OPENABLE);
        ((AppCompatActivity)context).startActivityForResult(i,requestCode);

    }

    /**
     * REPLACE FRAGMENT
     */
    public void Replace_Fragment(Fragment fragment, int id, Context context)
    {
        //ADD FRAGMENT TO ACTIVITY
        Fragment home=fragment;
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction()
          .replace(id, home ).commit();
    }

    /**
     * convert to byte array
     */
    public Bitmap convertToBitmap(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * SET LANGUAGE
     */
   public static void set_language(String Lan, Context context)
    {
        Resources resources=context.getResources();
        DisplayMetrics displayMetrics=resources.getDisplayMetrics();
        Configuration configuration=resources.getConfiguration();
        configuration.setLocale(new Locale(Lan.toLowerCase()));
        resources.updateConfiguration(configuration,displayMetrics);

    }

    /**
     * WAVE MARKER LOCATION
     */

    public void marker_animation(double lat, double lng, View view, GoogleMap mGoogleMap)
    {

        GroundOverlay groundOverlay = mGoogleMap.addGroundOverlay(new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.auth_logo))
                .position(new LatLng(lat, lng), 1000.0f,1000.0f));
        RadiusAnimation groundAnimation = new RadiusAnimation(groundOverlay);
        groundAnimation.setRepeatCount(Animation.INFINITE);
        groundAnimation.setRepeatMode(Animation.RESTART);
        groundAnimation.setDuration(2000);
        view.startAnimation(groundAnimation); // MapView where i show my map
    }

    /**
     * SET PROGRESS DIALOG
     */

    public void set_dialog(Context context)
    {
        String loading =  context.getResources().getString(R.string.loading_c);

        pd = new ProgressDialog(context);
        pd.setMessage(loading);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    /**
     * SET PROGRESS DIALOG DISMISS
     */

    public void dismiss_dialog(Context context)
    {
        pd.dismiss();
    }


    /**
     * GET FIEL NAME
     */

    public String getFileName(Uri uri, Context context) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    /**
     * YOYO LIBRARY
     */
    public static void yoyo(int id, View v) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(1)
                .playOn(v.findViewById(id));
    }





}

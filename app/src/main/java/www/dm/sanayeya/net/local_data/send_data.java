package www.dm.sanayeya.net.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class send_data {

    public static void send_lan(Context context,String lan)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("language",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("lan", lan);
        editor.commit();
    }


    //SAVE PERSONAL_NAME
    public static void send_name(Context context,String name)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name", name);
        editor.commit();
    }

    //SAVE PERSONAL_NAME
    public static void send_email(Context context,String email)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email", email);
        editor.commit();
    }

    //SAVE PERSONAL_TOKEN
    public static void send_token(Context context,String token)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

    //SAVE PERSONAL_PHONE
    public static void send_phone(Context context,String phone)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("personal_info",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("phone", phone);
        editor.commit();
    }



    //SET LOGIN TRUE
    public static void login_status(Context context,boolean status)
    {
        //SAVE LANGUAGE STATUS
        SharedPreferences sharedPreferences=context.getSharedPreferences("login_bool",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("login_bool", status);
        editor.commit();
    }


}

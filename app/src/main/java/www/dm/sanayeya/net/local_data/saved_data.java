package www.dm.sanayeya.net.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class saved_data {

    public String get_lan(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        return sharedPreferences.getString("lan", "en");

    }


    //GET NAME
    public String get_name(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "0");
        return name;
    }

    //GET EMAIL
    public String get_email(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "0");
        return email;
    }

    //GET EMAIL
    public String get_phone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String email = sharedPreferences.getString("phone", "0");
        return email;
    }

    //GET PHONE
    public String get_token(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "0");
        return token;
    }


    //GET LOGIN STATUS
    public Boolean get_login_status(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login_bool", MODE_PRIVATE);
        Boolean status = sharedPreferences.getBoolean("login_bool", false);
        return status;
    }

}

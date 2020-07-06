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

    //SAVE USER TYPE
    public String get_user_type(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String type = sharedPreferences.getString("type", "user");
        return type;
    }

    //GET ORDER ID
    public String order_id(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String type = sharedPreferences.getString("order_id", "0");
        return type;
    }

    //GET NOTIFCATION STATUS
    public String notifcation_status(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String type = sharedPreferences.getString("status", "0");
        return type;
    }

    //GET ORDER LAT
    public Double order_lat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        long type = sharedPreferences.getLong("order_lat", Double.doubleToLongBits(0.0));
        return Double.longBitsToDouble(type);
    }

    //GET ORDER LNG
    public Double order_lng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        long type = sharedPreferences.getLong("order_lng", Double.doubleToLongBits(0.0));
        return Double.longBitsToDouble(type);
    }

    //GET WINCH LAT
    public Double get_winch_owner_lat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        long type = sharedPreferences.getLong("winch_lat", Double.doubleToLongBits(0.0));
        return Double.longBitsToDouble(type);
    }

    //GET WINCH LAT
    public Double get_winch_owner_lng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        long type = sharedPreferences.getLong("winch_lng", Double.doubleToLongBits(0.0));
        return Double.longBitsToDouble(type);
    }

    //GET WINCH LAT
    public String get_winch_request_id(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        String id = sharedPreferences.getString("request_id","0");
        return id;
    }

}

package www.dm.sanayeya.net.NetworkLayer;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Apiclient {

    /**
     * @API
     *
     * ---> 1) URL OF API METHOD
     *
     * ---> 2) ARRAY OF PARAMETERS KEYS
     *
     */

    LOGIN_USER("Auth_general/login", Arrays.asList("emailOrPhone", "password","firebase_token")),
    INSERT_USER("Auth_general/register", Arrays.asList("username","type","phone","email","lat","lng","password","firebase_token","whatsapp")),
    FORGET_PASS("Auth_general/forget_password", Arrays.asList("email")),
    CHANGE_PASS("Auth_private/change_password", Arrays.asList("oldPassword","newPassword")),
    RESET_PASS("Auth_general/reset_password",Arrays.asList("password","code")),
    ACTIVE_ACCOUNT("Auth_private/check_code", Arrays.asList("code")),
    RESEND_CODE("Auth_private/resend_code", null),
    EDIT_PROFILE("Auth_private/edit_profile", Arrays.asList("username","phone","email","lat","lng")),
    LOGOUT("Auth_private/logout", null),
    CHOOSE_HOME_SERVICES("home_service/car_electrician", null),
    HOME_SERVICES_DATA("home_service/home_services/", null),
    HOME_SERVICE_DETAILS("home_service/single_home_services/", null),
    PROVINCE("industrial/Province", null),
    CAR_MODELS("industrial/car_models", null),
    WORK_SHOPS("industrial/work_shops", null),
    INDUSTERIAL_SERVICES("industrial/industrial_services", Arrays.asList("car_model_id","workShop_id","province_id")),
    INDUSTERIAL_SERVICES_DETAILS("industrial/single_industrial_services", null),
    TWENTY_FOUR("live_services/live_services", null),
    TWENTY_FOUR_DETAILS("live_services/single_live_services", null),
    WORKSHOP_RATE("live_services/rate", Arrays.asList("rate","comment")),
    INDUSTERIAL_RATE("industrial/rate", Arrays.asList("rate","comment")),
    HOMESERVICES_RATE("home_service/rate", Arrays.asList("rate","comment")),
    REQUEST_WENCH("wench/looking_for_wench", Arrays.asList("location_lat","location_lng","location_address","destination_lat","destination_lng","destination_address")),
    MY_ORDERS("wench/my_orders", null),
    CONFIRM_ORDER("wench/confirm_order", null),
    ACCEPT_REJECT_REQUEST("wench/change_status", Arrays.asList("status")),
    LANGUAGE("Auth_private/change_lang", Arrays.asList("lang")),
    UPDATE_INVESTOR("update_investor?", Arrays.asList("id","Name","Email","Password","Age","Gender","Work","Mobile","Images")),
    UPLOAD_CONSULTATION_FILES("upload-consultation-files", Arrays.asList("consultation_id","images","files"));



    //--------------------------------------

    /**
     * @BASE_URL
     */

    String BASE_URL = "http://snayia.trumpetagency.com/api/";

    private final String URL;
    private final List<String> params;


    Apiclient(String URL, List<String> params) {

        this.URL = URL;
        this.params = params;
    }

    public String getURL() {
        return BASE_URL + URL;
    }

    public List<String> getParams() {
        return params;
    }
}

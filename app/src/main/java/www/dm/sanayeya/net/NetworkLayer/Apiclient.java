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

    LOGIN_USER("Auth_general/login", Arrays.asList("emailOrPhone", "password")),
    INSERT_USER("Auth_general/register", Arrays.asList("username","type","phone","email","lat","lng","password")),
    FORGET_PASS("Auth_general/forget_password", Arrays.asList("email")),
    CHANGE_PASS("Auth_private/change_password", Arrays.asList("oldPassword","newPassword")),
    RESET_PASS("Auth_general/reset_password",Arrays.asList("password","code")),
    ACTIVE_ACCOUNT("Auth_private/check_code", Arrays.asList("code")),
    RESEND_CODE("Auth_private/resend_code", null),
    ADD_CONSULTATION("add-consultation", Arrays.asList("title","details","doctor_id","images","files")),
    SEND_MSG("send-message", Arrays.asList("consultation_id","message")),
    APPOIENMENTS("get-date", Arrays.asList("doctor_id","date")),
    UPDATE_USER_INFO("update-user-info", Arrays.asList("name","phone","email","password","image_url","medical_history")),
    GET_COMPLAINT_TYPE("get-complaint-type", null),
    MAKE_COMPLAINT("make-complaint", Arrays.asList("type_id","complaint","consultation_id")),
    MY_CONSULTATIONS("my-consultations", null),
    CONFIRM_CONSULTATION("confirm-consultation", Arrays.asList("consultation_id","payment_status")),
    RESERVE_CONVERSATION("reserve-conversation", Arrays.asList("doctor_id","appointment_id")),
    CONFIRM_CONVERSATION("confirm-reservation", Arrays.asList("consultation_id","payment_status")),
    FAQ("faq", null),
    GET_ALL_MSG("chat-messages", Arrays.asList("consultation_id")),
    DOWNLOAD_REPORT("download-report", Arrays.asList("consultation_id")),
    CONSULTATION_FILES("consultation-files", Arrays.asList("consultation_id")),
    CHANGE_LANG("change-lang", Arrays.asList("lang")),
    ADD_PHONE("add-phone", Collections.singletonList("phone")),
    CHANGE_PHOTO("update-personal-image", Collections.singletonList("image_url")),
    ADD_PROMO_CODE("add-promocode", Collections.singletonList("promo_code")),
    BILL_AMOUNT("finish-order", Arrays.asList("order_id","bill_amount")),
    CHECK_OUT("stripe-token", Arrays.asList("amount")),
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

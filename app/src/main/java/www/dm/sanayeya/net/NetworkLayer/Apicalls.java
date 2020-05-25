package www.dm.sanayeya.net.NetworkLayer;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONException;

import java.util.Arrays;
import java.util.Collections;

/**
 * @desc Java Api Calls Contains all the Project Calls
 */

public class Apicalls {

    private APIRouter apiRouter;

    public Apicalls(Context context, NetworkInterface networkInterface) {

        apiRouter = new APIRouter(context, networkInterface);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func User Login
     */

    public void loginUser(final String email, final String pass) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL(), Apiclient.LOGIN_USER.getParams(), Arrays.asList(email, pass), Request.Method.POST, 0);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func FORGET PASSWORD
     */

    public void FORGET_PASS(String email) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.FORGET_PASS.getURL(), Request.Method.POST, Apiclient.FORGET_PASS.getParams(), Arrays.asList(email), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func User Registration
     */

    public void insertUser(final String name, final String type, final String phone, final String email, final String lat, final String lng, final String password) {

        apiRouter.performRequest(Apiclient.INSERT_USER.getURL(), Apiclient.INSERT_USER.getParams(), Arrays.asList(name, type, phone, email, lat, lng, password), Request.Method.POST, 2);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func CHANGE PASSWORD
     */

    public void change_pass(final String oldpass, final String new_pass) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_PASS.getURL(), Request.Method.POST, Apiclient.CHANGE_PASS.getParams(), Arrays.asList(oldpass, new_pass), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func RESET PASSWORD
     */

    public void reset_pass(final String password, final String code) {

        apiRouter.performRequest(Apiclient.RESET_PASS.getURL(), Apiclient.RESET_PASS.getParams(), Arrays.asList(password, code), Request.Method.POST, 2);
    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func ACTIVE ACCOUNT
     */

    public void active_account(String code) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.ACTIVE_ACCOUNT.getURL(), Request.Method.POST, Apiclient.ACTIVE_ACCOUNT.getParams(), Arrays.asList(code), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func RESEND CODE
     */

    public void resend_code() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.RESEND_CODE.getURL(), Request.Method.POST, Apiclient.RESEND_CODE.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func EDIT PROFILE
     */

    public void edit_profile(String username, String phone, String email, String lat, String lng) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.EDIT_PROFILE.getURL(), Request.Method.POST, Apiclient.EDIT_PROFILE.getParams(), Arrays.asList(username, phone, email, lat, lng), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func MY_CONSULTATIONS
     */

    public void my_consultations() throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.MY_CONSULTATIONS.getURL(), Request.Method.GET, Apiclient.MY_CONSULTATIONS.getParams(), null, null);

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func CONFIRM_CONSULTATION
     */

    public void confirm_consultation(String consultation_id, String payment_status) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.CONFIRM_CONSULTATION.getURL(), Request.Method.POST, Apiclient.CONFIRM_CONSULTATION.getParams(), Arrays.asList(consultation_id, payment_status), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func RESERVE_CONVERSATION
     */

    public void reserve_conversation(String doctor_id, String appointment_id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.RESERVE_CONVERSATION.getURL(), Request.Method.POST, Apiclient.RESERVE_CONVERSATION.getParams(), Arrays.asList(doctor_id, appointment_id), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Confirm Conversation
     */

    public void confirm_conversation(final String consultation_id, final String payment_status) throws JSONException {
        apiRouter.makeAdvancedRequest(Apiclient.CONFIRM_CONVERSATION.getURL(), Request.Method.POST, Apiclient.CONFIRM_CONVERSATION.getParams(), Arrays.asList(consultation_id, payment_status), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func FAQ
     */

    public void faq() throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.FAQ.getURL(), Request.Method.GET, Apiclient.FAQ.getParams(), null, null);
    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func GET MESSAGES
     */

    public void get_all_msg(final String consultation_id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.GET_ALL_MSG.getURL(), Request.Method.POST, Apiclient.GET_ALL_MSG.getParams(), Arrays.asList(consultation_id), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Download Files
     */

    public void download_file(final String consultation_id) throws JSONException {
        apiRouter.makeAdvancedRequest(Apiclient.DOWNLOAD_REPORT.getURL(), Request.Method.POST, Apiclient.DOWNLOAD_REPORT.getParams(), Arrays.asList(consultation_id), null);
    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func CONSULTATION_FILES
     */

    public void consultation_files(final String consultation_id) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.CONSULTATION_FILES.getURL(), Request.Method.POST, Apiclient.CONSULTATION_FILES.getParams(), Arrays.asList(consultation_id), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func CHECK OUT
     */

    public void check_out(String amount) throws JSONException {

        apiRouter.makeAdvancedRequest(Apiclient.CHECK_OUT.getURL(), Request.Method.POST, Apiclient.CHECK_OUT.getParams(), Arrays.asList(amount), null);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Get Orders Data
     */

    public void Get_order_data() {


    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Get Offer Data
     */

    public void Get_myOrder() {


    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Set Complaint
     */

    public void set_complaint(String order_id, String complaint_type_id, String complaint) {

//        try {
//            apiRouter.makeAdvancedRequest(Apiclient.SET_COMPLAINT.getURL(), Request.Method.POST,Apiclient.SET_COMPLAINT.getParams(),Arrays.asList(order_id,complaint_type_id,complaint),null);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Cancel Order
     */

    public void cancel_order(String order_id) {


    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Get notifcation Data
     */

    public void Get_notifcation_data() {

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Reject Offer
     */

    public void change_password(final String password, final String password_confirmation) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_PASS.getURL(), Request.Method.PATCH, Apiclient.CHANGE_PASS.getParams(), Arrays.asList(password, password_confirmation), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Reject Offer
     */

    public void add_phone(final String phone) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.ADD_PHONE.getURL(), Request.Method.PATCH, Apiclient.ADD_PHONE.getParams(), Collections.singletonList(phone), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Reject Offer
     */

    public void change_photo(final String image) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_PHOTO.getURL(), Request.Method.PATCH, Apiclient.CHANGE_PHOTO.getParams(), Collections.singletonList(image), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Get Offer Data
     */


    public void get_balance() {


    }


    //----------------------------------------------------------------------------------------------

    public void bill_amount(final String order_id, final String bill_amount) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.BILL_AMOUNT.getURL(), Request.Method.PATCH, Apiclient.BILL_AMOUNT.getParams(), Arrays.asList(order_id, bill_amount), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func GET PROMO CODE
     */

    public void promoCode(final String promo_code) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.ADD_PROMO_CODE.getURL(), Request.Method.POST, Apiclient.ADD_PROMO_CODE.getParams(), Arrays.asList(promo_code), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------


}

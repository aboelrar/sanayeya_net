package www.dm.sanayeya.net.NetworkLayer;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONException;

import java.lang.reflect.Array;
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

    public void loginUser(final String email, final String pass, final String firebase_token) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL(), Apiclient.LOGIN_USER.getParams(), Arrays.asList(email, pass, firebase_token), Request.Method.POST, 0);

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

    public void insertUser(final String name, final String type, final String phone, final String email, final String lat, final String lng, final String password, final String firebase_token, String whatsapp) {

        apiRouter.performRequest(Apiclient.INSERT_USER.getURL(), Apiclient.INSERT_USER.getParams(), Arrays.asList(name, type, phone, email, lat, lng, password, firebase_token, whatsapp), Request.Method.POST, 2);

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
     * @func LOGOUT
     */

    public void logout() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.LOGOUT.getURL(), Request.Method.POST, Apiclient.LOGOUT.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func CHOOSE HOME SERVICES
     */

    public void choose_home_service() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHOOSE_HOME_SERVICES.getURL(), Request.Method.GET, Apiclient.CHOOSE_HOME_SERVICES.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func HOME SERVICES DATA
     */

    public void home_service_data(String id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.HOME_SERVICES_DATA.getURL() + id, Request.Method.GET, Apiclient.HOME_SERVICES_DATA.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func HOME SERVICES DATA
     */

    public void home_service_details(String id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.HOME_SERVICE_DETAILS.getURL() + id, Request.Method.GET, Apiclient.HOME_SERVICE_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func PROVINCE
     */

    public void province() {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.PROVINCE.getURL(), Request.Method.GET, Apiclient.PROVINCE.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func CAR MODELS
     */

    public void car_models() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CAR_MODELS.getURL(), Request.Method.GET, Apiclient.CAR_MODELS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func WORK SHOP TYPES
     */

    public void work_shops_types() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.WORK_SHOPS.getURL(), Request.Method.GET, Apiclient.WORK_SHOPS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func INDUSTERIAL SERVICES LIST
     */

    public void industerial_services_list(final String car_model_id, final String workShop_id, final String province_id) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.INDUSTERIAL_SERVICES.getURL() + "?car_model_id=" + car_model_id + "&workShop_id=" + workShop_id + "&province_id=" + province_id, Request.Method.GET, null, null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func INDUSTERIAL DETAILS
     */

    public void industerial_details(final String id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.INDUSTERIAL_SERVICES_DETAILS.getURL() + "/" + id, Request.Method.GET, Apiclient.INDUSTERIAL_SERVICES_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func TWINTY FOUR
     */

    public void twenty_four(final String lat, final String lng) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.TWENTY_FOUR.getURL() + "?lat=" + lat + "&lng=" + lng, Request.Method.GET, Apiclient.INDUSTERIAL_SERVICES_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Twenty four details
     */

    public void twenty_four_details(final String id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.TWENTY_FOUR_DETAILS.getURL() + "/" + id, Request.Method.GET, Apiclient.TWENTY_FOUR_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func WORKSHOP RATE
     */

    public void workshop_rate(final String id, final String rate, final String comment) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.WORKSHOP_RATE.getURL() + "/" + id, Request.Method.POST, Apiclient.WORKSHOP_RATE.getParams(), Arrays.asList(rate, comment), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Industerial Rate
     */

    public void industerial_rate(final String id, final String rate, final String comment) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.INDUSTERIAL_RATE.getURL() + "/" + id, Request.Method.POST, Apiclient.INDUSTERIAL_RATE.getParams(), Arrays.asList(rate, comment), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func HOME SERVICES RATE
     */

    public void home_services_rate(final String id, final String rate, final String comment) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.HOMESERVICES_RATE.getURL() + "/" + id, Request.Method.POST, Apiclient.HOMESERVICES_RATE.getParams(), Arrays.asList(rate, comment), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Get notifcation Data
     */

    public void request_winch(String location_lat, String location_lng, String location_address, String destination_lat, String destination_lng, String destination_address) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.REQUEST_WENCH.getURL(), Request.Method.POST, Apiclient.REQUEST_WENCH.getParams(), Arrays.asList(location_lat, location_lng, location_address, destination_lat, destination_lng, destination_address), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func My Orders
     */

    public void my_orders() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.MY_ORDERS.getURL(), Request.Method.GET, Apiclient.MY_ORDERS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Confirm Order
     */

    public void confirm_order(final String order_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CONFIRM_ORDER.getURL() + "/" + order_id, Request.Method.POST, Apiclient.CONFIRM_ORDER.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Accept Reject Request
     */

    public void accept_reject_request(final String request_id, String status) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.ACCEPT_REJECT_REQUEST.getURL() + "/" + request_id, Request.Method.POST, Apiclient.ACCEPT_REJECT_REQUEST.getParams(), Arrays.asList(status), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Change Language
     */


    public void change_language(String language) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.LANGUAGE.getURL(), Request.Method.POST, Apiclient.LANGUAGE.getParams(), Arrays.asList(language), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    public void bill_amount(final String order_id, final String bill_amount) {

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func GET PROMO CODE
     */

    public void promoCode(final String promo_code) {


    }
    //----------------------------------------------------------------------------------------------


}

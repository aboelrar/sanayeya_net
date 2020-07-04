package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.send_data;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_active_account.controller.active_account;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.model.signupDatum;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.model.signupRootClass;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_welcome_screen.Scenario_winch_signup.controller.winch_signup;

import static www.dm.sanayeya.net.utils.utils.yoyo;

public class signup extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.signup)
    Button signup;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    signupRootClass signupRootClass;
    signupDatum signupDatum;
    String token;
    @BindView(R.id.whatsapp)
    EditText whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNER
        back.setOnClickListener(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        //FIREBASE TOKEN
        firebase_token();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        } else if (view.getId() == R.id.login) {
            startActivity(new Intent(signup.this, www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login.class));
        } else if (view.getId() == R.id.signup) {
            signup_validation();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login.class));
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        //DISMISS DIALOG
        new utils().dismiss_dialog(signup.this);

        Gson gson = new Gson();
        signupRootClass = gson.fromJson(model.getResponse(), signupRootClass.class);

        //CHECK STATUS ZERO OR ONE TO SHOW RESPONSE
        if (signupRootClass.getStatus() == 0) {

            Toasty.error(signup.this, signupRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        } else if (signupRootClass.getStatus() == 1) {

            //GET SERVER DATA
            signupDatum = signupRootClass.getData();

            //SAVE LOCAL DATA
            save_local_data();

            //GO TO ACTIVE CODE
            Intent intent = new Intent(this, active_account.class);
            intent.putExtra("type", signupDatum.getType());
            startActivity(intent);


        }
    }

    @Override
    public void OnError(VolleyError error) {

    }

    //SIGN UP VAILDATION
    void signup_validation() {

        if (username.getText().toString().length() < 5)  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.user_val);
            username.setError(username_val);
            yoyo(R.id.username, username);
        } else if (email.getText().toString().length() < 6)  //VALIDATION ON EMAIL
        {
            String email_val = getResources().getString(R.string.email_val);
            email.setError(email_val);
            yoyo(R.id.email, email);
        } else if (phone.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String phone_val = getResources().getString(R.string.phone_val);
            phone.setError(phone_val);
            yoyo(R.id.phone, phone);
        } else if (password.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            password.setError(pass_val);
            yoyo(R.id.password, password);
        }  else if (whatsapp.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String whats_val = getResources().getString(R.string.whatsapp_val);
            whatsapp.setError(whats_val);
            yoyo(R.id.whatsapp, whatsapp);
        }
        else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(signup.this);

            //CALL API
            new Apicalls(signup.this, this).insertUser(username.getText().toString(),
                    "user", phone.getText().toString(), email.getText().toString(),
                    "2156.01", "78545.55", password.getText().toString(), firebase_token(), whatsapp.getText().toString());
        }
    }

    //SAVE LOCAL DATA
    void save_local_data() {

        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(signupDatum.getUsername());
        arrayList.add(signupDatum.getEmail());
        arrayList.add(signupDatum.getPhone());
        arrayList.add(signupDatum.getToken());
        arrayList.add(signupDatum.getType());


        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(signup.this, arrayList.get(0)); //ADD USER NAME
                send_data.send_email(signup.this, arrayList.get(1)); //ADD Email
                send_data.send_phone(signup.this, arrayList.get(2)); //ADD PHONE
                send_data.send_token(signup.this, arrayList.get(3)); //ADD TOKEN
                send_data.send_type(signup.this, arrayList.get(4)); //ADD TYPE
                send_data.login_status(signup.this, true); //ADD STATUS


            }
        });
    }

    public String firebase_token() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
                Log.e("token_is", token);
                // send it to server
            }
        });
        return token;
    }
}

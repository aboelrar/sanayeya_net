package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.local_data.send_data;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_choose_login.controller.choose_login;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_forget_password.controller.forget_password;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.model.Datum;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.model.login_model;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_welcome_screen.Scenario_winch_signup.controller.winch_signup;

import static www.dm.sanayeya.net.utils.utils.yoyo;

public class login extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.signup)
    TextView signup;
    @BindView(R.id.forget_pass)
    TextView forgetPass;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    login_model login_model;
    Datum data;
    String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNER
        back.setOnClickListener(this);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        forgetPass.setOnClickListener(this);

        //GET FIREBASE TOKEN
        firebase_token();

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(login.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        } else if (view.getId() == R.id.signup) {
            startActivity(new Intent(login.this, choose_login.class));
        } else if (view.getId() == R.id.login) {
            login_validation();  //LOGIN VALIDATION
        } else if (view.getId() == R.id.forget_pass) {
            startActivity(new Intent(login.this, forget_password.class));
        }
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        //DISMISS DIALOG
        new utils().dismiss_dialog(this);

        Gson gson = new Gson();
        login_model = gson.fromJson(model.getResponse(), login_model.class);

        if (login_model.getStatus() == 0) {

            Toasty.error(login.this, login_model.getMessage(), Toasty.LENGTH_LONG).show();

        } else if (login_model.getStatus() == 1) {


            data = login_model.getData();

            Toast.makeText(this, "" + data.getType(), Toast.LENGTH_SHORT).show();


            //SAVE LOCAL DATA IN BACKGROUND
            save_local_data();

            //OPEN DIALOG
            loading loading = new loading();
            loading.dialog(login.this, R.layout.successful_login, .80, data.getType());

        }
    }

    @Override
    public void OnError(VolleyError error) {
        new utils().dismiss_dialog(login.this);  //DISMISS PROGRESS DIALOG
    }

    //LOGIN VAILDATION
    void login_validation() {

        if (username.getText().toString().length() < 5)  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.user_val);
            username.setError(username_val);
            yoyo(R.id.username, username);
        } else if (password.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            password.setError(pass_val);
            yoyo(R.id.password, password);
        } else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(this);

            //CALL API
            new Apicalls(this, this).loginUser(username.getText().toString(), password.getText().toString(), firebase_token());
        }
    }

    //SAVE LOCAL DATA
    void save_local_data() {

        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(data.getUsername());
        arrayList.add(data.getEmail());
        arrayList.add(data.getPhone());
        arrayList.add(data.getToken());
        arrayList.add(data.getType());

        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(login.this, arrayList.get(0)); //ADD USER NAME
                send_data.send_email(login.this, arrayList.get(1)); //ADD Email
                send_data.send_phone(login.this, arrayList.get(2)); //ADD PHONE
                send_data.send_token(login.this, arrayList.get(3)); //ADD TOKEN
                send_data.send_type(login.this, arrayList.get(4)); //ADD TYPE
                send_data.login_status(login.this, true); //ADD STATUS

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

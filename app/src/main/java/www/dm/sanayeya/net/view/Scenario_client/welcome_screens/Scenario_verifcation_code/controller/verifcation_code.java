package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_verifcation_code.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.network_check_status.regist_network_broadcast;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_verifcation_code.model.reset_passRootClass;

import static www.dm.sanayeya.net.utils.utils.yoyo;

public class verifcation_code extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.verifcation_code)
    EditText verifcationCode;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.apply)
    Button apply;
    reset_passRootClass reset_passRootClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifcation_code);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);
        apply.setOnClickListener(this);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(verifcation_code.this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back)
        {
            finish();
        }
        else if(view.getId() == R.id.apply)
        {
            reset_password_validation();
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        //DISMISS DIALOG
        new utils().dismiss_dialog(this);

        //GET DATA
        Gson gson = new Gson();
        reset_passRootClass = gson.fromJson(""+model.getResponse(),reset_passRootClass.class);

        //CHECK IF RESET PASS OR NOT
        if(reset_passRootClass.getStatus() == 0)
        {
            Toasty.error(verifcation_code.this, reset_passRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        }
        else if(reset_passRootClass.getStatus() == 1)
        {
            Toasty.success(verifcation_code.this, reset_passRootClass.getMessage(), Toasty.LENGTH_LONG).show();
            startActivity(new Intent(this, login.class));
        }


    }

    @Override
    public void OnError(VolleyError error) {

    }

    //SET RESET PASSWORD
    void reset_password_validation() {

        if (verifcationCode.getText().toString().length() < 4)  //VALIDATION ON USERNAME
        {
            String ver_code_val = getResources().getString(R.string.ver_code_val);
            verifcationCode.setError(ver_code_val);
            yoyo(R.id.verifcation_code, verifcationCode);
        } else if (password.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            password.setError(pass_val);
            yoyo(R.id.password, password);
        } else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(this);

            //CALL API
            new Apicalls(this, this).reset_pass(password.getText().toString(), verifcationCode.getText().toString());
        }
    }
}

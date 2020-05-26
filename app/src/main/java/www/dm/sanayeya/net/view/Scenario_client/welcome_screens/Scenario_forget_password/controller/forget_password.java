package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_forget_password.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_forget_password.model.forget_passRootClass;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.loading;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_verifcation_code.controller.verifcation_code;

import static www.dm.sanayeya.net.utils.utils.yoyo;

public class forget_password extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.apply)
    Button apply;
    forget_passRootClass forget_passRootClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);
        apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        } else if (view.getId() == R.id.apply) {
            forgetpass_validation();
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
        Gson gson =  new Gson();
        forget_passRootClass = gson.fromJson(""+model.getJsonObject(),forget_passRootClass.class);

        //CHECK IF EMAIL EXIST TO SEND CODE
        if(forget_passRootClass.getStatus() == 0)
        {
            Toasty.error(forget_password.this, forget_passRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        }
        else if(forget_passRootClass.getStatus() == 1)
        {

            //SUCCESSFUL DIALOG
            Toasty.success(forget_password.this, forget_passRootClass.getMessage(), Toasty.LENGTH_LONG).show();
            startActivity(new Intent(this,verifcation_code.class));

        }

    }

    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(this, ""+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
    }

    //FORGET PASSWORD VAILDATION
    void forgetpass_validation() {

        if (email.getText().toString().length() < 5)  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.email_val);
            email.setError(username_val);
            yoyo(R.id.email, email);

        } else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(this);

            //CALL API
            new Apicalls(this, this).FORGET_PASS(email.getText().toString());
        }
    }
}

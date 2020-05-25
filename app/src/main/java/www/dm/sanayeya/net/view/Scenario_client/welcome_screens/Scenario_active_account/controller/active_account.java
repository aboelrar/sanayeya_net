package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_active_account.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.MainActivity;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_active_account.model.active_accountRootClass;

import static www.dm.sanayeya.net.utils.utils.yoyo;

public class active_account extends AppCompatActivity implements View.OnClickListener, NetworkInterface {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.verifcation_code)
    EditText verifcationCode;
    @BindView(R.id.active)
    Button active;
    @BindView(R.id.resend_code)
    TextView resendCode;
    active_accountRootClass active_accountRootClass;
    boolean resend_code = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_account);
        ButterKnife.bind(this);

        //SET ON CLICK LISTNERS
        back.setOnClickListener(this);
        active.setOnClickListener(this);
        resendCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.active)
        {
            active_account_validation();
        }
        else if(view.getId() == R.id.back)
        {
            finish();
        }
        else if(view.getId() == R.id.resend_code){

            resend_code = true; //SET CHECK TRUE

            //CALL API
            new Apicalls(this, this).resend_code();
        }

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        //DISMISS DIALOG
        new utils().dismiss_dialog(this);

        if(resend_code == false)
        {
            //GET DATA
            Gson gson =  new Gson();
            active_accountRootClass = gson.fromJson(""+model.getJsonObject(),active_accountRootClass.class);

            //CHECK IF EMAIL EXIST TO SEND CODE
            if(active_accountRootClass.getStatus() == 0)
            {
                Toasty.error(active_account.this, active_accountRootClass.getMessage(), Toasty.LENGTH_LONG).show();
            }
            else if(active_accountRootClass.getStatus() == 1)
            {
                Toasty.success(active_account.this, active_accountRootClass.getMessage(), Toasty.LENGTH_LONG).show();
                startActivity(new Intent(this, MainActivity.class));  //GO TO RESET PASSWORD
            }
        }

        //RECENT CODE
        else if(resend_code == true) {
            if(active_accountRootClass.getStatus() == 0)
            {
                Toasty.error(active_account.this, active_accountRootClass.getMessage(), Toasty.LENGTH_LONG).show();
            }
            else if(active_accountRootClass.getStatus() == 1)
            {
                Toasty.success(active_account.this, active_accountRootClass.getMessage(), Toasty.LENGTH_LONG).show();
            }
            resend_code = false;
        }



    }

    @Override
    public void OnError(VolleyError error) {

    }

    //ACTIVE ACCOUNT VAILDATION
    void active_account_validation() {

        if (verifcationCode.getText().toString().length() < 4)  //VALIDATION ON USERNAME
        {
            String ver_code_val = getResources().getString(R.string.ver_code_val);
            verifcationCode.setError(ver_code_val);
            yoyo(R.id.verifcation_code, verifcationCode);

        } else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(this);

            //CALL API
            new Apicalls(this, this).active_account(verifcationCode.getText().toString());
        }
    }
}

package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_chnage_password.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Scenario_chnage_password.model.change_passRootClass;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup;

import static www.dm.sanayeya.net.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class change_pass extends Fragment implements View.OnClickListener , NetworkInterface {
    View view;
    EditText old_pass;
    EditText new_pass;
    EditText co_new_pass;
    Button change_pass;
    change_passRootClass change_passRootClass;

    public change_pass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.change_pass, container, false);

        //DEFINE ALL VARS
        old_pass = view.findViewById(R.id.old_pass);
        new_pass = view.findViewById(R.id.new_pass);
        co_new_pass = view.findViewById(R.id.co_new_pass);

        //SET ON CHANGE PASS
        change_pass = view.findViewById(R.id.change_pass);
        change_pass.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
       if(view.getId() == R.id.change_pass)
       {
           setChange_pass();
       }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        //DISMISS DIALOG
        new utils().dismiss_dialog(getContext());

        Gson gson = new Gson();
        change_passRootClass = gson.fromJson(""+model.getJsonObject(),change_passRootClass.class);

        if(change_passRootClass.getStatus() == 0)
        {
            Toasty.error(getContext(), ""+change_passRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        }
        else if(change_passRootClass.getStatus() == 1)
        {
            Toasty.success(getContext(), ""+change_passRootClass.getMessage(), Toasty.LENGTH_LONG).show();
        }

    }

    @Override
    public void OnError(VolleyError error) {
        Toasty.error(getContext(), ""+error.networkResponse.statusCode, Toasty.LENGTH_LONG).show();
    }

    //CHANGE PASS
    void setChange_pass()
    {
        if (old_pass.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            old_pass.setError(pass_val);
            yoyo(R.id.old_pass, old_pass);
        } else if (new_pass.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            new_pass.setError(pass_val);
            yoyo(R.id.new_pass, new_pass);
        }else if (!co_new_pass.getText().toString().equals(new_pass.getText().toString()))  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_d_match);
            co_new_pass.setError(pass_val);
            yoyo(R.id.co_new_pass, co_new_pass);
        }
        else {
            //CALL PROGRESS DIALOG
            new utils().set_dialog(getContext());

            //CALL SERVER
            new Apicalls(getContext(),this).change_pass(old_pass.getText().toString(),
                    new_pass.getText().toString());
        }
    }
}

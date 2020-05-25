package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_edit_profile.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import www.dm.sanayeya.net.NetworkLayer.Apicalls;
import www.dm.sanayeya.net.NetworkLayer.NetworkInterface;
import www.dm.sanayeya.net.NetworkLayer.ResponseModel;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.local_data.saved_data;
import www.dm.sanayeya.net.local_data.send_data;
import www.dm.sanayeya.net.utils.utils;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.loading;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.Controller.login;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.model.Datum;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_login.model.login_model;
import www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.controller.signup;

import static www.dm.sanayeya.net.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class edit_profile extends Fragment implements View.OnClickListener, NetworkInterface {
    View view;
    EditText username, email, phone;
    Button apply;
    login_model login_model;
    Datum data;

    public edit_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.edit_profile, container, false);

        //SET USERNAME
        username = view.findViewById(R.id.username);
        username.setText(new saved_data().get_name(getContext()));

        //SET EMAIL
        email = view.findViewById(R.id.email);
        email.setText(new saved_data().get_email(getContext()));

        //SET PHONE
        phone = view.findViewById(R.id.phone);
        phone.setText(new saved_data().get_phone(getContext()));

        //SET APPLY
        apply = view.findViewById(R.id.apply);
        apply.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.apply)
        {
            profile_validation();
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
        login_model = gson.fromJson(""+model.getJsonObject(), login_model.class);

        if (login_model.getStatus() == 0) {

            Toasty.error(getContext(), login_model.getMessage(), Toasty.LENGTH_LONG).show();

        } else if (login_model.getStatus() == 1) {

            data = login_model.getData();

            //SAVE LOCAL DATA IN BACKGROUND
            save_local_data();

            //MESSAGE IS
            Toasty.success(getContext(), login_model.getMessage(), Toasty.LENGTH_LONG).show();



        }

    }

    @Override
    public void OnError(VolleyError error) {

    }

    //SIGN UP VAILDATION
    void profile_validation() {

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
        } else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(getContext());

            //CALL API
            new Apicalls(getContext(), this).edit_profile(username.getText().toString(),
                     phone.getText().toString(), email.getText().toString(),
                    "2156.01", "78545.55");
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

        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(getContext(), arrayList.get(0)); //ADD USER NAME
                send_data.send_email(getContext(), arrayList.get(1)); //ADD Email
                send_data.send_phone(getContext(), arrayList.get(2)); //ADD PHONE
                send_data.send_token(getContext(), arrayList.get(3)); //ADD TOKEN
            }
        });
    }
}

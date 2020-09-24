package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.add_warsha.controller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import www.dm.sanayeya.net.R;

public class add_warsha extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.arabic_name)
    EditText arabicName;
    @BindView(R.id.english_name)
    EditText englishName;
    @BindView(R.id.arabic_desc)
    EditText arabicDesc;
    @BindView(R.id.english_desc)
    EditText englishDesc;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.sms)
    EditText sms;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.apply)
    Button apply;
    String image = "";
    int storage_premission_code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_warsha);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.apply) {
            if (image.equals(""))
            {
                Toasty.warning(add_warsha.this,getString(R.string.please_upload_img),Toasty.LENGTH_SHORT).show();
            }
               else if ((arabicName.getText().toString().equals("")) || (englishName.getText().toString().equals("")) ||
                        (arabicDesc.getText().toString().equals("")) || (englishDesc.getText().toString().equals("")) ||
                        (phone.getText().toString().equals("")) || (sms.getText().toString().equals("")) ||
                        (address.getText().toString().equals(""))) {
                Toasty.warning(add_warsha.this,getString(R.string.complete_all_info),Toasty.LENGTH_SHORT).show();
                } else {

                }
        }
    }

    //Premission
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void grantedOrNot() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(add_warsha.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(add_warsha.this).setTitle("Premission To Open Gallery").setMessage("If you need to upload image you must do this premission").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                //positive
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(add_warsha.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, storage_premission_code);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(i, "Select Your Photo"), 1);
                }
            }).create().show();
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, storage_premission_code);
        }
    }
}
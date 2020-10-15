package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.add_warsha.controller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

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

        //SET ON IMAGE CLICK
        pic.setOnClickListener(this);
        apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.apply) {
            if (image.equals("")) {
                Toasty.warning(add_warsha.this, getString(R.string.please_upload_img), Toasty.LENGTH_SHORT).show();
            } else if ((arabicName.getText().toString().equals("")) || (englishName.getText().toString().equals("")) ||
                    (arabicDesc.getText().toString().equals("")) || (englishDesc.getText().toString().equals("")) ||
                    (phone.getText().toString().equals("")) || (sms.getText().toString().equals("")) ||
                    (address.getText().toString().equals(""))) {
                Toasty.warning(add_warsha.this, getString(R.string.complete_all_info), Toasty.LENGTH_SHORT).show();
            } else {

            }
        } else if (v.getId() == R.id.pic) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                grantedOrNot();
            }
        } else if(v.getId() == R.id.apply)
        {

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == storage_premission_code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Select Your Photo"), 1);
            } else {
                Toast.makeText(add_warsha.this, "not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                {
                    Uri selectedImage = data.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap SelectedPhoto = BitmapFactory.decodeStream(imageStream);
                    Bitmap bitmap = Bitmap.createScaledBitmap(SelectedPhoto, 300, 300, true);

                    //SET IMAGE
                    pic.setImageBitmap(bitmap);
                }
            }
        }
    }
}
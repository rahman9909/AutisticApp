package saim.com.autisticapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import saim.com.autisticapp.Util.SharedPrefDatabase;

public class ActivityParentsCapture extends AppCompatActivity {

    ImageView imgCaptureMain;
    Button btnCaptureMain, btnCaptureSave;
    EditText inputCaptureMain;
    Spinner spinCaptureMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_parents_capture);

        init();


        String path = getExternalCacheDir().getPath();
        //Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
    }


    private void init() {
        haveStoragePermission();

        imgCaptureMain = (ImageView) findViewById(R.id.imgCaptureMain);
        btnCaptureMain = (Button) findViewById(R.id.btnCaptureMain);
        btnCaptureSave = (Button) findViewById(R.id.btnCaptureSave);
        inputCaptureMain = (EditText) findViewById(R.id.inputCaptureMain);
        spinCaptureMain = (Spinner) findViewById(R.id.spinCaptureMain);

        actionEvent();
    }

    private void actionEvent() {
        btnCaptureMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(ActivityParentsCapture.this);
            }
        });

        btnCaptureSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinCaptureMain.getSelectedItem().toString().equals("Select Entry")) {
                    showDialog(getApplicationContext(), "Please select family member.");
                } else {
                    if (inputCaptureMain.getText().toString().isEmpty()) {
                        showDialog(getApplicationContext(), "Please enter family member name.");
                    } else {
                        if ( imgCaptureMain.getDrawable() == null ) {
                            showDialog(getApplicationContext(), "Please family member photo.");
                        } else {
                            Bitmap bitmap = ((BitmapDrawable) imgCaptureMain.getDrawable()).getBitmap();
                            if (savebitmap(bitmap)) {

                                if (spinCaptureMain.getSelectedItem().toString().equals("FATHER")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreFather(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("MOTHER")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreMother(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("GRAND_FATHER")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreGFather(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("GRAND_MOTHER")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreGMother(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("BROTHER_1")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreBrother_1(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("BROTHER_2")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreBrother_2(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("BROTHER_3")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreBrother_3(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("SISTER_1")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreSister_1(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("SISTER_2")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreSister_2(inputCaptureMain.getText().toString());
                                } else if (spinCaptureMain.getSelectedItem().toString().equals("SISTER_3")) {
                                    new SharedPrefDatabase(getApplicationContext()).StoreSister_3(inputCaptureMain.getText().toString());
                                }

                                showDialogSuccess(getApplicationContext(), "Family member saved successfully.");
                            }
                        }
                    }
                }


            }
        });
    }

    public boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imgCaptureMain.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    public Boolean savebitmap(Bitmap bmp) {
        Boolean status = false;

        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
            File f = new File(getExternalCacheDir() + File.separator + spinCaptureMain.getSelectedItem().toString() +".jpg");
            status = f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    public void showDialog(Context context, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle("Warnning")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void showDialogSuccess(Context context, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle("Success")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.btn_star_big_on)
                .show();
    }


}

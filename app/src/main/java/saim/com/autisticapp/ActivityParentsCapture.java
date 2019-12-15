package saim.com.autisticapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import saim.com.autisticapp.Util.DBHelper;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class ActivityParentsCapture extends AppCompatActivity {

    TextView txtTitle;
    ImageView imgCaptureMain;
    Button btnCaptureMain, btnCaptureSave, btnCaptureVoice;
    EditText inputCaptureMain;
    Spinner spinCaptureMain;

    DBHelper mydb;

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

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        imgCaptureMain = (ImageView) findViewById(R.id.imgCaptureMain);
        btnCaptureMain = (Button) findViewById(R.id.btnCaptureMain);
        btnCaptureVoice = (Button) findViewById(R.id.btnCaptureVoice);
        btnCaptureSave = (Button) findViewById(R.id.btnCaptureSave);
        inputCaptureMain = (EditText) findViewById(R.id.inputCaptureMain);
        spinCaptureMain = (Spinner) findViewById(R.id.spinCaptureMain);


        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.capture_bn_1);
            btnCaptureMain.setText(R.string.capture_bn_2);
            btnCaptureSave.setText(R.string.capture_bn_3);
            btnCaptureVoice.setText(R.string.capture_bn_4);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spiner_entry_bn));
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinCaptureMain.setAdapter(spinnerArrayAdapter);

        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.capture_en_1);
            btnCaptureMain.setText(R.string.capture_en_2);
            btnCaptureSave.setText(R.string.capture_en_3);
            btnCaptureVoice.setText(R.string.capture_en_4);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spiner_entry));
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinCaptureMain.setAdapter(spinnerArrayAdapter);
        }


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


        btnCaptureVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                String fileName = dateFormat.format(date) + ".mp3";
                recordAudio(fileName);
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
                            /*
                            Bitmap bitmap = ((BitmapDrawable) imgCaptureMain.getDrawable()).getBitmap();
                            savebitmap(bitmap);
                            Toast.makeText(getApplicationContext(), spinCaptureMain.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
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

                            }*/

                            if (btnCaptureVoice.getText().toString().isEmpty()) {
                                showDialog(getApplicationContext(), "Please enter voice.");
                            } else {
                                Bitmap bitmap = ((BitmapDrawable) imgCaptureMain.getDrawable()).getBitmap();
                                mydb = new DBHelper(getApplicationContext());
                                String name = inputCaptureMain.getText().toString();
                                String relation = spinCaptureMain.getSelectedItem().toString();
                                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                                Date date = new Date();
                                String fileName = dateFormat.format(date) + "";
                                savebitmap2(bitmap, fileName);
                                Toast.makeText(getApplicationContext(), name + "\n" + relation + "\n" + fileName + '\n' + btnCaptureVoice.getTag().toString(), Toast.LENGTH_LONG).show();
                                Boolean a = mydb.insertFamilyMember(name, relation, fileName, btnCaptureVoice.getTag().toString());

                                if (a) {
                                    showDialogSuccess(getApplicationContext(), "Saved successfully.");
                                }

                            }

                        }
                    }
                }


            }
        });
    }

    public boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
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

    public Boolean savebitmap2(Bitmap bmp, String filename) {
        Boolean status = false;

        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
            File f = new File(getExternalCacheDir() + File.separator + filename + ".jpg");
            status = f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }


    public void recordAudio(final String fileName) {
        final MediaRecorder recorder = new MediaRecorder();
        ContentValues values = new ContentValues(3);
        values.put(MediaStore.MediaColumns.TITLE, fileName);
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(getExternalCacheDir() + File.separator + fileName);
        try {
            recorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Recording Voice");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setButton("Stop recording", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mProgressDialog.dismiss();
                recorder.stop();
                recorder.release();

                btnCaptureVoice.setText("Voice Recorded");
                btnCaptureVoice.setTag(fileName);
            }
        });

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface p1) {
                recorder.stop();
                recorder.release();

                btnCaptureVoice.setText("Voice Recorded");
                btnCaptureVoice.setTag(fileName);
            }
        });
        recorder.start();
        mProgressDialog.show();
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

package saim.com.autisticapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import saim.com.autisticapp.Util.DBHelperEmoji;
import saim.com.autisticapp.Util.DBHelperRashed;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_splash);

        haveStoragePermission();
    }

    public void DataProcess() {
        new DBHelperRashed(this).deleteContact2();
        new DBHelperEmoji(this).deleteContact2();

        if (new DBHelperRashed(this).getAllFamilyMembers().size() == 0) {
            new DBHelperRashed(this).insertFamilyMember("Rased", "ic_game_rased_eye", "ic_game_rased", "a_rased.mpeg");
            new DBHelperRashed(this).insertFamilyMember("Ibu", "ic_game_ibu_eye", "ic_game_ibu", "a_ibu.mpeg");
            new DBHelperRashed(this).insertFamilyMember("Fazlu", "ic_game_fazlu_eye", "ic_game_fazlu", "a_fazlu.mpeg");
            new DBHelperRashed(this).insertFamilyMember("Ashraf", "ic_game_ashraf_eye", "ic_game_ashraf", "a_asraf.mpeg");
        }

        if (new DBHelperEmoji(this).getAllFamilyMembers().size() == 0) {
            new DBHelperEmoji(this).insertFamilyMember("Neutral", "ic_nutral_emoji", "ic_nutral", "সাধারণ");
            new DBHelperEmoji(this).insertFamilyMember("Happy", "ic_happy_emoji", "ic_happy", "সুখী");
            new DBHelperEmoji(this).insertFamilyMember("Sad", "ic_sad_emoji", "ic_sad", "দু:খিত");
            new DBHelperEmoji(this).insertFamilyMember("Surprised", "ic_surprised_emoji", "ic_surprised", "অবাক");
            new DBHelperEmoji(this).insertFamilyMember("Angry", "ic_angry_emoji", "ic_angry", "রাগান্বিত");
            new DBHelperEmoji(this).insertFamilyMember("Disgusted", "ic_distuested_emoji", "ic_distuested", "বিরক্ত");
            new DBHelperEmoji(this).insertFamilyMember("Fear", "ic_fear_emoji", "ic_fear", "ভয়");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2500);
    }


    public void haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                DataProcess();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.RECORD_AUDIO,}, 1);
            }
        } else {
            DataProcess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
            DataProcess();
        } else {
            haveStoragePermission();
        }
    }

}

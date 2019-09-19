package saim.com.autisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import saim.com.autisticapp.Util.DBHelperRashed;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_splash);

        new DBHelperRashed(this).deleteContact2();

        if (new DBHelperRashed(this).getAllFamilyMembers().size() == 0) {
            new DBHelperRashed(this).insertFamilyMember("Rased", "ic_game_rased_eye", "ic_game_rased");
            new DBHelperRashed(this).insertFamilyMember("Ibu", "ic_game_ibu_eye", "ic_game_ibu");
            new DBHelperRashed(this).insertFamilyMember("Fazlu", "ic_game_fazlu_eye", "ic_game_fazlu");
            new DBHelperRashed(this).insertFamilyMember("Ashraf", "ic_game_ashraf_eye", "ic_game_ashraf");
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
}

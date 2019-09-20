package saim.com.autisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import saim.com.autisticapp.Util.DBHelperEmoji;
import saim.com.autisticapp.Util.DBHelperRashed;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_splash);

        new DBHelperRashed(this).deleteContact2();
        new DBHelperEmoji(this).deleteContact2();

        if (new DBHelperRashed(this).getAllFamilyMembers().size() == 0) {
            new DBHelperRashed(this).insertFamilyMember("Rased", "ic_game_rased_eye", "ic_game_rased");
            new DBHelperRashed(this).insertFamilyMember("Ibu", "ic_game_ibu_eye", "ic_game_ibu");
            new DBHelperRashed(this).insertFamilyMember("Fazlu", "ic_game_fazlu_eye", "ic_game_fazlu");
            new DBHelperRashed(this).insertFamilyMember("Ashraf", "ic_game_ashraf_eye", "ic_game_ashraf");
        }

        if (new DBHelperEmoji(this).getAllFamilyMembers().size() == 0) {
            new DBHelperEmoji(this).insertFamilyMember("Nutral", "ic_nutral_emoji", "ic_nutral");
            new DBHelperEmoji(this).insertFamilyMember("Happy", "ic_happy_emoji", "ic_happy");
            new DBHelperEmoji(this).insertFamilyMember("Sad", "ic_sad_emoji", "ic_sad");
            new DBHelperEmoji(this).insertFamilyMember("Surprised", "ic_surprised_emoji", "ic_surprised");
            new DBHelperEmoji(this).insertFamilyMember("Angry", "ic_angry_emoji", "ic_angry");
            new DBHelperEmoji(this).insertFamilyMember("Disgusted", "ic_distuested_emoji", "ic_distuested");
            new DBHelperEmoji(this).insertFamilyMember("Fear", "ic_fear_emoji", "ic_fear");
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

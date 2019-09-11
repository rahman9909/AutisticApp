package saim.com.autisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import saim.com.autisticapp.Game.GameFind;

public class ActivityGameSelect extends AppCompatActivity {

    int GAME_TYPE;

    LinearLayout layoutGameTrainYourSelf, layoutGame1, layoutGame2, layoutGame3, layoutGame4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_select);

        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");


        init();
    }

    private void init() {
        layoutGameTrainYourSelf = (LinearLayout) findViewById(R.id.layoutGameTrainYourSelf);
        layoutGame1 = (LinearLayout) findViewById(R.id.layoutGame1);
        layoutGame2 = (LinearLayout) findViewById(R.id.layoutGame2);
        layoutGame3 = (LinearLayout) findViewById(R.id.layoutGame3);
        layoutGame4 = (LinearLayout) findViewById(R.id.layoutGame4);

        layoutGameTrainYourSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameTrain.class);
                intent.putExtra("GAME_TYPE", GAME_TYPE);
                startActivity(intent);
            }
        });

        layoutGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameFind.class);
                intent.putExtra("GAME_TYPE", GAME_TYPE);
                startActivity(intent);
            }
        });
    }
}

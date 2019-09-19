package saim.com.autisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import saim.com.autisticapp.Game.GameFind;
import saim.com.autisticapp.Game.GameMemory;
import saim.com.autisticapp.Game.GameRotate;
import saim.com.autisticapp.Game.GameTrain;
import saim.com.autisticapp.Game2.GameEye2;
import saim.com.autisticapp.Game2.GameFind2;
import saim.com.autisticapp.Game2.GameMemory2;
import saim.com.autisticapp.Game2.GameRotate2;
import saim.com.autisticapp.Game2.GameTrain2;

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

        if (GAME_TYPE == 2) {
            layoutGame4.setVisibility(View.VISIBLE);
        }

        layoutGameTrainYourSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GAME_TYPE == 1) {
                    Intent intent = new Intent(getApplicationContext(), GameTrain.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), GameTrain2.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                }
            }
        });

        layoutGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GAME_TYPE == 1) {
                    Intent intent = new Intent(getApplicationContext(), GameFind.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), GameFind2.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                }

            }
        });

        layoutGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GAME_TYPE == 1) {
                    Intent intent = new Intent(getApplicationContext(), GameMemory.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), GameMemory2.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                }
            }
        });

        layoutGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GAME_TYPE == 1) {
                    Intent intent = new Intent(getApplicationContext(), GameRotate.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), GameRotate2.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                }
            }
        });

        layoutGame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GAME_TYPE == 1) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), GameEye2.class);
                    intent.putExtra("GAME_TYPE", GAME_TYPE);
                    startActivity(intent);
                }
            }
        });
    }
}

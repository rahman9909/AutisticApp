package saim.com.autisticapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityGame extends AppCompatActivity {

    LinearLayout layoutGameFamilyAlbum, layoutGameAmarBonduRased;

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game);


        init();
    }

    private void init() {
        layoutGameFamilyAlbum = (LinearLayout) findViewById(R.id.layoutGameFamilyAlbum);
        layoutGameAmarBonduRased = (LinearLayout) findViewById(R.id.layoutGameAmarBonduRased);

        actionEvent();
    }

    private void actionEvent() {
        layoutGameFamilyAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityGameSelect.class);
                intent.putExtra("GAME_TYPE", 1);
                startActivity(intent);
            }
        });

        layoutGameAmarBonduRased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityGameSelect.class);
                intent.putExtra("GAME_TYPE", 2);
                startActivity(intent);
            }
        });
    }


}

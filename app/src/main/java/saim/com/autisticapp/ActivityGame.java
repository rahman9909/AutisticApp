package saim.com.autisticapp;

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

        actionEvent(layoutGameFamilyAlbum);
        actionEvent(layoutGameAmarBonduRased);
    }

    private void actionEvent(LinearLayout layout) {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}

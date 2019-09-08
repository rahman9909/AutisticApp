package saim.com.autisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityParents extends AppCompatActivity {

    Button btnCapture, btnAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_parents);

        init();
    }

    private void init() {
        btnCapture = (Button) findViewById(R.id.btnCapture);
        btnAlbum = (Button) findViewById(R.id.btnAlbum);

        actionEvent();
    }


    private void actionEvent() {

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityParentsCapture.class));
            }
        });

        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityParentsAlbum2.class));
            }
        });
    }
}

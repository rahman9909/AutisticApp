package saim.com.autisticapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    Button btnFamily, btnExpression, btnGame, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        btnFamily = (Button) findViewById(R.id.btnFamily);
        btnExpression = (Button) findViewById(R.id.btnExpression);
        btnGame = (Button) findViewById(R.id.btnGame);
        btnExit = (Button) findViewById(R.id.btnExit);


        actionEvent();
    }

    private void actionEvent() {

        btnFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExpression = new Intent(getApplicationContext(), ActivityParents.class);
                startActivity(intentExpression);
            }
        });

        btnExpression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExpression = new Intent(getApplicationContext(), ActivityExpression.class);
                startActivity(intentExpression);
            }
        });

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExpression = new Intent(getApplicationContext(), ActivityGame.class);
                startActivity(intentExpression);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    public boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }


}

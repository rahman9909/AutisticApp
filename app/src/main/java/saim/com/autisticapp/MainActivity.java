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

import saim.com.autisticapp.Expression.ExpressionSelect;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnFamily, btnExpression, btnGame, btnLanguage, btnExit;

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
        btnLanguage = (Button) findViewById(R.id.btnLanguage);
        btnExit = (Button) findViewById(R.id.btnExit);


        actionEvent();
    }

    private void actionEvent() {

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            btnLanguage.setText("BN");
            btnFamily.setText(R.string.home_button_bn_1);
            btnExpression.setText(R.string.home_button_bn_2);
            btnGame.setText(R.string.home_button_bn_3);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            btnLanguage.setText("EN");
            btnFamily.setText(R.string.home_button_en_1);
            btnExpression.setText(R.string.home_button_en_2);
            btnGame.setText(R.string.home_button_en_3);
        }


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
                Intent intentExpression = new Intent(getApplicationContext(), ExpressionSelect.class);
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

        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnLanguage.getText().toString().equals("EN")) {
                    new SharedPrefDatabase(getApplicationContext()).StoreLanguage("BN");
                    btnLanguage.setText("BN");
                    btnFamily.setText(R.string.home_button_bn_1);
                    btnExpression.setText(R.string.home_button_bn_2);
                    btnGame.setText(R.string.home_button_bn_3);
                } else if (btnLanguage.getText().toString().equals("BN")) {
                    new SharedPrefDatabase(getApplicationContext()).StoreLanguage("EN");
                    btnLanguage.setText("EN");
                    btnFamily.setText(R.string.home_button_en_1);
                    btnExpression.setText(R.string.home_button_en_2);
                    btnGame.setText(R.string.home_button_en_3);
                }
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
                    checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.RECORD_AUDIO}, 1);
                return false;
            }
        } else {
            return true;
        }
    }


}

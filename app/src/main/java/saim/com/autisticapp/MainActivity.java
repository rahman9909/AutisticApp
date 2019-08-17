package saim.com.autisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnFamily, btnExpression, btnGame;

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


    }


}

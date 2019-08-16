package saim.com.autisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityExpression extends AppCompatActivity {

    TextView txtNeutral, txtLaugh, txtHappy, txtSad, txtAngry, txtFear, txtSurprised, txtDisguested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_expression);

        init();
    }

    private void init() {

        txtNeutral = (TextView) findViewById(R.id.txtNeutral);
        txtLaugh = (TextView) findViewById(R.id.txtLaugh);
        txtHappy = (TextView) findViewById(R.id.txtHappy);
        txtSad = (TextView) findViewById(R.id.txtSad);
        txtAngry = (TextView) findViewById(R.id.txtAngry);
        txtFear = (TextView) findViewById(R.id.txtFear);
        txtSurprised = (TextView) findViewById(R.id.txtSurprised);
        txtDisguested = (TextView) findViewById(R.id.txtDisguested);

        actionEvent(txtNeutral);
        actionEvent(txtLaugh);
        actionEvent(txtHappy);
        actionEvent(txtSad);
        actionEvent(txtAngry);
        actionEvent(txtFear);
        actionEvent(txtSurprised);
        actionEvent(txtDisguested);
    }

    private void actionEvent(final TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityExpressionShow.class);
                intent.putExtra("EXPRESSION", textView.getText().toString());
                startActivity(intent);
            }
        });
    }
}

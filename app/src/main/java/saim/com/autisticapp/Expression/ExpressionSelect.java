package saim.com.autisticapp.Expression;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class ExpressionSelect extends AppCompatActivity {

    TextView txtTitle, txtShowExpression, txtTrainExpression;
    LinearLayout layoutExpression1, layoutExpression2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_expression_select);

        init();
    }

    private void init() {

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtShowExpression = (TextView) findViewById(R.id.txtShowExpression);
        txtTrainExpression = (TextView) findViewById(R.id.txtTrainExpression);

        layoutExpression1 = (LinearLayout) findViewById(R.id.layoutExpression1);
        layoutExpression2 = (LinearLayout) findViewById(R.id.layoutExpression2);

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.expression_bn_1);
            txtShowExpression.setText(R.string.expression_bn_2);
            txtTrainExpression.setText(R.string.expression_bn_3);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.expression_en_1);
            txtShowExpression.setText(R.string.expression_en_2);
            txtTrainExpression.setText(R.string.expression_en_3);
        }

        actionEvent();
    }

    private void actionEvent() {
        layoutExpression1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExpressionShow.class));
            }
        });

        layoutExpression2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExpressionTrain.class));
            }
        });
    }
}

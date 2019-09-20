package saim.com.autisticapp.Expression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import saim.com.autisticapp.R;

public class ExpressionSelect extends AppCompatActivity {

    LinearLayout layoutExpression1, layoutExpression2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_expression_select);

        init();
    }

    private void init() {
        layoutExpression1 = (LinearLayout) findViewById(R.id.layoutExpression1);
        layoutExpression2 = (LinearLayout) findViewById(R.id.layoutExpression2);

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

package saim.com.autisticapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityExpressionShow extends AppCompatActivity {

    String EXPRESSION = "";

    ImageView imgExpression;
    TextView txtExpression;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_expression_show);

        init();
    }

    private void init() {
        this.EXPRESSION = getIntent().getExtras().getString("EXPRESSION");

        imgExpression = (ImageView) findViewById(R.id.imgExpression);
        txtExpression = (TextView) findViewById(R.id.txtExpression);

        setAction();
    }

    private void setAction() {
        if (EXPRESSION.equals("Neutral")) {
            imgExpression.setImageResource(R.drawable.ic_nutral);
            txtExpression.setText(EXPRESSION);
        } else if (EXPRESSION.equals("Laugh")) {
            imgExpression.setImageResource(R.drawable.ic_laugh);
            txtExpression.setText(EXPRESSION);
        } else if (EXPRESSION.equals("Happy")) {
            imgExpression.setImageResource(R.drawable.ic_happy);
            txtExpression.setText(EXPRESSION);
        } else if (EXPRESSION.equals("Sad")) {
            imgExpression.setImageResource(R.drawable.ic_sad);
            txtExpression.setText(EXPRESSION);
        } else if (EXPRESSION.equals("Angry")) {
            imgExpression.setImageResource(R.drawable.ic_angry);
            txtExpression.setText(EXPRESSION);
        } else if (EXPRESSION.equals("Fear")) {
            imgExpression.setImageResource(R.drawable.ic_fear);
            txtExpression.setText(EXPRESSION);
        } else if (EXPRESSION.equals("Surprised")) {
            imgExpression.setImageResource(R.drawable.ic_surprised);
            txtExpression.setText(EXPRESSION);
        } else if (EXPRESSION.equals("Disguested")) {
            imgExpression.setImageResource(R.drawable.ic_distuested);
            txtExpression.setText(EXPRESSION);
        }
    }
}

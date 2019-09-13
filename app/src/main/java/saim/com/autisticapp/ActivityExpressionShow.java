package saim.com.autisticapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ActivityExpressionShow extends AppCompatActivity {

    String EXPRESSION = "";
    private TextToSpeech textToSpeech;

    ImageView imgExpression, imgExpressionSound;
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
        imgExpressionSound = (ImageView) findViewById(R.id.imgExpressionSound);
        txtExpression = (TextView) findViewById(R.id.txtExpression);


        imgExpressionSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speakout(EXPRESSION);
            }
        });

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

        Speakout(EXPRESSION);
    }

    public void Speakout(final String stringVoice) {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    } else {
                        textToSpeech.speak(stringVoice, TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });
    }
}

package saim.com.autisticapp.Game;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelper;

public class GameMemory extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0;

    TextView txtQuestion;
    ImageView qusImage11, qusImage12, qusImage13, qusImage14, qusImgSound;
    DBHelper dbHelper;

    ArrayList<ModelFamily> modelFamilies = new ArrayList<>();
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_memory);

        init();
    }

    private void init() {

        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");
        dbHelper = new DBHelper(this);
        modelFamilies = dbHelper.getAllFamilyMembers();

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        qusImage11 = (ImageView) findViewById(R.id.qusImage11);
        qusImage12 = (ImageView) findViewById(R.id.qusImage12);
        qusImage13 = (ImageView) findViewById(R.id.qusImage13);
        qusImage14 = (ImageView) findViewById(R.id.qusImage14);
        qusImgSound = (ImageView) findViewById(R.id.qusImgSound);

        actionEvent();
    }

    private void actionEvent() {
        String voiceText = "Who is  " + modelFamilies.get(COUNTER).name;
        txtQuestion.setText(voiceText);
        Speakout(voiceText);
        SpeackOutButton(qusImgSound, voiceText);
    }


    private void SpeackOutButton(ImageView speakImage, final String s) {
        speakImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speakout(s);
            }
        });
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

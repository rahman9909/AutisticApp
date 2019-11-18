package saim.com.autisticapp.Game;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelper;

public class GameLetter extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0, a;

    TextView txtQuestion21;
    ImageView qusImage21, qusImage22, qusImgSound;
    DBHelper dbHelper;

    ArrayList<ModelFamily> modelFamilies = new ArrayList<>();
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_letter);

        init();
    }

    private void init() {

        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");
        dbHelper = new DBHelper(this);
        modelFamilies = dbHelper.getAllFamilyMembersNew();

        txtQuestion21 = (TextView) findViewById(R.id.txtQuestion21);
        qusImage21 = (ImageView) findViewById(R.id.qusImage21);
        qusImage22 = (ImageView) findViewById(R.id.qusImage22);
        qusImgSound = (ImageView) findViewById(R.id.qusImgSound);

        actionEvent();
    }

    private void actionEvent() {

        a = getRandomNumber(modelFamilies);
        Toast.makeText(this, a + "", Toast.LENGTH_LONG).show();
        Log.d("SAIM_LIST", a + "");

        String voiceText = "Who is  " + modelFamilies.get(a).name;
        txtQuestion21.setText(voiceText);
        Speakout(voiceText);
        SpeackOutButton(qusImgSound, voiceText);

        Toast.makeText(this, modelFamilies.size() + "", Toast.LENGTH_LONG).show();

        String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
        qusImage21.setImageURI(Uri.parse(imgPath1));
        qusImage21.setTag(modelFamilies.get(COUNTER).name);


        if (a + 1 >= modelFamilies.size()) {
            String imgPath2 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(0).image + ".jpg";
            qusImage22.setImageURI(Uri.parse(imgPath2));
            qusImage22.setTag(modelFamilies.get(0).name);
        } else {
            String imgPath2 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a + 1).image + ".jpg";
            qusImage22.setImageURI(Uri.parse(imgPath2));
            qusImage22.setTag(modelFamilies.get(a + 1).name);
        }


        qusImage21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage21.getTag().toString().equals(modelFamilies.get(a).name)) {
                    Toast.makeText(v.getContext(), "Write Answer", Toast.LENGTH_LONG).show();
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
                    Toast.makeText(v.getContext(), "Wrong Answer", Toast.LENGTH_LONG).show();
                    showDialogFail(v.getContext(), "Wrong Answer");
                }
            }
        });

        qusImage22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage22.getTag().toString().equals(modelFamilies.get(a).name)) {
                    Toast.makeText(v.getContext(), "Write Answer", Toast.LENGTH_LONG).show();
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
                    Toast.makeText(v.getContext(), "Wrong Answer", Toast.LENGTH_LONG).show();
                    showDialogFail(v.getContext(), "Wrong Answer");
                }
            }
        });

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

    public void showDialogSuccess(final Context context, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle("Congratulations")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        COUNTER++;

                        if (COUNTER >= modelFamilies.size()) {
                            COUNTER = 0;
                            dialog.dismiss();
                            showDialogComplete(context, "You have completed the game");
                        } else {
                            dialog.dismiss();
                            actionEvent();
                        }
                    }
                })
                .setIcon(android.R.drawable.btn_star_big_on)
                .show();
    }


    public void showDialogFail(Context context, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle("Sorry")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_notification_clear_all)
                .show();
    }


    public void showDialogComplete(Context context, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle("Complete")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setIcon(android.R.drawable.star_on)
                .show();
    }

    public int getRandomNumber(ArrayList<ModelFamily> list) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * list.size();
        int randomInt = (int) randomDouble;
        /*if (randomInt == i) {
            getRandomNumber(list, i);
        } else {
            return randomInt;
        }*/
        return randomInt;
    }

}

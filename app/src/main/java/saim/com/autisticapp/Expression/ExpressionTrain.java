package saim.com.autisticapp.Expression;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelperEmoji;
import saim.com.autisticapp.Util.DBHelperRashed;

public class ExpressionTrain extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0, a;

    TextView txtQuestion;
    ImageView imgGameEye0, imgGameEye1, imgGameEye2, imgGameEye3, imgGameEye4,qusImgSound;
    DBHelperEmoji dbHelper;

    ArrayList<ModelFamily> modelFamilies = new ArrayList<>();
    ArrayList<Integer> aCounter = new ArrayList<>();
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_expression_train);

        init();
    }

    private void init() {

        dbHelper = new DBHelperEmoji(this);
        modelFamilies = dbHelper.getAllFamilyMembers();

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        qusImgSound = (ImageView) findViewById(R.id.qusImgSound);

        imgGameEye0 = (ImageView) findViewById(R.id.imgGameEye0);
        imgGameEye1 = (ImageView) findViewById(R.id.imgGameEye1);
        imgGameEye2 = (ImageView) findViewById(R.id.imgGameEye2);
        imgGameEye3 = (ImageView) findViewById(R.id.imgGameEye3);
        imgGameEye4 = (ImageView) findViewById(R.id.imgGameEye4);

        actionEvent();
    }

    private void actionEvent() {

        a = getRandomNumber(modelFamilies);


        String voiceText = "What kind of expression is this?";// + modelFamilies.get(a).name;
        txtQuestion.setText(voiceText);
        Speakout(voiceText);
        SpeackOutButton(qusImgSound, voiceText);

        //int imgResource = getResources().getIdentifier("ic_ammu", "drawable", getPackageName());
        //imgGameRoateimg.setImageResource(imgResource);

        int imgResource = getResources().getIdentifier(modelFamilies.get(a).relation, "drawable", getPackageName());
        imgGameEye0.setImageResource(imgResource);
        imgGameEye0.setTag(modelFamilies.get(a).name);

        //Toast.makeText(getApplicationContext(), a + " ", Toast.LENGTH_LONG).show();

        if (a + 1 >= modelFamilies.size()) {
            int imgResource1 = getResources().getIdentifier(modelFamilies.get(0).image, "drawable", getPackageName());
            imgGameEye1.setImageResource(imgResource1);
            imgGameEye1.setTag(modelFamilies.get(0).name);
        } else {
            int imgResource2 = getResources().getIdentifier(modelFamilies.get(a+1).image, "drawable", getPackageName());
            imgGameEye1.setImageResource(imgResource2);
            imgGameEye1.setTag(modelFamilies.get(a+1).name);
        }

        int imgResource3 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
        imgGameEye2.setImageResource(imgResource3);
        imgGameEye2.setTag(modelFamilies.get(a).name);

        imgGameEye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgGameEye1.getTag().toString().equals(modelFamilies.get(a).name)) {
                    Toast.makeText(v.getContext(), "Write Answer", Toast.LENGTH_LONG).show();
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
                    Toast.makeText(v.getContext(), "Wrong Answer", Toast.LENGTH_LONG).show();
                    showDialogFail(v.getContext(), "Wrong Answer");
                }
            }
        });

        imgGameEye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgGameEye2.getTag().toString().equals(modelFamilies.get(a).name)) {
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
                        a++;
                        COUNTER++;
                        aCounter.add(a);

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
        /*if (aCounter.contains(randomInt)) {
            getRandomNumber(list);
        } else {
            return randomInt;
        }*/
        return randomInt;
    }




}

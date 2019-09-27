package saim.com.autisticapp.Game;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;
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

public class GameRotate extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0, a;

    TextView txtQuestion;
    ImageView imgGameRoateimg, qusImgSound;
    ImageButton imgRotateLeft, imgRotateOk, imgRotateRight;
    DBHelper dbHelper;

    ArrayList<ModelFamily> modelFamilies = new ArrayList<>();
    ArrayList<Integer> aCounter = new ArrayList<>();
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_rotate);

        init();
    }

    private void init() {


        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");
        dbHelper = new DBHelper(this);
        modelFamilies = dbHelper.getAllFamilyMembers();

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        qusImgSound = (ImageView) findViewById(R.id.qusImgSound);

        imgGameRoateimg = (ImageView) findViewById(R.id.imgGameRoateimg);

        imgRotateLeft = (ImageButton) findViewById(R.id.imgRotateLeft);
        imgRotateOk = (ImageButton) findViewById(R.id.imgRotateOk);
        imgRotateRight = (ImageButton) findViewById(R.id.imgRotateRight);


        actionEvent();
    }

    private void actionEvent() {

        a = getRandomNumber(modelFamilies);


        String voiceText = "Fix below image of " + modelFamilies.get(a).name;
        txtQuestion.setText(voiceText);
        Speakout(voiceText);
        SpeackOutButton(qusImgSound, voiceText);

        //int imgResource = getResources().getIdentifier("ic_ammu", "drawable", getPackageName());
        //imgGameRoateimg.setImageResource(imgResource);

        String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
        imgGameRoateimg.setImageURI(Uri.parse(imgPath1));
        imgGameRoateimg.setRotation(135);


        imgRotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgGameRoateimg.setRotation(imgGameRoateimg.getRotation() + 45);
            }
        });

        imgRotateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgGameRoateimg.setRotation(imgGameRoateimg.getRotation() - 45);
            }
        });


        imgRotateOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imgGameRoateimg.getRotation()+"";
                if (imgGameRoateimg.getRotation() == 0 || (imgGameRoateimg.getRotation() % 360) == 0) {
                    Speakout("You have done it.");
                    showDialogSuccess(v.getContext(), "You have done it.");
                } else {
                    Speakout("Sorry fix it properly.");
                    showDialogFail(v.getContext(), "Sorry fix it properly.");
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


package saim.com.autisticapp.Game2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelperRashed;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class GameFind2 extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0;

    TextView txtQuestion, txtTitle;
    ImageView qusImage1, qusImage2, qusImgSound;
    DBHelperRashed dbHelper;

    ArrayList<ModelFamily> modelFamilies = new ArrayList<>();
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_find2);
        init();
    }

    private void init() {

        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");
        dbHelper = new DBHelperRashed(this);
        modelFamilies = dbHelper.getAllFamilyMembersNew();

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        qusImage1 = (ImageView) findViewById(R.id.qusImage1);
        qusImage2 = (ImageView) findViewById(R.id.qusImage2);
        qusImgSound = (ImageView) findViewById(R.id.qusImgSound);

        txtTitle = findViewById(R.id.txtTitle);

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.games_bn_3);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.games_en_3);
        }

        actionEvent();
    }

    private void actionEvent() {

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            String questionText = modelFamilies.get(COUNTER).name + " " + getResources().getString(R.string.games_find_bn);
            txtQuestion.setText(questionText);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            String questionText = getResources().getString(R.string.games_find_en) + " " + modelFamilies.get(COUNTER).name;
            txtQuestion.setText(questionText);
        }

        PlaySound();

        int path1 = getResources().getIdentifier(modelFamilies.get(COUNTER).image, "drawable", getPackageName());
        qusImage1.setImageResource(path1);
        qusImage1.setTag(modelFamilies.get(COUNTER).name);


        if (COUNTER + 1 >= modelFamilies.size()) {
            int path2 = getResources().getIdentifier(modelFamilies.get(0).image, "drawable", getPackageName());
            qusImage2.setImageResource(path2);
            qusImage2.setTag(modelFamilies.get(0).name);
        } else {
            int path2 = getResources().getIdentifier(modelFamilies.get(COUNTER + 1).image, "drawable", getPackageName());
            qusImage2.setImageResource(path2);
            qusImage2.setTag(modelFamilies.get(COUNTER + 1).name);
        }


        qusImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage1.getTag().toString().equals(modelFamilies.get(COUNTER).name)) {
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
                    showDialogFail(v.getContext(), "Wrong Answer");
                }
            }
        });

        qusImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage2.getTag().toString().equals(modelFamilies.get(COUNTER).name)) {
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
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

    public void PlaySound() {


        MediaPlayer mediaPlayer = new MediaPlayer();
        try {

            AssetFileDescriptor descriptor = getAssets().openFd("a_who_is_en.mpeg");

            if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                descriptor = getAssets().openFd("a_who_is_bd.mpeg");
            } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                descriptor = getAssets().openFd("a_who_is_en.mpeg");
            }

            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();

                MediaPlayer mediaPlayerNew = new MediaPlayer();
                try {
                    AssetFileDescriptor descriptor = getAssets().openFd("a_who_is_en_2.mpeg");
                    descriptor = getAssets().openFd(modelFamilies.get(COUNTER).getSound());
                    mediaPlayerNew.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                    descriptor.close();

                    mediaPlayerNew.prepare();
                    mediaPlayerNew.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayerNew.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                    }
                });
            }
        });



    }

    public void showDialogSuccess(final Context context, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle("Congratulations")
                .setMessage(message)
                .setCancelable(false)
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
                .setCancelable(false)
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
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setIcon(android.R.drawable.star_on)
                .show();
    }
}

package saim.com.autisticapp.Game;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelper;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class GameMemory extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0, a;

    TextView txtQuestion, txtTitle;
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
        modelFamilies = dbHelper.getAllFamilyMembersNew();

        txtQuestion = (TextView) findViewById(R.id.txtQuestion11);
        qusImage11 = (ImageView) findViewById(R.id.qusImage11);
        qusImage12 = (ImageView) findViewById(R.id.qusImage12);
        qusImage13 = (ImageView) findViewById(R.id.qusImage13);
        qusImage14 = (ImageView) findViewById(R.id.qusImage14);
        qusImgSound = (ImageView) findViewById(R.id.qusImgSound);

        txtTitle = findViewById(R.id.txtTitle);

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.games_bn_4);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.games_en_4);
        }

        actionEvent();
    }

    private void actionEvent() {
        a = getRandomNumber(modelFamilies);

        String voiceText = "Where is  " + modelFamilies.get(a).name;
        txtQuestion.setText(voiceText);
        PlaySound();


        if ( a == 1) {
            String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            qusImage11.setImageURI(Uri.parse(imgPath1));
            qusImage11.setTag(modelFamilies.get(a).name);
            qusImage12.setTag("_HELLO_");
            qusImage13.setTag("_HELLO_");
            qusImage14.setTag("_HELLO_");
        } else if ( a == 2) {
            String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            qusImage12.setImageURI(Uri.parse(imgPath1));
            qusImage12.setTag(modelFamilies.get(a).name);
            qusImage11.setTag("_HELLO_");
            qusImage13.setTag("_HELLO_");
            qusImage14.setTag("_HELLO_");
        } else if ( a == 3) {
            String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            qusImage13.setImageURI(Uri.parse(imgPath1));
            qusImage13.setTag(modelFamilies.get(a).name);
            qusImage12.setTag("_HELLO_");
            qusImage14.setTag("_HELLO_");
            qusImage11.setTag("_HELLO_");
        } else if ( a == 4) {
            String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            qusImage14.setImageURI(Uri.parse(imgPath1));
            qusImage14.setTag(modelFamilies.get(a).name);
            qusImage12.setTag("_HELLO_");
            qusImage13.setTag("_HELLO_");
            qusImage11.setTag("_HELLO_");
        } else {
            String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            qusImage12.setImageURI(Uri.parse(imgPath1));
            qusImage12.setTag(modelFamilies.get(a).name);
            qusImage13.setTag("_HELLO_");
            qusImage14.setTag("_HELLO_");
            qusImage11.setTag("_HELLO_");
        }



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                qusImage11.setImageResource(R.drawable.ic_angry);
                qusImage12.setImageResource(R.drawable.ic_angry);
                qusImage13.setImageResource(R.drawable.ic_angry);
                qusImage14.setImageResource(R.drawable.ic_angry);

            }
        }, 2000);


        qusImage11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage11.getTag().toString().equals(modelFamilies.get(a).name)) {
                    //Speakout("Right Answer");
                    String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
                    qusImage11.setImageURI(Uri.parse(imgPath1));
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
                    //Speakout("Wrong Answer");
                    showDialogFail(v.getContext(), "Wrong Answer");
                }
            }
        });

        qusImage12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage12.getTag().toString().equals(modelFamilies.get(a).name)) {
                    //Speakout("Right Answer");
                    String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
                    qusImage12.setImageURI(Uri.parse(imgPath1));
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
                    //Speakout("Wrong Answer");
                    showDialogFail(v.getContext(), "Wrong Answer");
                }
            }
        });

        qusImage13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage13.getTag().toString().equals(modelFamilies.get(a).name)) {
                    //Speakout("Right Answer");
                    String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
                    qusImage13.setImageURI(Uri.parse(imgPath1));
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
                    Speakout("Wrong Answer");
                    showDialogFail(v.getContext(), "Wrong Answer");
                }
            }
        });

        qusImage14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage14.getTag().toString().equals(modelFamilies.get(a).name)) {
                    //Speakout("Right Answer");
                    String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
                    qusImage14.setImageURI(Uri.parse(imgPath1));
                    showDialogSuccess(v.getContext(), "Right Answer!");
                } else {
                    //Speakout("Wrong Answer");
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


        MediaPlayer mediaPlayerNew = new MediaPlayer();
        try {
            Log.d("SAIM_LOG_FAMILY", getExternalCacheDir() + File.separator + modelFamilies.get(a).getSound());
            mediaPlayerNew.setDataSource(getExternalCacheDir() + File.separator + modelFamilies.get(a).getSound());
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

                MediaPlayer mediaPlayer = new MediaPlayer();
                try {

                    AssetFileDescriptor descriptor = getAssets().openFd("a_where_is_en.mpeg");

                    if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                        descriptor = getAssets().openFd("a_where_is_bn.mpeg");
                    } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                        descriptor = getAssets().openFd("a_where_is_en.mpeg");
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
                    }
                });
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

                        qusImage11.setImageResource(R.drawable.ic_angry);
                        qusImage12.setImageResource(R.drawable.ic_angry);
                        qusImage13.setImageResource(R.drawable.ic_angry);
                        qusImage14.setImageResource(R.drawable.ic_angry);

                        if (COUNTER >= modelFamilies.size()) {
                            a = 0;
                            dialog.dismiss();
                            //Speakout("You have completed the game");
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

package saim.com.autisticapp.Game;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelper;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class GameFind extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0;

    TextView txtTitle, txtQuestion;
    ImageView qusImage1, qusImage2, qusImgSound;
    DBHelper dbHelper;

    ArrayList<ModelFamily> modelFamilies = new ArrayList<>();
    private TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_find);

        init();
    }

    private void init() {

        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");
        dbHelper = new DBHelper(this);
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

        qusImgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySound();
            }
        });

        actionEvent();
    }

    private void actionEvent() {
        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtQuestion.setText(modelFamilies.get(COUNTER).name + " " + getResources().getString(R.string.games_find_bn));
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtQuestion.setText(getResources().getString(R.string.games_find_en) + " " + modelFamilies.get(COUNTER).name);
        }
        PlaySound();

        String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(COUNTER).image + ".jpg";
        qusImage1.setImageURI(Uri.parse(imgPath1));
        qusImage1.setTag(modelFamilies.get(COUNTER).name);


        if (COUNTER + 1 >= modelFamilies.size()) {
            String imgPath2 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(0).image + ".jpg";
            qusImage2.setImageURI(Uri.parse(imgPath2));
            qusImage2.setTag(modelFamilies.get(0).name);
        } else {
            String imgPath2 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(COUNTER + 1).image + ".jpg";
            qusImage2.setImageURI(Uri.parse(imgPath2));
            qusImage2.setTag(modelFamilies.get(COUNTER + 1).name);
        }


        qusImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage1.getTag().toString().equals(modelFamilies.get(COUNTER).name)) {
                    //Toast.makeText(v.getContext(), "Write Answer", Toast.LENGTH_LONG).show();
                    String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(COUNTER).image + ".jpg";
                    qusImage1.setImageURI(Uri.parse(imgPath1));
                    playRightAnswerSound();
                } else {
                    playWrongAnswerSound();
                }
            }
        });

        qusImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage2.getTag().toString().equals(modelFamilies.get(COUNTER).name)) {
                    String imgPath2 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(COUNTER).image + ".jpg";
                    qusImage2.setImageURI(Uri.parse(imgPath2));
                    playRightAnswerSound();
                } else {
                    playWrongAnswerSound();
                }
            }
        });
    }

    public void playRightAnswerSound() {
        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            actionEventSound(getApplicationContext(), "right_ans_bn.mp3");
            showDialogSuccess(getApplicationContext(), getResources().getString(R.string.ans_comments_bn),getResources().getString(R.string.ans_right_bn));
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            actionEventSound(getApplicationContext(), "right_ans_en.mp3");
            showDialogSuccess(getApplicationContext(), getResources().getString(R.string.ans_comments_en),getResources().getString(R.string.ans_right_en));
        }
    }
    public void playWrongAnswerSound() {
        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            actionEventSound(getApplicationContext(), "wrong_ans_bn.mp3");
            showDialogFail(getApplicationContext(), getResources().getString(R.string.ans_comments_bn),getResources().getString(R.string.ans_wrong_bn));
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            actionEventSound(getApplicationContext(), "wrong_ans_en.mp3");
            showDialogFail(getApplicationContext(), getResources().getString(R.string.ans_comments_en),getResources().getString(R.string.ans_wrong_en));
        }
    }

    private void actionEventSound(Context context,  final String Sound_s ) {

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor = context.getAssets().openFd(Sound_s);
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
                    Log.d("SAIM_LOG_FAMILY", getExternalCacheDir() + File.separator + modelFamilies.get(COUNTER).getSound());
                    mediaPlayerNew.setDataSource(getExternalCacheDir() + File.separator + modelFamilies.get(COUNTER).getSound());
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

    public void showDialogSuccess(final Context context, final String title, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        COUNTER++;

                        if (COUNTER >= modelFamilies.size()) {
                            COUNTER = 0;
                            dialog.dismiss();
                            if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                                showDialogComplete(context, title, getResources().getString(R.string.game_complete_bn));
                            } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                                showDialogComplete(context, title, getResources().getString(R.string.game_complete_en));
                            }
                        } else {
                            dialog.dismiss();
                            actionEvent();
                        }
                    }
                })
                .setIcon(android.R.drawable.btn_star_big_on)
                .show();
    }


    public void showDialogFail(Context context, String title, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle(title)
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


    public void showDialogComplete(Context context, String title, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle(title)
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

    public void UpdateQuestionAndView() {

    }
}

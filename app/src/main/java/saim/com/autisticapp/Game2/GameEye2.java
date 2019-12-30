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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelperRashed;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class GameEye2 extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0, a;

    TextView txtQuestion, txtTitle;
    ImageView imgGameEye0, imgGameEye1, imgGameEye2, qusImgSound;
    DBHelperRashed dbHelper;

    ArrayList<ModelFamily> modelFamilies = new ArrayList<>();
    ArrayList<Integer> aCounter = new ArrayList<>();
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_eye2);

        init();
    }

    private void init() {


        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");
        dbHelper = new DBHelperRashed(this);
        modelFamilies = dbHelper.getAllFamilyMembersNew();

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        qusImgSound = (ImageView) findViewById(R.id.qusImgSound);

        imgGameEye0 = (ImageView) findViewById(R.id.imgGameEye0);
        imgGameEye1 = (ImageView) findViewById(R.id.imgGameEye1);
        imgGameEye2 = (ImageView) findViewById(R.id.imgGameEye2);

        txtTitle = findViewById(R.id.txtTitle);

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.games_bn_6);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.games_en_6);
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

        a = getRandomNumber(modelFamilies);


        String voiceText = "Whose eye is this?";
        
        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtQuestion.setText(R.string.games_eye_bn);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtQuestion.setText(R.string.games_eye_en);
        }

        PlaySound();

        int imgResource = getResources().getIdentifier(modelFamilies.get(a).relation, "drawable", getPackageName());
        imgGameEye0.setImageResource(imgResource);
        imgGameEye0.setTag(modelFamilies.get(a).name);

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
                    playRightAnswerSound();
                } else {
                    playWrongAnswerSound();
                }
            }
        });

        imgGameEye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgGameEye2.getTag().toString().equals(modelFamilies.get(a).name)) {
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

    public void PlaySound() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {

            AssetFileDescriptor descriptor = getAssets().openFd("a_eye_en.mpeg");

            if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                descriptor = getAssets().openFd("a_eye_bn.mpeg");
            } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                descriptor = getAssets().openFd("a_eye_en.mpeg");
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


    public void showDialogSuccess(final Context context, final String title, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        a++;
                        COUNTER++;
                        aCounter.add(a);

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

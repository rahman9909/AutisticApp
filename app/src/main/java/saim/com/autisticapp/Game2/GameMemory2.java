package saim.com.autisticapp.Game2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelperRashed;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class GameMemory2 extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0, a;

    TextView txtQuestion, txtTitle;
    ImageView qusImage11, qusImage12, qusImage13, qusImage14, qusImgSound;
    DBHelperRashed dbHelper;

    ArrayList<ModelFamily> modelFamilies = new ArrayList<>();
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_memory2);
        init();
    }

    private void init() {

        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");
        dbHelper = new DBHelperRashed(this);
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

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtQuestion.setText(modelFamilies.get(a).name + " " + getResources().getString(R.string.games_memory_bn));
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtQuestion.setText(getResources().getString(R.string.games_memory_en) + " " + modelFamilies.get(a).name);
        }

        PlaySound();

        if (a == 1) {
            //String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            //qusImage11.setImageURI(Uri.parse(imgPath1));
            int path1 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
            qusImage11.setImageResource(path1);
            qusImage11.setTag(modelFamilies.get(a).name);
            qusImage12.setTag("_HELLO_");
            qusImage13.setTag("_HELLO_");
            qusImage14.setTag("_HELLO_");
        } else if (a == 2) {
            //String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            //qusImage12.setImageURI(Uri.parse(imgPath1));
            int path2 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
            qusImage12.setImageResource(path2);
            qusImage12.setTag(modelFamilies.get(a).name);
            qusImage11.setTag("_HELLO_");
            qusImage13.setTag("_HELLO_");
            qusImage14.setTag("_HELLO_");
        } else if (a == 3) {
            //String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            //qusImage13.setImageURI(Uri.parse(imgPath1));
            int path3 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
            qusImage13.setImageResource(path3);
            qusImage13.setTag(modelFamilies.get(a).name);
            qusImage12.setTag("_HELLO_");
            qusImage14.setTag("_HELLO_");
            qusImage11.setTag("_HELLO_");
        } else if (a == 4) {
            //String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            //qusImage14.setImageURI(Uri.parse(imgPath1));
            int path4 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
            qusImage14.setImageResource(path4);
            qusImage14.setTag(modelFamilies.get(a).name);
            qusImage12.setTag("_HELLO_");
            qusImage13.setTag("_HELLO_");
            qusImage11.setTag("_HELLO_");
        } else {
            //String imgPath1 = getExternalCacheDir().getPath() + "/" + modelFamilies.get(a).image + ".jpg";
            //qusImage12.setImageURI(Uri.parse(imgPath1));
            int path2 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
            qusImage12.setImageResource(path2);
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
                    int path2 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
                    qusImage11.setImageResource(path2);
                    playRightAnswerSound();

                } else {
                    playWrongAnswerSound();
                }
            }
        });

        qusImage12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage12.getTag().toString().equals(modelFamilies.get(a).name)) {
                    int path2 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
                    qusImage12.setImageResource(path2);
                    playRightAnswerSound();
                } else {
                    playWrongAnswerSound();
                }
            }
        });

        qusImage13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage13.getTag().toString().equals(modelFamilies.get(a).name)) {
                    int path2 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
                    qusImage13.setImageResource(path2);
                    playRightAnswerSound();
                } else {
                    playWrongAnswerSound();
                }
            }
        });

        qusImage14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qusImage14.getTag().toString().equals(modelFamilies.get(a).name)) {
                    int path2 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
                    qusImage14.setImageResource(path2);
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

            AssetFileDescriptor descriptor = getAssets().openFd("a_who_is_en_2.mpeg");

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

                MediaPlayer mediaPlayerNew = new MediaPlayer();
                try {
                    AssetFileDescriptor descriptor = getAssets().openFd("a_who_is_en_2.mpeg");
                    descriptor = getAssets().openFd(modelFamilies.get(a).getSound());
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
                .setCancelable(false)
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

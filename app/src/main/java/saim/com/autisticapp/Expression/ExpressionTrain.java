package saim.com.autisticapp.Expression;

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
import saim.com.autisticapp.Util.DBHelperEmoji;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class ExpressionTrain extends AppCompatActivity {

    int GAME_TYPE, COUNTER = 0, a;

    TextView txtTitle, txtQuestion, txtExpression1, txtExpression2;
    ImageView imgGameEye0, imgGameEye1, imgGameEye2, qusImgSound;
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

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtExpression1 = (TextView) findViewById(R.id.txtExpression1);
        txtExpression2 = (TextView) findViewById(R.id.txtExpression2);

        imgGameEye0 = (ImageView) findViewById(R.id.imgGameEye0);
        imgGameEye1 = (ImageView) findViewById(R.id.imgGameEye1);
        imgGameEye2 = (ImageView) findViewById(R.id.imgGameEye2);
        qusImgSound = (ImageView) findViewById(R.id.qusImgSound);


        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.train_expression_bn_1);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.train_expression_en_1);
        }

        qusImgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                    actionEventSound(getApplicationContext(), "qus_expression_bn.mpeg");

                } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                    actionEventSound(getApplicationContext(), "qus_expression_en.mpeg");
                }
            }
        });

        actionEvent();
    }

    private void actionEvent() {

        a = getRandomNumber(modelFamilies);
        String voiceText = "";
        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            voiceText = getResources().getString(R.string.train_expression_bn_2);
            actionEventSound(getApplicationContext(), "qus_expression_bn.mpeg");

        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            voiceText = getResources().getString(R.string.train_expression_en_2);
            actionEventSound(getApplicationContext(), "qus_expression_en.mpeg");
        }

        txtQuestion.setText(voiceText);

        int imgResource = getResources().getIdentifier(modelFamilies.get(a).relation, "drawable", getPackageName());
        imgGameEye0.setImageResource(imgResource);
        imgGameEye0.setTag(modelFamilies.get(a).name);

        //Toast.makeText(getApplicationContext(), a + " ", Toast.LENGTH_LONG).show();

        if (a + 1 >= modelFamilies.size()) {
            int imgResource1 = getResources().getIdentifier(modelFamilies.get(0).image, "drawable", getPackageName());
            imgGameEye1.setImageResource(imgResource1);
            imgGameEye1.setTag(modelFamilies.get(0).name);


            if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                if (modelFamilies.get(0).name.equals("Neutral")) {
                    txtExpression1.setText(R.string.expres_neutral);
                } else if (modelFamilies.get(0).name.equals("Happy")) {
                    txtExpression1.setText(R.string.expres_happy);
                } else if (modelFamilies.get(0).name.equals("Sad")) {
                    txtExpression1.setText(R.string.expres_sad);
                } else if (modelFamilies.get(0).name.equals("Surprised")) {
                    txtExpression1.setText(R.string.expres_surprised);
                } else if (modelFamilies.get(0).name.equals("Angry")) {
                    txtExpression1.setText(R.string.expres_angry);
                } else if (modelFamilies.get(0).name.equals("Disgusted")) {
                    txtExpression1.setText(R.string.expres_disgusted);
                } else if (modelFamilies.get(0).name.equals("Fear")) {
                    txtExpression1.setText(R.string.expres_fear);
                }
            } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                txtExpression1.setText(modelFamilies.get(0).name);
            }

        } else {
            int imgResource2 = getResources().getIdentifier(modelFamilies.get(a+1).image, "drawable", getPackageName());
            imgGameEye1.setImageResource(imgResource2);
            imgGameEye1.setTag(modelFamilies.get(a+1).name);

            //txtExpression1.setText(modelFamilies.get(a+1).name);
            if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                if (modelFamilies.get(a + 1).name.equals("Neutral")) {
                    txtExpression1.setText(R.string.expres_neutral);
                } else if (modelFamilies.get(a + 1).name.equals("Happy")) {
                    txtExpression1.setText(R.string.expres_happy);
                } else if (modelFamilies.get(a + 1).name.equals("Sad")) {
                    txtExpression1.setText(R.string.expres_sad);
                } else if (modelFamilies.get(a + 1).name.equals("Surprised")) {
                    txtExpression1.setText(R.string.expres_surprised);
                } else if (modelFamilies.get(a + 1).name.equals("Angry")) {
                    txtExpression1.setText(R.string.expres_angry);
                } else if (modelFamilies.get(a + 1).name.equals("Disgusted")) {
                    txtExpression1.setText(R.string.expres_disgusted);
                } else if (modelFamilies.get(a + 1).name.equals("Fear")) {
                    txtExpression1.setText(R.string.expres_fear);
                }
            } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                txtExpression1.setText(modelFamilies.get(a + 1).name);
            }
        }

        int imgResource3 = getResources().getIdentifier(modelFamilies.get(a).image, "drawable", getPackageName());
        imgGameEye2.setImageResource(imgResource3);
        imgGameEye2.setTag(modelFamilies.get(a).name);

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            if (modelFamilies.get(a).name.equals("Neutral")) {
                txtExpression2.setText(R.string.expres_neutral);
            } else if (modelFamilies.get(a).name.equals("Happy")) {
                txtExpression2.setText(R.string.expres_happy);
            } else if (modelFamilies.get(a).name.equals("Sad")) {
                txtExpression2.setText(R.string.expres_sad);
            } else if (modelFamilies.get(a).name.equals("Surprised")) {
                txtExpression2.setText(R.string.expres_surprised);
            } else if (modelFamilies.get(a).name.equals("Angry")) {
                txtExpression2.setText(R.string.expres_angry);
            } else if (modelFamilies.get(a).name.equals("Disgusted")) {
                txtExpression2.setText(R.string.expres_disgusted);
            } else if (modelFamilies.get(a).name.equals("Fear")) {
                txtExpression2.setText(R.string.expres_fear);
            }
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtExpression2.setText(modelFamilies.get(a).name);
        }

        imgGameEye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgGameEye1.getTag().toString().equals(modelFamilies.get(a).name)) {
                    if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                        actionEventSound(getApplicationContext(), "right_ans_bn.mp3");
                        showDialogSuccess(v.getContext(), getResources().getString(R.string.ans_comments_bn),getResources().getString(R.string.ans_right_bn));
                    } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                        actionEventSound(getApplicationContext(), "right_ans_en.mp3");
                        showDialogSuccess(v.getContext(), getResources().getString(R.string.ans_comments_en),getResources().getString(R.string.ans_right_en));
                    }
                } else {
                    if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                        actionEventSound(getApplicationContext(), "wrong_ans_bn.mp3");
                        showDialogSuccess(v.getContext(), getResources().getString(R.string.ans_comments_bn),getResources().getString(R.string.ans_wrong_bn));
                    } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                        actionEventSound(getApplicationContext(), "wrong_ans_en.mp3");
                        showDialogSuccess(v.getContext(), getResources().getString(R.string.ans_comments_en),getResources().getString(R.string.ans_wrong_en));
                    }
                }
            }
        });

        imgGameEye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgGameEye2.getTag().toString().equals(modelFamilies.get(a).name)) {
                    if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                        actionEventSound(getApplicationContext(), "right_ans_bn.mp3");
                        showDialogSuccess(v.getContext(), getResources().getString(R.string.ans_comments_bn),getResources().getString(R.string.ans_right_bn));
                    } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                        actionEventSound(getApplicationContext(), "right_ans_en.mp3");
                        showDialogSuccess(v.getContext(), getResources().getString(R.string.ans_comments_en),getResources().getString(R.string.ans_right_en));
                    }
                } else {
                    if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
                        actionEventSound(getApplicationContext(), "wrong_ans_bn.mp3");
                        showDialogSuccess(v.getContext(), getResources().getString(R.string.ans_comments_bn),getResources().getString(R.string.ans_wrong_bn));
                    } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
                        actionEventSound(getApplicationContext(), "wrong_ans_en.mp3");
                        showDialogSuccess(v.getContext(), getResources().getString(R.string.ans_comments_en),getResources().getString(R.string.ans_wrong_en));
                    }
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

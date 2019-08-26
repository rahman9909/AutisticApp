package saim.com.autisticapp;

import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import saim.com.autisticapp.Util.SharedPrefDatabase;

public class ActivityParentsAlbum extends AppCompatActivity {

    LinearLayout layoutFather, layoutMother, layoutGFather, layoutGMother,
            layoutBrother1, layoutBrother2, layoutBrother3,
            layoutSister1, layoutSister2, layoutSister3;

    ImageView imgAlbumFather, imgAlbumFatherSound,
            imgAlbumMother, imgAlbumMotherSound,
            imgAlbumGFather, imgAlbumGFatherSound,
            imgAlbumGMother, imgAlbumGMotherSound,
            imgAlbumBrother1, imgAlbumBrother1Sound,
            imgAlbumBrother2, imgAlbumBrother2Sound,
            imgAlbumBrother3, imgAlbumBrother3Sound,
            imgAlbumSister1, imgAlbumSister1Sound,
            imgAlbumSister2, imgAlbumSister2Sound,
            imgAlbumSister3, imgAlbumSister3Sound;
    TextView txtAlbumFather, txtAlbumFatherR,
            txtAlbumMother, txtAlbumMotherR,
            txtAlbumGFather, txtAlbumGFatherR,
            txtAlbumGMother, txtAlbumGMotherR,
            txtAlbumBrother1, txtAlbumBrother1R,
            txtAlbumBrother2, txtAlbumBrother2R,
            txtAlbumBrother3, txtAlbumBrother3R,
            txtAlbumSister1, txtAlbumSister1R,
            txtAlbumSister2, txtAlbumSister2R,
            txtAlbumSister3, txtAlbumSister3R;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_parents_album);


        init();
    }

    private void init() {

        layoutFather = (LinearLayout) findViewById(R.id.layoutFather);
        layoutMother = (LinearLayout) findViewById(R.id.layoutMother);
        layoutGFather = (LinearLayout) findViewById(R.id.layoutGFather);
        layoutGMother = (LinearLayout) findViewById(R.id.layoutGMother);
        layoutBrother1 = (LinearLayout) findViewById(R.id.layoutBrother1);
        layoutBrother2 = (LinearLayout) findViewById(R.id.layoutBrother2);
        layoutBrother3 = (LinearLayout) findViewById(R.id.layoutBrother3);
        layoutSister1 = (LinearLayout) findViewById(R.id.layoutSister1);
        layoutSister2 = (LinearLayout) findViewById(R.id.layoutSister2);
        layoutSister3 = (LinearLayout) findViewById(R.id.layoutSister3);


        imgAlbumFather = (ImageView) findViewById(R.id.imgAlbumFather);
        imgAlbumFatherSound = (ImageView) findViewById(R.id.imgAlbumFatherSound);
        txtAlbumFather = (TextView) findViewById(R.id.txtAlbumFather);
        txtAlbumFatherR = (TextView) findViewById(R.id.txtAlbumFatherR);

        imgAlbumMother = (ImageView) findViewById(R.id.imgAlbumMother);
        imgAlbumMotherSound = (ImageView) findViewById(R.id.imgAlbumMotherSound);
        txtAlbumMother = (TextView) findViewById(R.id.txtAlbumMother);
        txtAlbumMotherR = (TextView) findViewById(R.id.txtAlbumMotherR);

        imgAlbumGFather = (ImageView) findViewById(R.id.imgAlbumGFather);
        imgAlbumGFatherSound = (ImageView) findViewById(R.id.imgAlbumGFatherSound);
        txtAlbumGFather = (TextView) findViewById(R.id.txtAlbumGFather);
        txtAlbumGFatherR = (TextView) findViewById(R.id.txtAlbumGFatherR);

        imgAlbumGMother = (ImageView) findViewById(R.id.imgAlbumGMother);
        imgAlbumGMotherSound = (ImageView) findViewById(R.id.imgAlbumGMotherSound);
        txtAlbumGMother = (TextView) findViewById(R.id.txtAlbumGMother);
        txtAlbumGMotherR = (TextView) findViewById(R.id.txtAlbumGMotherR);

        imgAlbumBrother1 = (ImageView) findViewById(R.id.imgAlbumBrother1);
        imgAlbumBrother1Sound = (ImageView) findViewById(R.id.imgAlbumBrother1Sound);
        txtAlbumBrother1 = (TextView) findViewById(R.id.txtAlbumBrother1);
        txtAlbumBrother1R = (TextView) findViewById(R.id.txtAlbumBrother1R);

        imgAlbumBrother2 = (ImageView) findViewById(R.id.imgAlbumBrother2);
        imgAlbumBrother2Sound = (ImageView) findViewById(R.id.imgAlbumBrother2Sound);
        txtAlbumBrother2 = (TextView) findViewById(R.id.txtAlbumBrother2);
        txtAlbumBrother2R = (TextView) findViewById(R.id.txtAlbumBrother2R);

        imgAlbumBrother3 = (ImageView) findViewById(R.id.imgAlbumBrother3);
        imgAlbumBrother3Sound = (ImageView) findViewById(R.id.imgAlbumBrother3Sound);
        txtAlbumBrother3 = (TextView) findViewById(R.id.txtAlbumBrother3);
        txtAlbumBrother3R = (TextView) findViewById(R.id.txtAlbumBrother3R);

        imgAlbumSister1 = (ImageView) findViewById(R.id.imgAlbumSister1);
        imgAlbumSister1Sound = (ImageView) findViewById(R.id.imgAlbumSister1Sound);
        txtAlbumSister1 = (TextView) findViewById(R.id.txtAlbumSister1);
        txtAlbumSister1R = (TextView) findViewById(R.id.txtAlbumSister1R);

        imgAlbumSister2 = (ImageView) findViewById(R.id.imgAlbumSister2);
        imgAlbumSister2Sound = (ImageView) findViewById(R.id.imgAlbumSister2Sound);
        txtAlbumSister2 = (TextView) findViewById(R.id.txtAlbumSister2);
        txtAlbumSister2R = (TextView) findViewById(R.id.txtAlbumSister2R);

        imgAlbumSister3 = (ImageView) findViewById(R.id.imgAlbumSister3);
        imgAlbumSister3Sound = (ImageView) findViewById(R.id.imgAlbumSister3Sound);
        txtAlbumSister3 = (TextView) findViewById(R.id.txtAlbumSister3);
        txtAlbumSister3R = (TextView) findViewById(R.id.txtAlbumSister3R);


        actionEvent();

    }

    private void actionEvent() {

        if (new SharedPrefDatabase(getApplicationContext()).RetriveFather() != null) {
            layoutFather.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/FATHER.jpg";
            imgAlbumFather.setImageURI(Uri.parse(path));
            txtAlbumFather.setText(new SharedPrefDatabase(getApplicationContext()).RetriveFather());
            txtAlbumFatherR.setText("FATHER");
        } else {
            layoutFather.setVisibility(View.GONE);
        }


        if (new SharedPrefDatabase(getApplicationContext()).RetriveMother() != null) {
            layoutMother.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/MOTHER.jpg";
            imgAlbumMother.setImageURI(Uri.parse(path));
            txtAlbumMother.setText(new SharedPrefDatabase(getApplicationContext()).RetriveMother());
            txtAlbumMotherR.setText("MOTHER");
        } else {
            layoutMother.setVisibility(View.GONE);
        }


        if (new SharedPrefDatabase(getApplicationContext()).RetriveGFather() != null) {
            layoutGFather.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/GRAND_FATHER.jpg";
            imgAlbumGFather.setImageURI(Uri.parse(path));
            txtAlbumGFather.setText(new SharedPrefDatabase(getApplicationContext()).RetriveGFather());
            txtAlbumGFatherR.setText("GRAND FATHER");
        } else {
            layoutGFather.setVisibility(View.GONE);
        }

        if (new SharedPrefDatabase(getApplicationContext()).RetriveGMother() != null) {
            layoutGMother.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/GRAND_MOTHER.jpg";
            imgAlbumGMother.setImageURI(Uri.parse(path));
            txtAlbumGMother.setText(new SharedPrefDatabase(getApplicationContext()).RetriveGMother());
            txtAlbumGMotherR.setText("GRAND MOTHER");
        } else {
            layoutGMother.setVisibility(View.GONE);
        }


        if (new SharedPrefDatabase(getApplicationContext()).RetriveBrother_1() != null) {
            layoutBrother1.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/BROTHER_1.jpg";
            imgAlbumBrother1.setImageURI(Uri.parse(path));
            txtAlbumBrother1.setText(new SharedPrefDatabase(getApplicationContext()).RetriveBrother_1());
            txtAlbumBrother1R.setText("BROTHER");
        } else {
            layoutBrother1.setVisibility(View.GONE);
        }

        if (new SharedPrefDatabase(getApplicationContext()).RetriveBrother_2() != null) {
            layoutBrother2.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/BROTHER_2.jpg";
            imgAlbumBrother2.setImageURI(Uri.parse(path));
            txtAlbumBrother2.setText(new SharedPrefDatabase(getApplicationContext()).RetriveBrother_2());
            txtAlbumBrother2R.setText("BROTHER");
        } else {
            layoutBrother2.setVisibility(View.GONE);
        }

        if (new SharedPrefDatabase(getApplicationContext()).RetriveBrother_3() != null) {
            layoutBrother3.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/BROTHER_3.jpg";
            imgAlbumBrother3.setImageURI(Uri.parse(path));
            txtAlbumBrother3.setText(new SharedPrefDatabase(getApplicationContext()).RetriveBrother_3());
            txtAlbumBrother3R.setText("BROTHER");
        } else {
            layoutBrother3.setVisibility(View.GONE);
        }


        if (new SharedPrefDatabase(getApplicationContext()).RetriveSister_1() != null) {
            layoutSister1.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/SISTER_1.jpg";
            imgAlbumSister1.setImageURI(Uri.parse(path));
            txtAlbumSister1.setText(new SharedPrefDatabase(getApplicationContext()).RetriveSister_1());
            txtAlbumSister1R.setText("SISTER");
        } else {
            layoutSister1.setVisibility(View.GONE);
        }

        if (new SharedPrefDatabase(getApplicationContext()).RetriveSister_2() != null) {
            layoutSister2.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/SISTER_2.jpg";
            imgAlbumSister2.setImageURI(Uri.parse(path));
            txtAlbumSister2.setText(new SharedPrefDatabase(getApplicationContext()).RetriveSister_2());
            txtAlbumSister2R.setText("SISTER");
        } else {
            layoutSister2.setVisibility(View.GONE);
        }


        if (new SharedPrefDatabase(getApplicationContext()).RetriveSister_3() != null) {
            layoutSister3.setVisibility(View.VISIBLE);
            String path = getExternalCacheDir().getPath() + "/SISTER_3.jpg";
            imgAlbumSister3.setImageURI(Uri.parse(path));
            txtAlbumSister3.setText(new SharedPrefDatabase(getApplicationContext()).RetriveSister_3());
            txtAlbumSister3R.setText("SISTER");
        } else {
            layoutSister3.setVisibility(View.GONE);
        }


        actionEvent2();
        actionEventSound(imgAlbumFatherSound, txtAlbumFather);
        actionEventSound(imgAlbumMotherSound, txtAlbumMother);
        actionEventSound(imgAlbumGFatherSound, txtAlbumGFather);
        actionEventSound(imgAlbumGMotherSound, txtAlbumGMother);
        actionEventSound(imgAlbumBrother1Sound, txtAlbumBrother1);
        actionEventSound(imgAlbumBrother2Sound, txtAlbumBrother2);
        actionEventSound(imgAlbumBrother3Sound, txtAlbumBrother3);
        actionEventSound(imgAlbumSister1Sound, txtAlbumSister1);
        actionEventSound(imgAlbumSister2Sound, txtAlbumSister2);
        actionEventSound(imgAlbumSister3Sound, txtAlbumSister3);

    }

    private void actionEvent2() {
        layoutFather.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Long click works", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }


    private TextToSpeech textToSpeech;
    private void actionEventSound(ImageView button, final TextView textView) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = textToSpeech.setLanguage(Locale.US);
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "This Language is not supported");
                            } else {
                                textToSpeech.speak(textView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                            }
                        } else {
                            Log.e("TTS", "Initilization Failed!");
                        }
                    }
                });
            }
        });

    }







}

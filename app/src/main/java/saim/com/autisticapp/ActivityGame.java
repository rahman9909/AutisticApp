package saim.com.autisticapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import saim.com.autisticapp.Util.DBHelper;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class ActivityGame extends AppCompatActivity {

    TextView txtTitle, txtGameOption1, txtGameOption2;
    LinearLayout layoutGameFamilyAlbum, layoutGameAmarBonduRased;
    DBHelper dbHelper;

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game);


        init();
    }

    private void init() {

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtGameOption1 = (TextView) findViewById(R.id.txtGameOption1);
        txtGameOption2 = (TextView) findViewById(R.id.txtGameOption2);

        layoutGameFamilyAlbum = (LinearLayout) findViewById(R.id.layoutGameFamilyAlbum);
        layoutGameAmarBonduRased = (LinearLayout) findViewById(R.id.layoutGameAmarBonduRased);

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.game_bn_1);
            txtGameOption1.setText(R.string.game_bn_2);
            txtGameOption2.setText(R.string.game_bn_3);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.game_en_1);
            txtGameOption1.setText(R.string.game_en_2);
            txtGameOption2.setText(R.string.game_en_3);
        }

        dbHelper = new DBHelper(this);

        if (dbHelper.getAllFamilyMembers().size() < 2) {
            showDialogComplete(this, "This game need minimum 2 family member or Object");
        } else {
            actionEvent();
        }
    }

    private void actionEvent() {
        layoutGameFamilyAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityGameSelect.class);
                intent.putExtra("GAME_TYPE", 1);
                startActivity(intent);
            }
        });

        layoutGameAmarBonduRased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityGameSelect.class);
                intent.putExtra("GAME_TYPE", 2);
                startActivity(intent);
                //showDialogComplete2(v.getContext(), "This game is under construction.");
            }
        });
    }


    public void showDialogComplete(Context context, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle("Opps")
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

    public void showDialogComplete2(Context context, String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setTitle("Opps")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                })
                .setIcon(android.R.drawable.star_on)
                .show();
    }



}

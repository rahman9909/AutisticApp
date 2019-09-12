package saim.com.autisticapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import saim.com.autisticapp.Util.DBHelper;

public class ActivityGame extends AppCompatActivity {

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
        layoutGameFamilyAlbum = (LinearLayout) findViewById(R.id.layoutGameFamilyAlbum);
        layoutGameAmarBonduRased = (LinearLayout) findViewById(R.id.layoutGameAmarBonduRased);

        dbHelper = new DBHelper(this);

        if (dbHelper.getAllFamilyMembers().size() < 2) {
            showDialogComplete(this, "This game need minimum 2 family member");
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
                //Intent intent = new Intent(getApplicationContext(), ActivityGameSelect.class);
                //intent.putExtra("GAME_TYPE", 2);
                //startActivity(intent);
                showDialogComplete2(v.getContext(), "This game is under construction.");
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

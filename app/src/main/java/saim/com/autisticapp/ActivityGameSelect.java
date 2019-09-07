package saim.com.autisticapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityGameSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_select);
    }
}

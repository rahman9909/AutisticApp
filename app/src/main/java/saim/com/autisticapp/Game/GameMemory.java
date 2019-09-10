package saim.com.autisticapp.Game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import saim.com.autisticapp.R;

public class GameMemory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_memory);
    }
}

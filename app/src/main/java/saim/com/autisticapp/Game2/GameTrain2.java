package saim.com.autisticapp.Game2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import saim.com.autisticapp.Adapter.AdapterTraining2;
import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelperRashed;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class GameTrain2 extends AppCompatActivity {

    int GAME_TYPE;
    TextView txtTitle;
    ArrayList<ModelFamily> arrayListFamily = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManagerRecyclerView;
    RecyclerView.Adapter recyclerViewAdapterFamily;

    DBHelperRashed mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_game_train2);

        init();
    }


    public void init() {
        GAME_TYPE = getIntent().getExtras().getInt("GAME_TYPE");
        mydb = new DBHelperRashed(this);

        txtTitle = findViewById(R.id.txtTitle);

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.games_bn_2);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.games_en_2);
        }

        arrayListFamily = mydb.getAllFamilyMembersNew();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManagerRecyclerView = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManagerRecyclerView);
        recyclerView.setHasFixedSize(true);


        recyclerViewAdapterFamily = new AdapterTraining2(arrayListFamily);
        recyclerView.setAdapter(recyclerViewAdapterFamily);


    }
}

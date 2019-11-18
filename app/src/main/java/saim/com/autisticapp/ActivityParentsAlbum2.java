package saim.com.autisticapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import saim.com.autisticapp.Adapter.AdapterFamily;
import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.Util.DBHelper;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class ActivityParentsAlbum2 extends AppCompatActivity {

    TextView txtTitle;

    ArrayList<ModelFamily> arrayListFamily = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManagerRecyclerView;
    RecyclerView.Adapter recyclerViewAdapterFamily;

    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_parents_album2);

        init();
    }


    public void init() {

        txtTitle = (TextView) findViewById(R.id.txtTitle);

        if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("BN")) {
            txtTitle.setText(R.string.album_bn_1);
        } else if (new SharedPrefDatabase(getApplicationContext()).RetriveLanguage().equals("EN")) {
            txtTitle.setText(R.string.album_en_1);
        }

        mydb = new DBHelper(this);
        arrayListFamily = mydb.getAllFamilyMembersNew();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManagerRecyclerView = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerRecyclerView);
        recyclerView.setHasFixedSize(true);


        recyclerViewAdapterFamily = new AdapterFamily(arrayListFamily);
        recyclerView.setAdapter(recyclerViewAdapterFamily);


    }
}

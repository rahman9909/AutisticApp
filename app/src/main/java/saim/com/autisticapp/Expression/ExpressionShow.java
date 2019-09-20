package saim.com.autisticapp.Expression;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import saim.com.autisticapp.Adapter.AdapterTraining2;
import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelper;
import saim.com.autisticapp.Util.DBHelperEmoji;
import saim.com.autisticapp.Util.DBHelperRashed;

public class ExpressionShow extends AppCompatActivity {

    ArrayList<ModelFamily> arrayListFamily = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManagerRecyclerView;
    RecyclerView.Adapter recyclerViewAdapterFamily;

    DBHelperEmoji mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFull);
        setContentView(R.layout.activity_expression_show2);

        init();
    }


    public void init() {
        mydb = new DBHelperEmoji(this);
        arrayListFamily = mydb.getAllFamilyMembers();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManagerRecyclerView = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManagerRecyclerView);
        recyclerView.setHasFixedSize(true);


        recyclerViewAdapterFamily = new AdapterTraining2(arrayListFamily);
        recyclerView.setAdapter(recyclerViewAdapterFamily);

    }
}

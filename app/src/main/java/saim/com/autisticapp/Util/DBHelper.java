package saim.com.autisticapp.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import saim.com.autisticapp.Model.ModelFamily;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "family";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_RELATION = "RELATION";
    public static final String COLUMN_IMAGE = "IMAGE";
    public static final String COLUMN_SOUND = "SOUND";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table family " + "(ID integer primary key, NAME text, RELATION text, IMAGE text, SOUND text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS family");
        onCreate(db);
    }

    public boolean insertFamilyMember(String name, String relation, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("SAIM_DATABASE", name + " " + relation + " " + image);
        contentValues.put("NAME", name);
        contentValues.put("RELATION", relation);
        contentValues.put("IMAGE", image);
        db.insert("family", null, contentValues);
        return true;
    }

    public boolean insertFamilyMember(String name, String relation, String image, String sound) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("SAIM_DATABASE", name + " " + relation + " " + image);
        contentValues.put("NAME", name);
        contentValues.put("RELATION", relation);
        contentValues.put("IMAGE", image);
        contentValues.put("SOUND", sound);
        db.insert("family", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from family where ID=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateFamily(Integer id, String name, String relation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("RELATION", relation);
        contentValues.put("RELATION", relation);
        db.update("family", contentValues, "ID = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("family", "ID = ? ", new String[]{Integer.toString(id)});
    }

    public ArrayList<ModelFamily> getAllFamilyMembers() {

        ArrayList<ModelFamily> array_list = new ArrayList<ModelFamily>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from family", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            /*String id       = res.getColumnIndex(COLUMN_ID) + "";
            String name     = res.getColumnIndex(COLUMN_NAME) + "";
            String relation = res.getColumnIndex(COLUMN_RELATION) + "";
            String image    = res.getColumnIndex(COLUMN_IMAGE) + "";*/

            String id = res.getInt(0) + "";
            String name = res.getString(1) + "";
            String relation = res.getString(2) + "";
            String image = res.getString(3) + "";


            array_list.add(new ModelFamily(id, name, relation, image));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<ModelFamily> getAllFamilyMembersNew() {

        ArrayList<ModelFamily> array_list = new ArrayList<ModelFamily>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from family", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            /*String id       = res.getColumnIndex(COLUMN_ID) + "";
            String name     = res.getColumnIndex(COLUMN_NAME) + "";
            String relation = res.getColumnIndex(COLUMN_RELATION) + "";
            String image    = res.getColumnIndex(COLUMN_IMAGE) + "";*/

            String id = res.getInt(0) + "";
            String name = res.getString(1) + "";
            String relation = res.getString(2) + "";
            String image = res.getString(3) + "";
            String sound = res.getString(4) + "";


            array_list.add(new ModelFamily(id, name, relation, image, sound));
            res.moveToNext();
        }
        return array_list;
    }
}

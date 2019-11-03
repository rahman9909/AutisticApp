package saim.com.autisticapp.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



public class SharedPrefDatabase {

    public static final String KEY_FAMILY_FATHER = "FATHER";
    public static final String KEY_FAMILY_MOTHER = "MOTHER";

    public static final String KEY_FAMILY_G_FATHER = "GRAND_FATHER";
    public static final String KEY_FAMILY_G_MOTHER = "GRAND_MOTHER";

    public static final String KEY_FAMILY_BROTHER_1 = "BROTHER_1";
    public static final String KEY_FAMILY_BROTHER_2 = "BROTHER_2";
    public static final String KEY_FAMILY_BROTHER_3 = "BROTHER_3";

    public static final String KEY_FAMILY_SISTER_1 = "SISTER_1";
    public static final String KEY_FAMILY_SISTER_2 = "SISTER_2";
    public static final String KEY_FAMILY_SISTER_3 = "SISTER_3";

    public static final String KEY_LANGUAGE = "LANGUAGE";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public SharedPrefDatabase(Context ctx) {
        this.context = ctx;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = sharedPreferences.edit();
    }

    /*Father*/
    public void StoreFather(String data){
        editor.putString(KEY_FAMILY_FATHER, data);
        editor.commit();
    }

    public String RetriveFather(){
        String text = sharedPreferences.getString(KEY_FAMILY_FATHER, null);
        return text;
    }

    /*Mother*/
    public void StoreMother(String data){
        editor.putString(KEY_FAMILY_MOTHER, data);
        editor.commit();
    }

    public String RetriveMother(){
        String text = sharedPreferences.getString(KEY_FAMILY_MOTHER, null);
        return text;
    }

    /*G_Father*/
    public void StoreGFather(String data){
        editor.putString(KEY_FAMILY_G_FATHER, data);
        editor.commit();
    }

    public String RetriveGFather(){
        String text = sharedPreferences.getString(KEY_FAMILY_G_FATHER, null);
        return text;
    }


    /*G_Mother*/
    public void StoreGMother(String data){
        editor.putString(KEY_FAMILY_G_MOTHER, data);
        editor.commit();
    }

    public String RetriveGMother(){
        String text = sharedPreferences.getString(KEY_FAMILY_G_MOTHER, null);
        return text;
    }


    /*Brother 1*/
    public void StoreBrother_1(String data){
        editor.putString(KEY_FAMILY_BROTHER_1, data);
        editor.commit();
    }

    public String RetriveBrother_1(){
        String text = sharedPreferences.getString(KEY_FAMILY_BROTHER_1, null);
        return text;
    }

    /*Brother 2*/
    public void StoreBrother_2(String data){
        editor.putString(KEY_FAMILY_BROTHER_2, data);
        editor.commit();
    }

    public String RetriveBrother_2(){
        String text = sharedPreferences.getString(KEY_FAMILY_BROTHER_2, null);
        return text;
    }

    /*Brother 3*/
    public void StoreBrother_3(String data){
        editor.putString(KEY_FAMILY_BROTHER_3, data);
        editor.commit();
    }

    public String RetriveBrother_3(){
        String text = sharedPreferences.getString(KEY_FAMILY_BROTHER_3, null);
        return text;
    }


    /*Sister 1*/
    public void StoreSister_1(String data){
        editor.putString(KEY_FAMILY_SISTER_1, data);
        editor.commit();
    }

    public String RetriveSister_1(){
        String text = sharedPreferences.getString(KEY_FAMILY_SISTER_1, null);
        return text;
    }

    /*Sister 2*/
    public void StoreSister_2(String data){
        editor.putString(KEY_FAMILY_SISTER_2, data);
        editor.commit();
    }

    public String RetriveSister_2(){
        String text = sharedPreferences.getString(KEY_FAMILY_SISTER_2, null);
        return text;
    }

    /*Sister 3*/
    public void StoreSister_3(String data){
        editor.putString(KEY_FAMILY_SISTER_3, data);
        editor.commit();
    }

    public String RetriveSister_3(){
        String text = sharedPreferences.getString(KEY_FAMILY_SISTER_3, null);
        return text;
    }


    /*Language*/
    public void StoreLanguage(String data) {
        editor.putString(KEY_LANGUAGE, data);
        editor.commit();
    }

    public String RetriveLanguage() {
        String text = sharedPreferences.getString(KEY_LANGUAGE, "EN");
        return text;
    }


}

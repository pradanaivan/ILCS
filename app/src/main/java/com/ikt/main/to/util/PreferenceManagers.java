package com.ikt.main.to.util;

/**
 * Created by uer on 27/11/2015.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferenceManagers {


    public static void setData(String data, String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor prefEditor = prefs.edit();
        prefEditor.putString(key, data);
        prefEditor.commit();
    }

    public static void setDataWithSameKey(String data, String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        if (prefs.contains(key)) {
            editor.remove(key);
            editor.commit();
        }
        editor.putString(key, data);
        editor.commit();
    }

    public static void clearByKey(String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        if (prefs.contains(key)) {
            editor.remove(key);
            editor.commit();
        }
    }

    public static String getData(String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, null);
    }

    public static boolean hasData(String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, null) != null;
    }

    public static void clear(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context); // here you get your prefrences by either of two methods
        Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}

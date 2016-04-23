package com.example.root.angelhackapp.Activity.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by root on 23/4/16.
 */
public class MyPreferenceManager {
    static SharedPreferences sharedPreferences;

     public static void saveBooleanSharedPreferences(String key,boolean value,Context context){
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(key,value);
    }
    public static boolean getBooleanSharedPreference(String key,Context context){
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
      return  sharedPreferences.getBoolean(key,false);
    }

}

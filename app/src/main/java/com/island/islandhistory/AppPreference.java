package com.island.islandhistory;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private static AppPreference appPreference;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    public static String APP_SOUND="sound";

    private AppPreference(Context context) {
        mPref = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public static AppPreference getInstance(Context context) {
        if (appPreference == null) {
            appPreference = new AppPreference(context.getApplicationContext());
        }
        return appPreference;
    }


    // GET SET

    public void setSound(boolean sound)
    {
        mEditor.putBoolean(APP_SOUND,sound);
        mEditor.commit();
    }
    public boolean getSound()
    {
        return mPref.getBoolean(APP_SOUND,true);
    }

}
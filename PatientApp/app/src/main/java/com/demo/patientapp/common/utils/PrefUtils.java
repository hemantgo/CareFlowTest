package com.demo.patientapp.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils{

    public final static String PREF_TOKEN = "PREF_TOKEN";
    public final static String KEY_TOKEN = "KEY_TOKEN";

    /**
     * Storing Token in shared preferences to
     * add it in header part of every retrofit request
     */
    public PrefUtils() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE);
    }

    public static void storeToken(Context context, String token) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public static String getToken(Context context) {
        return getSharedPreferences(context).getString(KEY_TOKEN, null);
    }
}
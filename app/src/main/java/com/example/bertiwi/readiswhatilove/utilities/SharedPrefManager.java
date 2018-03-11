package com.example.bertiwi.readiswhatilove.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bertiwi on 11/03/2018.
 */

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "codingsharedpref";
    private static final String KEY_TOKEN = "keytoken";

    private static SharedPrefManager mInstance;
    private static Context mContext;

    public SharedPrefManager(Context context) {
        mContext = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public String getToken() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public void setToken(String token) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

}

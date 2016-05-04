package com.hlandim.fragmentmanager.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hlandim on 5/2/16.
 */
public class Utility {

    private static final String SHERED_NAME = "mySharedName";
    private static final String SHARED_KEY_IS_LOGGED = "isLogged";

    public static boolean isLogged(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHERED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(SHARED_KEY_IS_LOGGED, false);
    }

    public static void saveLogin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHERED_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(SHARED_KEY_IS_LOGGED, true).commit();

    }
}

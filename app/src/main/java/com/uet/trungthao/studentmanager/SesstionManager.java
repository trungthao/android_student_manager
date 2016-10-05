package com.uet.trungthao.studentmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;

/**
 * Created by JiH on 03/10/2016.
 */

public class SesstionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "login";
    private static final String KEY_USER_NAME = "UserName";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_LOGIN = "islogin";


    public SesstionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setStatusLogin(String userName, String password) {
        editor.putString(KEY_USER_NAME, userName)
                .putString(KEY_PASSWORD, password)
                .putBoolean(KEY_IS_LOGIN, true)
                .commit();
    }

    public boolean getStatusLogin() {
        return pref.getBoolean(KEY_IS_LOGIN, false);
    }

    public void clearSession() {
        editor.clear().commit();
    }
}

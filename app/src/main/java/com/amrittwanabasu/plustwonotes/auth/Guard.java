package com.amrittwanabasu.plustwonotes.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.amrittwanabasu.plustwonotes.common.Constants;

public class Guard {

    /**
     * Application Context.
     */
    private Context context;

    /**
     * SharedPreferences instance.
     */
    private static SharedPreferences prefs;

    /**
     * Creates Guard instance.
     *
     * @param context   Application context.
     */
    public Guard(Context context) {
        this.context = context;
    }

    /**
     * Checks if API is authenticated.
     *
     * @return boolean
     */
    public boolean isAuthenticated() {
        return (getToken() != null);
    }

    /**
     * Returns token stored in shared prefs.
     *
     * @return  Token String.
     */
    public String getToken() {
        return this.getPrefs().getString(Constants.AUTH_PREFS_TOKEN,null);
    }

    /**
     * Sets token to storage.
     *
     * @param token Token to be applied.
     */
    public void setToken(String token) {
        SharedPreferences.Editor editor = this.getPrefs().edit();

        editor.putString(Constants.AUTH_PREFS_TOKEN,token);

        editor.apply();
    }

    /**
     * Returns shared preference.
     *
     * @return  Auth Shared Prefs.
     */
    private SharedPreferences getPrefs() {
        if(prefs == null) {
            prefs = context.getSharedPreferences(Constants.AUTH_PREFS, Context.MODE_PRIVATE);
        }

        return prefs;
    }
}

package com.example.qlsv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.qlsv.Entity.AdminUser;

public class SharedPreferencesManager {
    private static final String PREF_NAME = "MyAppPrefs";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_NAME = "admin_name";
    private static final String KEY_AVATAR = "admin_avatar";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void saveAdminInfo(AdminUser admin) {
        if (admin != null) {
            sharedPreferences.edit()
                    .putString(KEY_NAME, admin.getFullName())
                    .putString(KEY_AVATAR, admin.getAvatarUrl())
                    .apply();
        }
    }

    public String getAdminName() {
        return sharedPreferences.getString(KEY_NAME, "Admin");
    }

    public String getAdminAvatar() {
        return sharedPreferences.getString(KEY_AVATAR, null);
    }
}

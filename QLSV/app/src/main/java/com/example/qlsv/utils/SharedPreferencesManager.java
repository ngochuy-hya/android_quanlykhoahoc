package com.example.qlsv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.qlsv.Entity.AdminUser;
import com.example.qlsv.R;
import com.google.android.material.navigation.NavigationView;

public class SharedPreferencesManager {
    private static final String PREF_NAME = "QLSV_Prefs";
    private static final String KEY_ADMIN_ID = "admin_id";
    private static final String KEY_ADMIN_NAME = "admin_name";
    private static final String KEY_ADMIN_EMAIL = "admin_email";
    private static final String KEY_ADMIN_USERNAME = "admin_username";
    private static final String KEY_ADMIN_PHONE = "admin_phone";
    private static final String KEY_ADMIN_AVATAR = "admin_avatar";
    private static final String KEY_ADMIN_PASSWORD = "admin_password";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_TOKEN = "token";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveAdminInfo(AdminUser admin) {
        if (admin != null) {
            sharedPreferences.edit()
                .putInt(KEY_ADMIN_ID, admin.getId())
                .putString(KEY_ADMIN_NAME, admin.getFullName())
                .putString(KEY_ADMIN_EMAIL, admin.getEmail())
                .putString(KEY_ADMIN_USERNAME, admin.getUsername())
                .putString(KEY_ADMIN_AVATAR, admin.getAvatarUrl())
                .putString(KEY_ADMIN_PASSWORD, admin.getPassword())
                .apply();
        }
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void saveAdminId(int id) {
        sharedPreferences.edit().putInt(KEY_ADMIN_ID, id).apply();
    }

    public int getAdminId() {
        return sharedPreferences.getInt(KEY_ADMIN_ID, -1);
    }

    public void saveAdminName(String name) {
        sharedPreferences.edit().putString(KEY_ADMIN_NAME, name).apply();
    }

    public String getAdminName() {
        return sharedPreferences.getString(KEY_ADMIN_NAME, "");
    }

    public void saveAdminEmail(String email) {
        sharedPreferences.edit().putString(KEY_ADMIN_EMAIL, email).apply();
    }

    public String getAdminEmail() {
        return sharedPreferences.getString(KEY_ADMIN_EMAIL, "");
    }

    public void saveAdminUsername(String username) {
        sharedPreferences.edit().putString(KEY_ADMIN_USERNAME, username).apply();
    }

    public String getAdminUsername() {
        return sharedPreferences.getString(KEY_ADMIN_USERNAME, "");
    }

    public void saveAdminPhone(String phone) {
        sharedPreferences.edit().putString(KEY_ADMIN_PHONE, phone).apply();
    }

    public String getAdminPhone() {
        return sharedPreferences.getString(KEY_ADMIN_PHONE, "");
    }

    public void saveAdminAvatar(String avatar) {
        sharedPreferences.edit().putString(KEY_ADMIN_AVATAR, avatar).apply();
    }

    public String getAdminAvatar() {
        return sharedPreferences.getString(KEY_ADMIN_AVATAR, "");
    }

    public void saveAdminPassword(String password) {
        sharedPreferences.edit().putString(KEY_ADMIN_PASSWORD, password).apply();
    }

    public String getAdminPassword() {
        return sharedPreferences.getString(KEY_ADMIN_PASSWORD, "");
    }

    public void setLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clearAll() {
        sharedPreferences.edit().clear().apply();
    }
}

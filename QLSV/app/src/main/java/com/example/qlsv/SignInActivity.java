package com.example.qlsv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qlsv.Entity.AdminUser;
import com.example.qlsv.Service.Apimanager;
import com.example.qlsv.utils.SharedPreferencesManager;

public class SignInActivity extends AppCompatActivity {

    EditText txtPhone, txtPassword;
    Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);

        txtPhone = findViewById(R.id.editTextPhoneEmail);
        txtPassword = findViewById(R.id.editTextPassword);
        btnDangNhap = findViewById(R.id.buttonSignIn);

        btnDangNhap.setOnClickListener(v -> {
            String phone = txtPhone.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            Apimanager apimanager = new Apimanager(SignInActivity.this);
            apimanager.SignIn(phone, password, new Apimanager.LoginCallback() {
                @Override
                public void onSuccess(String token, AdminUser admin) {
                    // Lưu token và thông tin admin
                    SharedPreferencesManager prefs = new SharedPreferencesManager(SignInActivity.this);
                    prefs.saveToken(token);
                    Log.d("DEBUG", "Full name: " + admin.getFullName());
                    Log.d("DEBUG", "Avatar URL: " + admin.getAvatarUrl());

                    prefs.saveAdminInfo(admin);

                    Toast.makeText(SignInActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        });

        findViewById(R.id.txtFotgot).setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, ForgotPasswordActivity.class));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

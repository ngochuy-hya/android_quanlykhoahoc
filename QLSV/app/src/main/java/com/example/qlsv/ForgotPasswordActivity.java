package com.example.qlsv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv.Service.Apimanager;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText txtEmail;
    Button btnSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        txtEmail = findViewById(R.id.editTextEmail);
        btnSendCode = findViewById(R.id.buttonSendOtp);

        btnSendCode.setOnClickListener(v -> {
            String email = txtEmail.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gọi API
            Apimanager apimanager = new Apimanager(this);
            apimanager.forgotPassword(email, this, new Apimanager.ForgotPasswordCallback() {
                @Override
                public void onSuccess() {
                    // Email hợp lệ, chuyển sang VerifyResetPasswordActivity
                    Intent intent = new Intent(ForgotPasswordActivity.this, VerifyResetPasswordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(ForgotPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}

package com.example.qlsv;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.qlsv.Service.Apimanager;

public class VerifyResetPasswordActivity extends AppCompatActivity {

    Button buttonConfirmReset;
    EditText editTextOtp,editTextNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verify_reset_password);
        String email = getIntent().getStringExtra("email");
        buttonConfirmReset = findViewById(R.id.buttonConfirmReset);
        editTextOtp = findViewById(R.id.editTextOtp);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        buttonConfirmReset.setOnClickListener(v -> {
            String otp = editTextOtp.getText().toString().trim();
            String newPassword = editTextNewPassword.getText().toString().trim();

            if (otp.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ OTP và mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            new Apimanager(this).resetPassword(email, otp, newPassword, this);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
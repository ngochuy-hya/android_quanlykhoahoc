package com.example.qlsv.Service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.qlsv.MainActivity;
import com.example.qlsv.Service.DTO.Request.ForgotPasswordRequest;
import com.example.qlsv.Service.DTO.Request.LoginRequest;
import com.example.qlsv.Service.DTO.Request.ResetPasswordRequest;
import com.example.qlsv.Service.DTO.Response.LoginResponse;
import com.example.qlsv.SignInActivity;
import com.example.qlsv.utils.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Apimanager {
    private ApiService apiService;
    private SharedPreferencesManager preferencesManager;

    public Apimanager(Context context) {
        // Sử dụng Retrofit đã cấu hình sẵn
        apiService = RetrofitClient.getInstance().create(ApiService.class);
        preferencesManager = new SharedPreferencesManager(context);
    }

    public void SignIn(String phone, String password, LoginCallback callback) {
        LoginRequest request = new LoginRequest();
        request.setUsername(phone);
        request.setPassword(password);

        apiService.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    preferencesManager.saveToken(token);
                    callback.onSuccess(token);
                } else {
                    callback.onFailure("Sai tài khoản hoặc mật khẩu!");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
    public interface LoginCallback {
        void onSuccess(String token);
        void onFailure(String message);
    }
    public void forgotPassword(String email, Context context, ForgotPasswordCallback callback) {
        ForgotPasswordRequest request = new ForgotPasswordRequest(email);

        apiService.forgotPassword(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess();
                } else {
                    callback.onFailure("Email không tồn tại hoặc không hợp lệ!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
    public interface ForgotPasswordCallback {
        void onSuccess();
        void onFailure(String message);
    }
    public void resetPassword(String email, String otp, String newPassword, Context context) {
        ResetPasswordRequest request = new ResetPasswordRequest(email, otp, newPassword);

        apiService.resetPassword(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, SignInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "OTP không hợp lệ hoặc đã hết hạn!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

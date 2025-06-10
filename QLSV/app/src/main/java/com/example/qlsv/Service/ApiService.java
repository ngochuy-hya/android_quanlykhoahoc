package com.example.qlsv.Service;

import com.example.qlsv.Service.DTO.Request.ForgotPasswordRequest;
import com.example.qlsv.Service.DTO.Request.LoginRequest;
import com.example.qlsv.Service.DTO.Request.ResetPasswordRequest;
import com.example.qlsv.Service.DTO.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

//    Bảo mật
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
    @POST("auth/forgot-password")
    Call<Void> forgotPassword(@Body ForgotPasswordRequest request);

    @POST("auth/reset-password")
    Call<Void> resetPassword(@Body ResetPasswordRequest request);
}

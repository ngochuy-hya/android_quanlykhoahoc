package com.example.qlsv.Service;

import com.example.qlsv.Service.DTO.Request.ForgotPasswordRequest;
import com.example.qlsv.Service.DTO.Request.LoginRequest;
import com.example.qlsv.Service.DTO.Request.ResetPasswordRequest;
import com.example.qlsv.Service.DTO.Response.LoginResponse;
import com.example.qlsv.Service.DTO.Response.ScheduleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

//    Bảo mật
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
    @POST("auth/forgot-password")
    Call<Void> forgotPassword(@Body ForgotPasswordRequest request);

    @POST("auth/reset-password")
    Call<Void> resetPassword(@Body ResetPasswordRequest request);

    // Homepage
    @GET("schedules/nearest") // hoặc endpoint đúng của bạn
    Call<ScheduleResponse> getNearestSchedule(@Header("Authorization") String token);
}

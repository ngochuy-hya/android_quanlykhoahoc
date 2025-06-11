package com.example.qlsv.Service;

import com.example.qlsv.Service.DTO.Request.ForgotPasswordRequest;
import com.example.qlsv.Service.DTO.Request.LoginRequest;
import com.example.qlsv.Service.DTO.Request.ResetPasswordRequest;
import com.example.qlsv.Service.DTO.Response.LoginResponse;
import com.example.qlsv.Service.DTO.Response.ScheduleResponse;
import com.example.qlsv.Entity.AdminUser;
import com.example.qlsv.Service.DTO.Request.ChangePasswordRequest;
import com.example.qlsv.Service.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    @GET("admin-users/{id}")
    Call<AdminUser> getAdminUserById(@Path("id") int id);

    @PUT("admin-users/{id}")
    Call<AdminUser> updateAdminUser(@Path("id") int id, @Body AdminUser adminUser, @Header("Authorization") String token);

    @POST("admin-users/{id}/change-password")
    Call<ApiResponse> changePassword(@Path("id") int id, @Body ChangePasswordRequest request, @Header("Authorization") String token);
}

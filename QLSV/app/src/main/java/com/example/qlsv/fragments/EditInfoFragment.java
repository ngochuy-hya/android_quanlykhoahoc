package com.example.qlsv.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.qlsv.R;
import com.example.qlsv.Entity.AdminUser;
import com.example.qlsv.Service.ApiService;
import com.example.qlsv.Service.RetrofitClient;
import com.example.qlsv.utils.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class EditInfoFragment extends Fragment {

    public EditInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        EditText etFullName = view.findViewById(R.id.etFullName);
        EditText etEmail = view.findViewById(R.id.etEmailAddress);
        EditText etUsername = view.findViewById(R.id.etUsername);

        // Load current user info
        SharedPreferencesManager prefs = new SharedPreferencesManager(requireContext());
        etFullName.setText(prefs.getAdminName());
        etEmail.setText(prefs.getAdminEmail());
        etUsername.setText(prefs.getAdminUsername());
        int adminId = prefs.getAdminId();

        // Set click listener for Save button
        View saveButton = view.findViewById(R.id.btnSave);
        saveButton.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String username = etUsername.getText().toString().trim();

            AdminUser updatedUser = new AdminUser();
            updatedUser.setId(adminId);
            updatedUser.setFullName(fullName);
            updatedUser.setEmail(email);
            updatedUser.setUsername(username);
            updatedUser.setAvatarUrl(prefs.getAdminAvatar() != null ? prefs.getAdminAvatar() : "");
            updatedUser.setPassword(prefs.getAdminPassword() != null ? prefs.getAdminPassword() : "");

            // Log request data
            Log.d("API_DEBUG", "Update request - ID: " + adminId);
            Log.d("API_DEBUG", "Update request - FullName: " + fullName);
            Log.d("API_DEBUG", "Update request - Email: " + email);
            Log.d("API_DEBUG", "Update request - Username: " + username);
            Log.d("API_DEBUG", "Update request - Avatar: " + prefs.getAdminAvatar());

            // Get token from SharedPreferences
            String token = prefs.getToken();
            if (token == null || token.isEmpty()) {
                Log.e("API_DEBUG", "No token found in SharedPreferences");
                Toast.makeText(requireContext(), "Phiên đăng nhập hết hạn, vui lòng đăng nhập lại!", Toast.LENGTH_SHORT).show();
                return;
            }

            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            apiService.updateAdminUser(adminId, updatedUser, "Bearer " + token).enqueue(new Callback<AdminUser>() {
                @Override
                public void onResponse(Call<AdminUser> call, Response<AdminUser> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Log success response
                        Log.d("API_DEBUG", "Update success - Response: " + response.body().toString());
                        // Cập nhật lại SharedPreferences nếu muốn
                        prefs.saveAdminInfo(response.body());
                        Toast.makeText(requireContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        // Quay lại UserInfoFragment
                        Navigation.findNavController(view).popBackStack();
                    } else {
                        // Log error response
                        String errorBody = "";
                        try {
                            errorBody = response.errorBody() != null ? response.errorBody().string() : "No error body";
                        } catch (IOException e) {
                            errorBody = "Error reading error body: " + e.getMessage();
                        }
                        Log.e("API_DEBUG", "Update failed - Code: " + response.code() + ", Error: " + errorBody);
                        Toast.makeText(requireContext(), "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AdminUser> call, Throwable t) {
                    // Log failure
                    Log.e("API_DEBUG", "Update failed - Error: " + t.getMessage());
                    Toast.makeText(requireContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Set click listener for Back button
        View backButton = view.findViewById(R.id.backToPreFrBtn);
        backButton.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
    }
}
package com.example.qlsv.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.qlsv.R;
import com.example.qlsv.Service.ApiService;
import com.example.qlsv.Service.RetrofitClient;
import com.example.qlsv.Service.DTO.Request.ChangePasswordRequest;
import com.example.qlsv.utils.SharedPreferencesManager;
import com.google.android.material.textfield.TextInputEditText;
import com.example.qlsv.Service.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordFragment extends Fragment {

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set click listener for Back button
        View backButton = view.findViewById(R.id.backToPreFrBtn);
        backButton.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        // Initialize views
        TextInputEditText oldPasswordInput = view.findViewById(R.id.oldPassword);
        TextInputEditText newPasswordInput = view.findViewById(R.id.newPassword);
        TextInputEditText confirmPasswordInput = view.findViewById(R.id.confirmPassword);
        Button saveButton = view.findViewById(R.id.savePasswordBtn);

        // Set click listener for Save button
        saveButton.setOnClickListener(v -> {
            String oldPassword = oldPasswordInput.getText().toString().trim();
            String newPassword = newPasswordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            // Validate input
            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(requireContext(), "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get admin ID from SharedPreferences
            SharedPreferencesManager prefs = new SharedPreferencesManager(requireContext());
            int adminId = prefs.getAdminId();
            String token = prefs.getToken();

            if (token == null || token.isEmpty()) {
                Toast.makeText(requireContext(), "Phiên đăng nhập hết hạn, vui lòng đăng nhập lại!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create request object
            ChangePasswordRequest request = new ChangePasswordRequest(oldPassword, newPassword);

            // Call API to change password
            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            apiService.changePassword(adminId, request, "Bearer " + token).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).popBackStack();
                    } else {
                        String errorMessage = "Đổi mật khẩu thất bại!";
                        try {
                            if (response.errorBody() != null) {
                                errorMessage = response.errorBody().string();
                            }
                        } catch (Exception e) {
                            Log.e("API_DEBUG", "Error reading error body: " + e.getMessage());
                        }
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("API_DEBUG", "Error changing password: " + t.getMessage());
                    Toast.makeText(requireContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}

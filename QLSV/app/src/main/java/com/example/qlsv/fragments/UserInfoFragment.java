package com.example.qlsv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.qlsv.R;
import com.example.qlsv.utils.SharedPreferencesManager;

public class UserInfoFragment extends Fragment {

    public UserInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        ImageView avatar = view.findViewById(R.id.avatar);
        TextView name = view.findViewById(R.id.name);
        TextView email = view.findViewById(R.id.email);
        TextView username = view.findViewById(R.id.username);

        // Các TextView phía dưới
        TextView bindUsername = view.findViewById(R.id.bindUsername);
        TextView bindAddress = view.findViewById(R.id.bindAddress);
        TextView bindEmail = view.findViewById(R.id.bindEmail);

        // Load user info
        SharedPreferencesManager prefs = new SharedPreferencesManager(requireContext());
        String fullName = prefs.getAdminName();
        String userName = prefs.getAdminUsername();
        String emailStr = prefs.getAdminEmail();

        name.setText(fullName);
        email.setText(emailStr);
        username.setText(userName);

        // Set dữ liệu cho các TextView phía dưới
        bindUsername.setText(fullName);
        bindAddress.setText(userName);
        bindEmail.setText(emailStr);

        // Load avatar using Glide
        String avatarUrl = prefs.getAdminAvatar();
        if (avatarUrl != null && !avatarUrl.isEmpty()) {
            Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.ic_avatar_placeholder)
                .error(R.drawable.ic_avatar_placeholder)
                .circleCrop()
                .into(avatar);
        }

        // Set click listener for Edit button bên phải
        View editRightBtn = view.findViewById(R.id.navigateToEditInfo);
        editRightBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_userInfoFragment_to_editInfoFragment);
        });

        // Set click listener for Back button
        View backButton = view.findViewById(R.id.backToPreFrBtn);
        backButton.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
    }
}

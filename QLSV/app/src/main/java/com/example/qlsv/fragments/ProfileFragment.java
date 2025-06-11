package com.example.qlsv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.qlsv.R;
import com.example.qlsv.utils.SharedPreferencesManager;
import android.util.Log;
import com.example.qlsv.SignInActivity;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("PROFILE", "onViewCreated ProfileFragment");

        // Initialize views
        ImageView avatarImageView = view.findViewById(R.id.cardAvatarImageView);
        TextView nameTextView = view.findViewById(R.id.cardNameTextView);
        TextView phoneTextView = view.findViewById(R.id.cardPhoneTextView);

        // Load user info from SharedPreferences
        SharedPreferencesManager prefs = new SharedPreferencesManager(requireContext());
        String name = prefs.getAdminName();
        String avatarUrl = prefs.getAdminAvatar();
        String email = prefs.getAdminEmail();

        // Set user info
        nameTextView.setText(name);
        phoneTextView.setText(email);
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.girlface)
                .error(R.drawable.girlface)
                .into(avatarImageView);

        // Set click listener for Personal Information
        View personalInfoLayout = view.findViewById(R.id.navigateToUserInfo);
        personalInfoLayout.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_userInfoFragment);
        });

        // Set click listener for Change Password
        View changePasswordLayout = view.findViewById(R.id.navigateToChangePassword);
        changePasswordLayout.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_changePasswordFragment);
        });

        // Set click listener for Logout
        View logoutLayout = view.findViewById(R.id.logoutButton);
        logoutLayout.setOnClickListener(v -> {
            prefs.clearAll();
            Intent intent = new Intent(requireContext(), SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}

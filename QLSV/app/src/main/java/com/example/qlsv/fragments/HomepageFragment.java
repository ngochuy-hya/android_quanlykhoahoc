package com.example.qlsv.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.qlsv.R;
import com.example.qlsv.Service.Apimanager;
import com.example.qlsv.Service.DTO.Response.ScheduleResponse;
import com.example.qlsv.utils.SharedPreferencesManager;

public class HomepageFragment extends Fragment {

    public HomepageFragment() {
        // Bắt buộc constructor rỗng
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Lấy avatar và tên từ SharedPreferences
        TextView textViewName = view.findViewById(R.id.textViewName);
        ImageView imageViewAvatar = view.findViewById(R.id.imageViewAvatar);

        SharedPreferencesManager prefs = new SharedPreferencesManager(requireContext());
        String name = prefs.getAdminName();
        String avatarUrl = prefs.getAdminAvatar();

        // Hiển thị lời chào
        textViewName.setText("Hi, " + name);

        // Hiển thị ảnh avatar
        Glide.with(requireContext())
                .load(avatarUrl)
                .placeholder(R.drawable.girlface)
                .error(R.drawable.girlface)
                .into(imageViewAvatar);

        // Ánh xạ các view lịch học gần nhất
        TextView txtProgramName = view.findViewById(R.id.textView12);
        TextView txtStartDate = view.findViewById(R.id.txtAppointmentDate);
        TextView txtTeacherSpec = view.findViewById(R.id.txtAppointmentTime);
        TextView txtTeacherName = view.findViewById(R.id.txtAppointmentName);
        ImageView imgTeacher = view.findViewById(R.id.appointmentImage);

        // Gọi API lấy lịch học gần nhất
        Apimanager apiManager = new Apimanager(requireContext());
        apiManager.getNearestSchedule(new Apimanager.ScheduleCallback() {
            @Override
            public void onSuccess(ScheduleResponse s) {
                txtProgramName.setText(s.getProgramName());
                txtStartDate.setText(s.getStartDate());
                txtTeacherSpec.setText(s.getTeacherSpecialization());
                txtTeacherName.setText(s.getTeacherName());

                Glide.with(requireContext())
                        .load(s.getTeacherImage())
                        .placeholder(R.drawable.girlface)
                        .error(R.drawable.girlface)
                        .into(imgTeacher);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

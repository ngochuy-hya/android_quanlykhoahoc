package com.example.qlsv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlsv.R;

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

        // Tại đây bạn có thể xử lý logic sau khi view đã được tạo
        // Example: TextView tv = view.findViewById(R.id.textViewWelcome);
    }
}

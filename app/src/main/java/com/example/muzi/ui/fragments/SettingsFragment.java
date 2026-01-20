package com.example.muzi.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.muzi.R;
import com.example.muzi.auth.AuthActivity;
import com.example.muzi.util.AuthManager;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        
        TextView tvLogout = view.findViewById(R.id.tvLogout);
        tvLogout.setOnClickListener(v -> {
            new AuthManager(requireContext()).logout();
            startActivity(new Intent(requireContext(), AuthActivity.class));
            requireActivity().finish();
        });
        
        return view;
    }
}

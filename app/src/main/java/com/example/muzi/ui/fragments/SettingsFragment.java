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
        
        AuthManager authManager = new AuthManager(requireContext());
        
        // Profile
        TextView tvProfileEmail = view.findViewById(R.id.tvProfileEmail);
        String email = authManager.getUserEmail();
        tvProfileEmail.setText(email != null ? email : "user@email.com");
        
        // Logout
        Button btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            authManager.logout();
            Intent intent = new Intent(requireContext(), AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // Placeholders
        View.OnClickListener placeholderListener = v -> 
            android.widget.Toast.makeText(requireContext(), "Tính năng đang phát triển", android.widget.Toast.LENGTH_SHORT).show();

        view.findViewById(R.id.btnChangePassword).setOnClickListener(placeholderListener);
        view.findViewById(R.id.btnLanguage).setOnClickListener(placeholderListener);
        view.findViewById(R.id.btnNotification).setOnClickListener(placeholderListener);
        view.findViewById(R.id.btnHelpCenter).setOnClickListener(placeholderListener);
        view.findViewById(R.id.btnTerms).setOnClickListener(placeholderListener);
        view.findViewById(R.id.btnAbout).setOnClickListener(placeholderListener);

        return view;
    }
}

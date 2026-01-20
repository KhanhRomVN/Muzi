package com.example.muzi.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.muzi.MainActivity;
import com.example.muzi.R;
import com.example.muzi.util.AuthManager;

public class AuthActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etRepeatPassword;
    private Button btnAction;
    private TextView tvSwitch;
    private boolean isLoginMode = true;
    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authManager = new AuthManager(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        btnAction = findViewById(R.id.btnAction);
        tvSwitch = findViewById(R.id.tvSwitch);

        updateUI();

        btnAction.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isLoginMode) {
                String repeatPassword = etRepeatPassword.getText().toString();
                if (!password.equals(repeatPassword)) {
                    Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Fake Success
            authManager.setLogin(true, email);
            startActivity(new Intent(AuthActivity.this, MainActivity.class));
            finish();
        });

        tvSwitch.setOnClickListener(v -> {
            isLoginMode = !isLoginMode;
            updateUI();
        });
    }

    private void updateUI() {
        if (isLoginMode) {
            etRepeatPassword.setVisibility(View.GONE);
            btnAction.setText(getString(R.string.login));
            tvSwitch.setText(getString(R.string.no_account));
        } else {
            etRepeatPassword.setVisibility(View.VISIBLE);
            btnAction.setText(getString(R.string.register));
            tvSwitch.setText(getString(R.string.have_account));
        }
    }
}

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

    private android.widget.ImageView ivAuthSlideshow;
    private android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
    private String[] hotelImages = {"hotel1.png", "hotel2.png", "hotel3.png"};
    private int currentImageIndex = 0;

    private EditText etEmail, etPassword, etRepeatPassword;
    private android.widget.CheckBox cbRememberMe;
    private TextView tvForgotPassword, tvRepeatPasswordLabel, tvAuthTitle;
    private com.google.android.material.textfield.TextInputLayout tilRepeatPassword;
    private Button btnAction;
    private TextView tvSwitch;
    private boolean isLoginMode = true;
    private AuthManager authManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authManager = new AuthManager(this);

        ivAuthSlideshow = findViewById(R.id.ivAuthSlideshow);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        tilRepeatPassword = findViewById(R.id.tilRepeatPassword);
        tvRepeatPasswordLabel = findViewById(R.id.tvRepeatPasswordLabel);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvAuthTitle = findViewById(R.id.tvAuthTitle);
        btnAction = findViewById(R.id.btnAction);
        tvSwitch = findViewById(R.id.tvSwitch);

        // Start Slideshow
        startSlideshow();

        updateUI();

        btnAction.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isLoginMode) {
                // Login Logic
                if (authManager.login(email, password)) {
                    Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Register Logic
                String repeatPassword = etRepeatPassword.getText().toString();
                if (!password.equals(repeatPassword)) {
                    Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (authManager.register(email, password)) {
                    Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSwitch.setOnClickListener(v -> {
            isLoginMode = !isLoginMode;
            updateUI();
        });
    }

    private void updateUI() {
        if (isLoginMode) {
            tilRepeatPassword.setVisibility(View.GONE);
            tvRepeatPasswordLabel.setVisibility(View.GONE);
            cbRememberMe.setVisibility(View.VISIBLE);
            tvForgotPassword.setVisibility(View.VISIBLE);
            tvAuthTitle.setText("Đăng nhập");
            btnAction.setText("Đăng nhập");
            
            String text = "Chưa có tài khoản? <font color='#075b5f'>Đăng ký ngay</font>";
            tvSwitch.setText(android.text.Html.fromHtml(text, android.text.Html.FROM_HTML_MODE_LEGACY));
        } else {
            tilRepeatPassword.setVisibility(View.VISIBLE);
            tvRepeatPasswordLabel.setVisibility(View.VISIBLE);
            cbRememberMe.setVisibility(View.GONE);
            tvForgotPassword.setVisibility(View.GONE);
            tvAuthTitle.setText("Đăng ký");
            btnAction.setText("Đăng ký");
            
            String text = "Đã có tài khoản? <font color='#075b5f'>Đăng nhập ngay</font>";
            tvSwitch.setText(android.text.Html.fromHtml(text, android.text.Html.FROM_HTML_MODE_LEGACY));
        }
    }

    private void startSlideshow() {
         Runnable slideshowRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    String imagePath = "hotels/" + hotelImages[currentImageIndex];
                    java.io.InputStream ims = getAssets().open(imagePath);
                    android.graphics.drawable.Drawable d = android.graphics.drawable.Drawable.createFromStream(ims, null);
                    
                    // Crossfade animation
                    ivAuthSlideshow.setAlpha(0f);
                    ivAuthSlideshow.setImageDrawable(d);
                    ivAuthSlideshow.animate().alpha(1f).setDuration(1000).start();
                    
                    ims.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }

                currentImageIndex = (currentImageIndex + 1) % hotelImages.length;
                handler.postDelayed(this, 5000); // 5 seconds
            }
        };
        handler.post(slideshowRunnable);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}

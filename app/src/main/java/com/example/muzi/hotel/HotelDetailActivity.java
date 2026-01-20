package com.example.muzi.hotel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.muzi.R;
import java.io.IOException;
import java.io.InputStream;

public class HotelDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        ImageView ivDetailImage = findViewById(R.id.ivDetailImage);
        TextView tvDetailName = findViewById(R.id.tvDetailName);
        TextView tvDetailAddress = findViewById(R.id.tvDetailAddress);
        TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("hotel_name");
            String address = intent.getStringExtra("hotel_address");
            String description = intent.getStringExtra("hotel_description");
            String imagePath = intent.getStringExtra("hotel_image");

            tvDetailName.setText(name);
            tvDetailAddress.setText(address);
            tvDetailDescription.setText(description);

            if (imagePath != null) {
                try {
                    InputStream is = getAssets().open(imagePath);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ivDetailImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

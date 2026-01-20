package com.example.muzi.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.muzi.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ImageView ivConfirmImage = findViewById(R.id.ivConfirmImage);
        TextView tvConfirmHotel = findViewById(R.id.tvConfirmHotel);
        TextView tvConfirmAddress = findViewById(R.id.tvConfirmAddress);
        TextView tvConfirmRoomPrice = findViewById(R.id.tvConfirmRoomPrice);
        TextView tvConfirmPrice = findViewById(R.id.tvConfirmPrice);
        TextView tvBasePrice = findViewById(R.id.tvBasePrice);
        Button btnConfirm = findViewById(R.id.btnConfirmBooking);

        String hotelName = getIntent().getStringExtra("hotel_name");
        String hotelAddress = getIntent().getStringExtra("hotel_address");
        String imagePath = getIntent().getStringExtra("hotel_image");
        double totalPrice = getIntent().getDoubleExtra("total_price", 0);

        tvConfirmHotel.setText(hotelName);
        tvConfirmAddress.setText(hotelAddress != null ? hotelAddress : "Việt Nam, Hà Nội");
        tvConfirmPrice.setText(String.format("%,.0f", totalPrice).replace(',', '.') + " VNĐ");
        tvBasePrice.setText(String.format("%,.0f", totalPrice - 150000).replace(',', '.') + " VNĐ");
        tvConfirmRoomPrice.setText(String.format("%,.0f", (totalPrice - 150000) / 2).replace(',', '.') + " VNĐ");

        // Load image
        loadImageFromAssets(imagePath, ivConfirmImage);
        
        // Load Payment Icons
        loadImageFromAssets("payments/ic_momo.png", findViewById(R.id.ivMomo));
        loadImageFromAssets("payments/ic_zalopay.png", findViewById(R.id.ivZalopay));
        loadImageFromAssets("payments/ic_visa.png", findViewById(R.id.ivVisa));
        loadImageFromAssets("payments/ic_cash.png", findViewById(R.id.ivCash));

        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
            // Navigate to My Bookings in MainActivity
            Intent intent = new Intent(this, com.example.muzi.MainActivity.class);
            intent.putExtra("select_tab", R.id.nav_booking);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void loadImageFromAssets(String path, ImageView imageView) {
        if (path != null && imageView != null) {
            try {
                InputStream is = getAssets().open(path);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

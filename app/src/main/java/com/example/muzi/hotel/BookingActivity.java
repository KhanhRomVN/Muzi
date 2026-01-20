package com.example.muzi.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.muzi.R;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        TextView tvConfirmHotel = findViewById(R.id.tvConfirmHotel);
        TextView tvConfirmRoom = findViewById(R.id.tvConfirmRoom);
        TextView tvConfirmPrice = findViewById(R.id.tvConfirmPrice);
        Button btnConfirm = findViewById(R.id.btnConfirmBooking);

        String hotelName = getIntent().getStringExtra("hotel_name");
        String roomType = getIntent().getStringExtra("room_type");
        double totalPrice = getIntent().getDoubleExtra("total_price", 0);

        tvConfirmHotel.setText(hotelName);
        tvConfirmRoom.setText(roomType);
        tvConfirmPrice.setText(String.format("%,.0f", totalPrice).replace(',', '.') + " VNĐ");

        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(this, "Đặt phòng thành công!", Toast.LENGTH_SHORT).show();
            // Navigate to My Bookings in MainActivity
            Intent intent = new Intent(this, com.example.muzi.MainActivity.class);
            intent.putExtra("select_tab", R.id.nav_booking);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}

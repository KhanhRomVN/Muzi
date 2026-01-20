package com.example.muzi.hotel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.muzi.R;
import com.example.muzi.data.model.Room;
import com.example.muzi.data.model.Comment;
import com.example.muzi.ui.adapter.PhotoAdapter;
import com.example.muzi.ui.adapter.RoomAdapter;
import com.example.muzi.ui.adapter.CommentAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HotelDetailActivity extends AppCompatActivity {

    private TextView tvTotalPrice;
    private Button btnBookNow;
    private View cardBooking;
    private Room selectedRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        ImageView ivDetailImage = findViewById(R.id.ivDetailImage);
        TextView tvDetailName = findViewById(R.id.tvDetailName);
        TextView tvDetailAddress = findViewById(R.id.tvDetailAddress);
        TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);
        TextView tvDetailRatingCount = findViewById(R.id.tvDetailRatingCount);
        TextView tvDetailStayInfo = findViewById(R.id.tvDetailStayInfo);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnBookNow = findViewById(R.id.btnBookNow);
        cardBooking = findViewById(R.id.cardBooking);

        // Initially hide/disable or set to 0
        cardBooking.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("hotel_name");
            String address = intent.getStringExtra("hotel_address");
            String description = intent.getStringExtra("hotel_description");
            String imagePath = intent.getStringExtra("hotel_image");
            float rating = intent.getFloatExtra("hotel_rating", 4.5f);
            String amenities = intent.getStringExtra("hotel_amenities");

            tvDetailName.setText(name);
            tvDetailAddress.setText(address);
            tvDetailDescription.setText(description);
            tvDetailRatingCount.setText(rating + " (124 đánh giá)");
            tvDetailStayInfo.setText(amenities != null ? amenities : "4 giường • 2 phòng tắm");

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

        // Photo Gallery
        RecyclerView rvGallery = findViewById(R.id.rvPhotoGallery);
        rvGallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<String> photos = new ArrayList<>();
        photos.add("hotels/hotel1.png");
        photos.add("hotels/hotel2.png");
        photos.add("hotels/hotel3.png");
        rvGallery.setAdapter(new PhotoAdapter(photos));

        // Room Selection
        RecyclerView rvRooms = findViewById(R.id.rvRoomSelection);
        rvRooms.setLayoutManager(new LinearLayoutManager(this));
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("r1", "h1", "101", "Phòng Deluxe Cao Cấp", 2500000.0, "hotels/hotel1.png", "1 giường đôi cực lớn"));
        rooms.add(new Room("r2", "h1", "102", "Phòng Suite Sang Trọng", 4000000.0, "hotels/hotel2.png", "2 giường đôi, 1 sofa giường"));
        rooms.add(new Room("r3", "h1", "103", "Phòng Đôi Tiêu Chuẩn", 1800000.0, "hotels/hotel3.png", "2 giường đơn"));
        
        RoomAdapter roomAdapter = new RoomAdapter(rooms, room -> {
            selectedRoom = room;
            tvTotalPrice.setText(String.format("%,.0f", room.getPrice()).replace(',', '.') + " VNĐ");
            cardBooking.setVisibility(View.VISIBLE);
        });
        rvRooms.setAdapter(roomAdapter);

        // Comments
        RecyclerView rvComments = findViewById(R.id.rvComments);
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment("c1", "Nguyễn Văn A", "hotels/1.jpg", 5.0f, "Phòng ốc rất sạch sẽ, nhân viên phục vụ tận tình chu đáo. Sẽ quay lại!", "20/01/2026"));
        commentList.add(new Comment("c2", "Trần Thị B", "hotels/2.jpg", 4.5f, "Khách sạn có view nhìn ra biển rất đẹp. Đồ ăn sáng ngon và phong phú.", "18/01/2026"));
        commentList.add(new Comment("c3", "Lê Văn C", "hotels/3.jpg", 4.0f, "Vị trí thuận tiện, gần trung tâm. Tuy nhiên thủ tục check-in hơi chậm một chút.", "15/01/2026"));
        rvComments.setAdapter(new CommentAdapter(commentList));

        btnBookNow.setOnClickListener(v -> {
            if (selectedRoom != null) {
                // Navigate to Booking Activity
                Intent bookingIntent = new Intent(this, BookingActivity.class);
                bookingIntent.putExtra("hotel_name", tvDetailName.getText().toString());
                bookingIntent.putExtra("hotel_address", tvDetailAddress.getText().toString());
                bookingIntent.putExtra("hotel_image", getIntent().getStringExtra("hotel_image"));
                bookingIntent.putExtra("room_type", selectedRoom.getType());
                bookingIntent.putExtra("total_price", selectedRoom.getPrice());
                startActivity(bookingIntent);
            }
        });
    }
}

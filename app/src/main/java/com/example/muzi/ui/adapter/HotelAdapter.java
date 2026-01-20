package com.example.muzi.ui.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.muzi.R;
import com.example.muzi.data.model.Hotel;
import com.example.muzi.hotel.HotelDetailActivity;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> hotelList;

    public HotelAdapter(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel_card, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.tvName.setText(hotel.getName());
        holder.tvAddress.setText(hotel.getAddress());
        String formattedPrice = String.format("%,.0f", hotel.getPrice()).replace(',', '.') + " VNĐ";
        holder.tvPrice.setText(formattedPrice);
        
        // Find the per night text and update it to VNĐ/đêm
        // Since it's in a separate TextView in the layout (sometimes), let's be careful.
        // Actually, in item_hotel_card.xml, we have fixed strings or IDs.
        // Let's check item_hotel_card.xml again.

        // Load image from assets
        if (hotel.getImagePath() != null) {
            try {
                InputStream is = holder.itemView.getContext().getAssets().open(hotel.getImagePath());
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                holder.ivHotelImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                holder.ivHotelImage.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HotelDetailActivity.class);
            intent.putExtra("hotel_name", hotel.getName());
            intent.putExtra("hotel_address", hotel.getAddress());
            intent.putExtra("hotel_image", hotel.getImagePath());
            intent.putExtra("hotel_description", hotel.getDescription());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvPrice;
        ImageView ivHotelImage;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvHotelName);
            tvAddress = itemView.findViewById(R.id.tvHotelAddress);
            tvPrice = itemView.findViewById(R.id.tvHotelPrice);
            ivHotelImage = itemView.findViewById(R.id.ivHotelImage);
        }
    }
}

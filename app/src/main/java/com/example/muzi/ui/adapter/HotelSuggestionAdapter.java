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

public class HotelSuggestionAdapter extends RecyclerView.Adapter<HotelSuggestionAdapter.SuggestionViewHolder> {

    private List<Hotel> hotelList;

    public HotelSuggestionAdapter(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel_suggestion, parent, false);
        return new SuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.tvName.setText(hotel.getName());
        holder.tvAddress.setText(hotel.getAddress());
        String formattedPrice = String.format("%,.0f", hotel.getPrice()).replace(',', '.') + " VNĐ";
        holder.tvPrice.setText(formattedPrice);
        holder.tvRating.setText("★ " + hotel.getRating());
        holder.tvAmenities.setText(hotel.getAmenities());

        if (hotel.getImagePath() != null) {
            try {
                InputStream is = holder.itemView.getContext().getAssets().open(hotel.getImagePath());
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                holder.ivImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                holder.ivImage.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HotelDetailActivity.class);
            intent.putExtra("hotel_name", hotel.getName());
            intent.putExtra("hotel_address", hotel.getAddress());
            intent.putExtra("hotel_image", hotel.getImagePath());
            intent.putExtra("hotel_description", hotel.getDescription());
            intent.putExtra("hotel_rating", hotel.getRating());
            intent.putExtra("hotel_amenities", hotel.getAmenities());
            intent.putExtra("hotel_price", hotel.getPrice());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    static class SuggestionViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvPrice, tvRating, tvAmenities;
        ImageView ivImage;

        public SuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvSuggestName);
            tvAddress = itemView.findViewById(R.id.tvSuggestAddress);
            tvPrice = itemView.findViewById(R.id.tvSuggestPrice);
            ivImage = itemView.findViewById(R.id.ivSuggestImage);
            tvRating = itemView.findViewById(R.id.tvSuggestRating);
            tvAmenities = itemView.findViewById(R.id.tvSuggestAmenities);
        }
    }
}

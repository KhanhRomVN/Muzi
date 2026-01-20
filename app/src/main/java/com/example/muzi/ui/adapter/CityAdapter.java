package com.example.muzi.ui.adapter;

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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<String> cities;
    private List<String> imagePaths;

    public CityAdapter(List<String> cities, List<String> imagePaths) {
        this.cities = cities;
        this.imagePaths = imagePaths;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city_card, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.tvName.setText(cities.get(position));
        
        if (imagePaths != null && position < imagePaths.size()) {
            try {
                InputStream is = holder.itemView.getContext().getAssets().open(imagePaths.get(position));
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                holder.ivImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivImage;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCityName);
            ivImage = itemView.findViewById(R.id.imgCity);
        }
    }
}

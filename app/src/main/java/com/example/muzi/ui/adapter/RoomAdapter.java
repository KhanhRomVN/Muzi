package com.example.muzi.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.IOException;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.muzi.R;
import com.example.muzi.data.model.Room;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> roomList;
    private OnRoomSelectedListener listener;
    private int selectedPosition = -1;

    public interface OnRoomSelectedListener {
        void onRoomSelected(Room room);
    }

    public RoomAdapter(List<Room> roomList, OnRoomSelectedListener listener) {
        this.roomList = roomList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.tvType.setText(room.getType());
        holder.tvNumber.setText("Phòng " + room.getRoomNumber());
        String formattedPrice = String.format("%,.0f", room.getPrice()).replace(',', '.') + " VNĐ / đêm";
        holder.tvPrice.setText(formattedPrice);
        holder.tvRating.setText("4.9 (124)");
        holder.tvBedding.setText(room.getBeddingInfo());
        
        // Load Image
        if (room.getImagePath() != null) {
            try {
                InputStream is = holder.itemView.getContext().getAssets().open(room.getImagePath());
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                holder.ivRoomImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (position == selectedPosition) {
            holder.cardRoom.setCardBackgroundColor(0xFFE0F2F1); // Light primary
            holder.tvType.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.primary));
            // Keep other text colors as default or set specifically if needed
            holder.tvNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black)); // Assuming default black
            holder.tvPrice.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.primary)); // Assuming primary for price
        } else {
            holder.cardRoom.setCardBackgroundColor(android.graphics.Color.TRANSPARENT);
            holder.tvType.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            // Keep other text colors as default or set specifically if needed
            holder.tvNumber.setTextColor(0xFF757575); // Assuming default grey
            holder.tvPrice.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.primary)); // Assuming primary for price
        }

        holder.itemView.setOnClickListener(v -> {
            int previousSelected = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(previousSelected);
            notifyItemChanged(selectedPosition);
            if (listener != null) {
                listener.onRoomSelected(room);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    static class RoomViewHolder extends RecyclerView.ViewHolder {
        CardView cardRoom;
        TextView tvType, tvNumber, tvPrice, tvRating, tvBedding;
        ImageView ivRoomImage;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            cardRoom = itemView.findViewById(R.id.cardRoom);
            tvType = itemView.findViewById(R.id.tvRoomType);
            tvNumber = itemView.findViewById(R.id.tvRoomNumber);
            tvPrice = itemView.findViewById(R.id.tvRoomPrice);
            tvRating = itemView.findViewById(R.id.tvRoomRating);
            tvBedding = itemView.findViewById(R.id.tvRoomBedding);
            ivRoomImage = itemView.findViewById(R.id.ivRoomImage);
        }
    }
}

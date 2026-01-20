package com.example.muzi.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.muzi.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.muzi.data.model.Hotel;
import com.example.muzi.ui.adapter.HotelAdapter;
import java.util.ArrayList;
import java.util.List;

public class BookingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        RecyclerView rvBookings = view.findViewById(R.id.rvBookings);
        rvBookings.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Hotel> bookedHotels = new ArrayList<>();
        bookedHotels.add(new Hotel("1", "Khách sạn Hoàng Gia", "Việt Nam, Hà Nội", 
            "Trải nghiệm sự sang trọng bậc nhất tại Khách sạn Hoàng Gia với tầm nhìn hướng biển tuyệt đẹp và tiện nghi cao cấp.", 
            "o1", "hotels/hotel1.png", 2000000.0, 4.9f, "4 giường • 2 phòng tắm"));
        bookedHotels.add(new Hotel("2", "Mường Thanh Luxury", "Việt Nam, Đà Nẵng", 
            "Khách sạn đẳng cấp 5 sao tọa lạc ngay bên bờ biển Mỹ Khê xinh đẹp.", 
            "o2", "hotels/hotel2.png", 3500000.0, 4.8f, "3 giường • 2 phòng tắm"));

        HotelAdapter adapter = new HotelAdapter(bookedHotels);
        rvBookings.setAdapter(adapter);

        return view;
    }
}

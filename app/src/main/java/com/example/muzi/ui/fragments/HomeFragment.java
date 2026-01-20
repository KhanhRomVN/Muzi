package com.example.muzi.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.muzi.R;
import com.example.muzi.data.model.Hotel;
import com.example.muzi.ui.adapter.CityAdapter;
import com.example.muzi.ui.adapter.HotelAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 1. Setup City RecyclerView (Horizontal)
        RecyclerView rvCities = view.findViewById(R.id.rvCities);
        rvCities.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        
        List<String> cityNames = new ArrayList<>();
        cityNames.add("Hồ Chí Minh");
        cityNames.add("Đà Nẵng");
        cityNames.add("Hà Nội");
        
        List<String> cityCounts = new ArrayList<>();
        cityCounts.add("450+");
        cityCounts.add("200+");
        cityCounts.add("150+");

        CityAdapter cityAdapter = new CityAdapter(cityNames, cityCounts);
        rvCities.setAdapter(cityAdapter);

        // 2. Setup Hotel RecyclerView (Vertical)
        RecyclerView rvHotels = view.findViewById(R.id.rvHotels);
        rvHotels.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("1", "Khách sạn Aston Vill", "Quận 1, TP. Hồ Chí Minh", 
            "Trải nghiệm sự sang trọng tại Aston Vill với tầm nhìn hướng biển tuyệt đẹp và tiện nghi cao cấp.", 
            "o1", "hotels/hotel1.png", 200.0));
        hotels.add(new Hotel("2", "Resort Golden Sands", "Mỹ Khê, Đà Nẵng", 
            "Tận hưởng nắng vàng và biển xanh tại resort nghỉ dưỡng ven biển tuyệt đẹp với bãi tắm riêng.", 
            "o2", "hotels/hotel2.png", 350.0));
        hotels.add(new Hotel("3", "Mountain View Lodge", "Sapa, Lào Cai", 
            "Điểm dừng chân ấm cúng giữa lòng núi rừng, hoàn hảo cho những người yêu thiên nhiên và khí hậu núi cao.", 
            "o3", "hotels/hotel3.png", 180.0));
        
        HotelAdapter hotelAdapter = new HotelAdapter(hotels);
        rvHotels.setAdapter(hotelAdapter);

        return view;
    }
}

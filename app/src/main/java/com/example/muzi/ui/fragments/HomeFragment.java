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
import com.example.muzi.ui.adapter.HotelSuggestionAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // 1. Setup Nearby Hotels (Horizontal)
        RecyclerView rvNearby = view.findViewById(R.id.rvNearbyHotels);
        rvNearby.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        
        List<Hotel> nearbyHotels = new ArrayList<>();
        nearbyHotels.add(new Hotel("1", "Khách sạn Hoàng Gia", "Việt Nam, Hà Nội", 
            "Trải nghiệm sự sang trọng bậc nhất tại Khách sạn Hoàng Gia với tầm nhìn hướng biển tuyệt đẹp và tiện nghi cao cấp.", 
            "o1", "hotels/hotel1.png", 2000000.0, 4.9f, "4 giường • 2 phòng tắm"));
        
        HotelAdapter nearbyAdapter = new HotelAdapter(nearbyHotels);
        rvNearby.setAdapter(nearbyAdapter);

        // 2. Setup Region Hotels (Horizontal)
        RecyclerView rvRegions = view.findViewById(R.id.rvRegionHotels);
        rvRegions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        
        List<String> regionNames = new ArrayList<>();
        regionNames.add("Hà Nội");
        regionNames.add("Hải Phòng");
        regionNames.add("Hồ Chí Minh");
        regionNames.add("Vũng Tàu");
        regionNames.add("Đà Lạt");

        List<String> regionImages = new ArrayList<>();
        regionImages.add("cities/hanoi.png");
        regionImages.add("cities/haiphong.png");
        regionImages.add("cities/hcmc.png");
        regionImages.add("cities/vungtau.png");
        regionImages.add("cities/dalat.png");
        
        CityAdapter regionAdapter = new CityAdapter(regionNames, regionImages);
        rvRegions.setAdapter(regionAdapter);

        // 3. Setup Suggestions (Vertical)
        RecyclerView rvSuggestions = view.findViewById(R.id.rvSuggestions);
        rvSuggestions.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Hotel> suggestions = new ArrayList<>();
        suggestions.add(new Hotel("3", "Nhà nghỉ Tầm Nhìn Núi", "Sapa, Lào Cai", 
            "Điểm dừng chân ấm cúng giữa lòng núi rừng, hoàn hảo cho những người yêu thiên nhiên và khí hậu núi cao.", 
            "o3", "hotels/hotel3.png", 1800000.0, 4.7f, "2 giường • 1 phòng tắm"));
        suggestions.add(new Hotel("4", "Khách sạn Gió Biển", "Nha Trang, Khánh Hòa", 
            "Khách sạn hiện đại sát biển với hồ bơi vô cực và nhà hàng hải sản cao cấp.", 
            "o4", "hotels/hotel1.png", 1500000.0, 4.5f, "3 giường • 2 phòng tắm"));
        suggestions.add(new Hotel("5", "Khách sạn Phố Cổ", "Phố Cổ Hội An", 
            "Nét cổ kính hòa quyện cùng sự tiện nghi ngay giữa lòng phố cổ Hội An.", 
            "o5", "hotels/hotel2.png", 1200000.0, 4.3f, "2 giường • 1 phòng tắm"));
        
        HotelSuggestionAdapter suggestionAdapter = new HotelSuggestionAdapter(suggestions);
        rvSuggestions.setAdapter(suggestionAdapter);

        return view;
    }
}

package com.example.muzi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.muzi.ui.fragments.BookingFragment;
import com.example.muzi.ui.fragments.HomeFragment;
import com.example.muzi.ui.fragments.ManagementFragment;
import com.example.muzi.ui.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setItemActiveIndicatorEnabled(false);
        
        // Handle tab selection from Intent
        int tabToSelect = getIntent().getIntExtra("select_tab", R.id.nav_home);
        bottomNav.setSelectedItemId(tabToSelect);
        
        if (tabToSelect == R.id.nav_home) {
            loadFragment(new HomeFragment());
        } else if (tabToSelect == R.id.nav_management) {
            loadFragment(new ManagementFragment());
        } else if (tabToSelect == R.id.nav_booking) {
            loadFragment(new BookingFragment());
        } else if (tabToSelect == R.id.nav_settings) {
            loadFragment(new SettingsFragment());
        }

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Fragment fragment = null;
            
            if (itemId == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (itemId == R.id.nav_management) {
                fragment = new ManagementFragment();
            } else if (itemId == R.id.nav_booking) {
                fragment = new BookingFragment();
            } else if (itemId == R.id.nav_settings) {
                fragment = new SettingsFragment();
            }

            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}

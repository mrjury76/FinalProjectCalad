package com.example.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Menu_Adapter extends FragmentStateAdapter {
    public Menu_Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {

            case 0: {
                return new Calorie();
            }
            case 1: {
                Homepage homepageFragment = new Homepage();
                return homepageFragment;
            }
            case 2: {
                CalendarFragment calendarFragment = new CalendarFragment();
                return calendarFragment;
            }
            case 3: {
                List listFragment = new List();
                return listFragment;
            }

            default: {
                return null;
            }
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}


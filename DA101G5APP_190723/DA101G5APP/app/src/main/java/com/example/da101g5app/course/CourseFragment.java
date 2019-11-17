package com.example.da101g5app.course;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.da101g5app.R;
import com.example.da101g5app.main.Page;

import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course, container, false);

        ViewPager viewPager = view.findViewById(R.id.course_fm_viewPager);
        viewPager.setAdapter(new CourseFragment.MyPagerAdapter(getFragmentManager()));

        TabLayout tabLayout = view.findViewById(R.id.course_fm_tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        List<Page> pageList;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            pageList = new ArrayList<>();
            pageList.add(new Page(new CourseFragmentTabCalendar(), "行事曆"));
            pageList.add(new Page(new CourseFragmentTabAppointmentRecord(), "預約清單"));
        }

        @Override
        public Fragment getItem(int position) {
            return pageList.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageList.get(position).getTitle();
        }
    }
}

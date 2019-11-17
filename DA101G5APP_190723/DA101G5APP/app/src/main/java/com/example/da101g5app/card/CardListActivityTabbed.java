package com.example.da101g5app.card;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.da101g5app.R;
import com.example.da101g5app.main.Page;
import com.example.da101g5app.task.CommonTask;

import java.util.ArrayList;
import java.util.List;

public class CardListActivityTabbed extends AppCompatActivity {
    private CommonTask getCardVOTask;
    private static final String TAG = "CardListActivity";
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private boolean isFABOpen;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_tabbed);

        ViewPager viewPager = findViewById(R.id.card_list_activity_viewPager);
        viewPager.setAdapter(new CardListActivityTabbed.MyPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.card_list_activity_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        List<Page> pageList;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            pageList = new ArrayList<>();
            pageList.add(new Page(new CardListTabListAllFragment(), "所有單字"));
            pageList.add(new Page(new CardListTabListWrongOnesFragment(), "易錯單字"));
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
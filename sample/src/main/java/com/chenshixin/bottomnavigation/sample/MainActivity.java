package com.chenshixin.bottomnavigation.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.chenshixin.bottomnavigation.BottomNavigationBar;
import com.chenshixin.bottomnavigation.BottomNavigationItem;
import com.chenshixin.bottomnavigation.BottomNavigation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<BottomNavigationItem> tabItems = new ArrayList<>();
//        tabItems.add(new BottomNavigationItem("", 0, R.drawable.ic_tab_explore_b, R.drawable.ic_tab_explore_a));
//        tabItems.add(new BottomNavigationItem("", 2, R.drawable.ic_tab_news_b, R.drawable.ic_tab_news_a));
//        tabItems.add(new BottomNavigationItem("", 3, R.drawable.ic_tab_mine_b, R.drawable.ic_tab_mine_a));
        tabItems.add(new BottomNavigationItem("Explore", 0, R.drawable.ic_tab_explore_b, R.drawable.ic_tab_explore_a));
        tabItems.add(new BottomNavigationItem("News", 2, R.drawable.ic_tab_news_b, R.drawable.ic_tab_news_a));
        tabItems.add(new BottomNavigationItem("Mine", 3, R.drawable.ic_tab_mine_b, R.drawable.ic_tab_mine_a));

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(SimpleTextFragment.newInstance("Explore"));
        fragments.add(SimpleTextFragment.newInstance("News"));
        fragments.add(SimpleTextFragment.newInstance("Mine"));

        BottomNavigation bottomNavigation = (BottomNavigation) findViewById(R.id.bottom_navigation_bar_with_content);
        bottomNavigation.setTabItems(tabItems);
        bottomNavigation.setFragments(getSupportFragmentManager(), fragments);
        bottomNavigation.setTitleColorActive(Color.BLACK);
        bottomNavigation.setTitleColorInactive(Color.GRAY);
        bottomNavigation.initialise();
        bottomNavigation.setCurrentTab(0);
    }
}

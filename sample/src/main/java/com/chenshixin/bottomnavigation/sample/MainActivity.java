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
        tabItems.add(new BottomNavigationItem("Book", 0, R.drawable.ic_account_balance_wallet_black_24dp, R.drawable.ic_add_shopping_cart_black_24dp));
        tabItems.add(new BottomNavigationItem("Fav", 1, R.drawable.ic_add_shopping_cart_black_24dp, R.drawable.ic_alarm_black_24dp));
        tabItems.add(new BottomNavigationItem("White", 2, R.drawable.ic_alarm_black_24dp, R.drawable.ic_android_black_24dp));
        tabItems.add(new BottomNavigationItem("Link", 3, R.drawable.ic_android_black_24dp, R.drawable.ic_account_balance_wallet_black_24dp));
        tabItems.add(new BottomNavigationItem("Fav", 4, R.drawable.ic_add_shopping_cart_black_24dp, R.drawable.ic_alarm_black_24dp));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(SimpleTextFragment.newInstance("Book"));
        fragments.add(SimpleTextFragment.newInstance("Fav"));
        fragments.add(SimpleTextFragment.newInstance("White"));
        fragments.add(SimpleTextFragment.newInstance("Link"));
        fragments.add(SimpleTextFragment.newInstance("Fav"));

        BottomNavigation bottomNavigation = (BottomNavigation) findViewById(R.id.bottom_navigation_bar_with_content);
        bottomNavigation.setTabItems(tabItems);
        bottomNavigation.setFragments(getSupportFragmentManager(), fragments);
        bottomNavigation.setTitleColorActive(Color.BLUE);
        bottomNavigation.setTitleColorInactive(Color.RED);
        bottomNavigation.setOnTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public boolean onTabWillBeSelected(int position) {
                if (position == 1) {
                    Toast.makeText(MainActivity.this, "Need login first", Toast.LENGTH_SHORT).show();
                }
                return position != 1;
            }

            @Override
            public void onTabSelected(int position) {
//                Toast.makeText(MainActivity.this, position + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(int position) {
//                Toast.makeText(MainActivity.this, position + " onTabUnselected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(int position) {
                Toast.makeText(MainActivity.this, position + " 需要滚动到顶部", Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.initialise();
        bottomNavigation.setCurrentTab(0);
    }
}

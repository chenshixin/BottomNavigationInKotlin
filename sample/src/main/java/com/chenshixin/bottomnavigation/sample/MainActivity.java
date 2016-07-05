package com.chenshixin.bottomnavigation.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.chenshixin.bottomnavigation.BottomNavigation;
import com.chenshixin.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigation bottomNavigationBar = (BottomNavigation) findViewById(R.id.bottomNavigationBar);
        bottomNavigationBar.addItem(new BottomNavigationItem("Book", R.drawable.ic_account_balance_wallet_black_24dp, R.drawable.ic_add_shopping_cart_black_24dp));
        bottomNavigationBar.addItem(new BottomNavigationItem("Fav", R.drawable.ic_add_shopping_cart_black_24dp, R.drawable.ic_alarm_black_24dp));
        bottomNavigationBar.addItem(new BottomNavigationItem("White", R.drawable.ic_alarm_black_24dp, R.drawable.ic_android_black_24dp));
        bottomNavigationBar.addItem(new BottomNavigationItem("Link", R.drawable.ic_android_black_24dp, R.drawable.ic_account_balance_wallet_black_24dp));
        bottomNavigationBar.addItem(new BottomNavigationItem("Fav", R.drawable.ic_add_shopping_cart_black_24dp, R.drawable.ic_alarm_black_24dp));
        bottomNavigationBar.setTitleColorActive(Color.BLUE);
        bottomNavigationBar.setTitleColorInactive(Color.RED);
        bottomNavigationBar.setOnTabSelectedListener(new BottomNavigation.OnTabSelectedListener() {
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
        bottomNavigationBar.initialise();
    }
}

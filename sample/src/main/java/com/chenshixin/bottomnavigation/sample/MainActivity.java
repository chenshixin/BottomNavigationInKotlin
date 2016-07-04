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
        bottomNavigationBar.addItem(new BottomNavigationItem("001", R.drawable.icon111, R.drawable.icon222));
        bottomNavigationBar.addItem(new BottomNavigationItem("002", R.drawable.icon111, R.drawable.icon222));
        bottomNavigationBar.addItem(new BottomNavigationItem("003", R.drawable.icon111, R.drawable.icon222));
        bottomNavigationBar.addItem(new BottomNavigationItem("004", R.drawable.icon111, R.drawable.icon222));
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
                Toast.makeText(MainActivity.this, position + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(int position) {
                Toast.makeText(MainActivity.this, position + " onTabUnselected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(int position) {
                Toast.makeText(MainActivity.this, position + " onTabReselected", Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigationBar.initialise();
    }
}

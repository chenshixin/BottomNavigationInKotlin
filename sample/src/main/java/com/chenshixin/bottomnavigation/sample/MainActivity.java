package com.chenshixin.bottomnavigation.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        bottomNavigationBar.initialise();
    }
}

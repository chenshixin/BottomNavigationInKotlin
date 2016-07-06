# BottomNavigationInKotlin
Bottom navigation widget written using Kotlin language

#Features
* Basic bottom navigation features(icons, text, colors...)
* Interface to judge if to open a tab
* Fragment manage
* Badge view supoorted
* Double tap to scroll to top

#Installing
In your **root** `build.gradle`, add `maven { url "https://jitpack.io" }`
```gradle
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```
and in your own module:
```gradle
dependencies {
    compile 'com.github.chenshixin:BottomNavigationInKotlin:0.1'
}
```


#Screenshots
![](http://7xored.com1.z0.glb.clouddn.com/github_BottomNavigationInKotlin_screenshot.jpg?imageView2/2/w/300/q/75)

#Example in Java
```java
  List<BottomNavigationItem> tabItems = new ArrayList<>();
  tabItems.add(new BottomNavigationItem("Book", 0, R.drawable.ic_account_balance_wallet_black_24dp, R.drawable.ic_add_shopping_cart_black_24dp));
  tabItems.add(new BottomNavigationItem("Fav", 1, R.drawable.ic_add_shopping_cart_black_24dp, R.drawable.ic_alarm_black_24dp));
  tabItems.add(new BottomNavigationItem("White", 2, R.drawable.ic_alarm_black_24dp, R.drawable.ic_android_black_24dp));
  tabItems.add(new BottomNavigationItem("Link", 3, R.drawable.ic_android_black_24dp, R.drawable.ic_account_balance_wallet_black_24dp));
  tabItems.add(new BottomNavigationItem("Fav", 4, R.drawable.ic_add_shopping_cart_black_24dp, R.drawable.ic_alarm_black_24dp));

  final List<Fragment> fragments = new ArrayList<>();
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

      }

      @Override
      public void onTabUnselected(int position) {

      }

      @Override
      public void onTabReselected(int position) {

      }
  });
  bottomNavigation.initialise();
  bottomNavigation.setCurrentTab(0);
```



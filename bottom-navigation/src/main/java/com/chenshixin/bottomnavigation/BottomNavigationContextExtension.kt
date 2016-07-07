package com.chenshixin.bottomnavigation

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

/**
 * Created by chenshixin on 7/5/16.
 */
internal fun BottomNavigationBar.getItemWidth(): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val size = Point()
    wm.defaultDisplay.getSize(size)
    val screenWidth = size.x
    return screenWidth / items.count()
}
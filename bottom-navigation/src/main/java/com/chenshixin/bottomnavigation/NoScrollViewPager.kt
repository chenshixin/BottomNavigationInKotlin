package com.chenshixin.bottomnavigation

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * ViewPager without scroll between tabs
 * Created by chenshixin on 7/18/16.
 */
class NoScrollViewPager(context: Context, attributeSet: AttributeSet) : ViewPager(context, attributeSet) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}
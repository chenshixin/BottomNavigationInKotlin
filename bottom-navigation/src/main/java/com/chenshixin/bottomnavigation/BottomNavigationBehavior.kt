package com.chenshixin.bottomnavigation

import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.view.View

/**
 * Created by chenshixin on 7/5/16.
 */
class BottomNavigationBehavior: CoordinatorLayout.Behavior<BottomNavigationBar>() {

    companion object {
        val SCROLL_DIRECTION_UP = 1
        val SCROLL_DIRECTION_DOWN = -1
        val SCROLL_NONE = 0
    }

    private var totalDyConsumed = -1

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: BottomNavigationBar, dependency: View?): Boolean {
        return dependency?.id == R.id.bottom_navigation_bar_nested_scroll_view
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: BottomNavigationBar, directTargetChild: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout?, child: BottomNavigationBar, target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        if (dyConsumed > 0 && totalDyConsumed < 0) {
            totalDyConsumed = 0
            dealBar(child, SCROLL_DIRECTION_UP)
        } else if (dyConsumed < 0 && totalDyConsumed > 0) {
            totalDyConsumed = 0
            dealBar(child, SCROLL_DIRECTION_DOWN)
        }
        totalDyConsumed += dyConsumed
    }

    private fun dealBar(bar: BottomNavigationBar, consumedScrollDirection: Int) {
        if (consumedScrollDirection == SCROLL_DIRECTION_DOWN && bar.isHidden) {
            bar.show()
        } else if (consumedScrollDirection == SCROLL_DIRECTION_UP && !bar.isHidden) {
            bar.hide()
        }
    }


}
package com.chenshixin.bottomnavigation

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

/**
 * Created by chenshixin on 7/5/16.
 */
class BottomNavigationBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<BottomNavigationBar>(context, attrs) {

    constructor() : this(null, null)

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: BottomNavigationBar, dependency: View?): Boolean {
        return dependency?.id == R.id.bottom_navigation_bar_content
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout?, child: BottomNavigationBar, target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        offset(child, dyConsumed)
    }

    private fun offset(bar: BottomNavigationBar, dyConsumed: Int) {
        if (dyConsumed > 0) {
            bar.visibility = View.VISIBLE
        } else {
            bar.visibility = View.GONE
        }
    }
}
package com.chenshixin.bottomnavigation

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View

/**
 * Created by chenshixin on 7/5/16.
 */
class BottomNavigationBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<BottomNavigationBar>(context, attrs) {

    constructor() : this(null, null)

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: BottomNavigationBar, dependency: View?): Boolean {
        return dependency?.id == R.id.bottom_navigation_bar_nested_scroll_view
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: BottomNavigationBar?, directTargetChild: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout?, child: BottomNavigationBar?, target: View?, dx: Int, dy: Int, consumed: IntArray?) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed)
        if (child == null) return
        if (dy > child.height && child.visibility == View.VISIBLE) {
            hideBar(child)
        } else if (dy < (0 - child.height) && child.visibility == View.GONE) {
            showBar(child)
        }
    }

    private fun hideBar(bar: BottomNavigationBar) {
        bar.visibility == View.GONE
    }

    private fun showBar(bar: BottomNavigationBar) {
        bar.visibility == View.VISIBLE
    }


}
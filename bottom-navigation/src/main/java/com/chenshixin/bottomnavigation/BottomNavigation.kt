package com.chenshixin.bottomnavigation

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.bottom_navigation.view.*

/**
 * Created by chenshixin on 7/5/16.
 */
class BottomNavigation(context: Context?, attrs: AttributeSet?) : CoordinatorLayout(context, attrs) {

    var fragmentChangeManager: FragmentChangManager? = null

    var onTabSelectedListener: BottomNavigationBar.OnTabSelectedListener? = null

    /**
     * Inactive title color res id
     */
    var titleColorInactive: Int?
        set(value) {
            bottom_navigation_bar.titleColorActive = value
        }
        get() = bottom_navigation_bar.titleColorActive

    /**
     * Active title color res id
     */
    var titleColorActive: Int?
        set(value) {
            bottom_navigation_bar.titleColorInactive = value
        }
        get() = bottom_navigation_bar.titleColorInactive

    var currentTab: Int
        get() = bottom_navigation_bar.selectedPosition
        set(value) {
            fragmentChangeManager?.currentTab = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation, this, true)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    fun setFragments(fragmentManager: FragmentManager, fragments: List<Fragment>) {
        fragmentChangeManager = FragmentChangManager(fragmentManager, R.id.bottom_navigation_bar_content, fragments)
    }

    fun setTabItems(tabs: List<BottomNavigationItem>) {
        tabs.map { item ->
            bottom_navigation_bar.addItem(item)
        }
    }

    fun initialise() {
        bottom_navigation_bar.initialise()
        bottom_navigation_bar.onTabSelectedListener = object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabWillBeSelected(position: Int): Boolean {
                return onTabSelectedListener?.onTabWillBeSelected(position) ?: true
            }

            override fun onTabSelected(position: Int) {
                this@BottomNavigation.currentTab = position
                onTabSelectedListener?.onTabSelected(position)
            }

            override fun onTabUnselected(position: Int) {
                onTabSelectedListener?.onTabUnselected(position)
            }

            override fun onTabReselected(position: Int) {
                val fragment = fragmentChangeManager!!.fragments[position]
                if (fragment is BottomNavigationBar.DoubleTapToScrollTop) {
                    fragment.scrollToTop()
                }
                onTabSelectedListener?.onTabReselected(position)
            }
        }
    }

    fun setItemBadge(index: Int, number: Int) {
        bottom_navigation_bar.tabs[index].setBadgeNumber(number)
    }

    fun setItemTitle(index: Int, title: String) {
        bottom_navigation_bar.tabs[index].setTitle(title)
    }

    fun setItemActiveIcon(index: Int, resId: Int) {
        bottom_navigation_bar.tabs[index].setActiveIcon(resId)
    }

    fun setItemInActiveIcon(index: Int, resId: Int) {
        bottom_navigation_bar.tabs[index].setInActiveIcon(resId)
    }

}
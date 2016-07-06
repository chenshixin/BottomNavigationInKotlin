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

    val bottomNavigationBar by lazy { bottom_navigation_bar }

    var fragmentChangeManager: FragmentChangManager? = null

    var onTabSelectedListener: BottomNavigationBar.OnTabSelectedListener?
        set(value) {
            bottomNavigationBar.onTabSelectedListener = object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabWillBeSelected(position: Int): Boolean {
                    return value?.onTabWillBeSelected(position) ?: true
                }

                override fun onTabSelected(position: Int) {
                    this@BottomNavigation.currentTab = position
                    value?.onTabSelected(position)
                }

                override fun onTabUnselected(position: Int) {
                    value?.onTabUnselected(position)
                }

                override fun onTabReselected(position: Int) {
                    value?.onTabReselected(position)
                }
            }
        }
        get() = bottomNavigationBar.onTabSelectedListener

    /**
     * Inactive title color res id
     */
    var titleColorInactive: Int?
        set(value) {
            bottomNavigationBar.titleColorActive = value
        }
        get() = bottomNavigationBar.titleColorActive

    /**
     * Active title color res id
     */
    var titleColorActive: Int?
        set(value) {
            bottomNavigationBar.titleColorInactive = value
        }
        get() = bottomNavigationBar.titleColorInactive

    var currentTab: Int
        get() = bottomNavigationBar.selectedPosition
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
            bottomNavigationBar.addItem(item)
        }
    }

    fun initialise() {
        bottomNavigationBar.initialise()
    }


}
package com.chenshixin.bottomnavigation

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.bottom_navigation_bar.view.*
import java.util.*

/**
 * Bottom Navigation Bar
 * Created by chenshixin on 7/4/16.
 */
class BottomNavigation(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    val items: ArrayList<BottomNavigationItem> = arrayListOf()
    val tabs: ArrayList<BottomNavigationTab> = arrayListOf()

    var backgroundColor: Int? = null
    var onTabSelectedListener: OnTabSelectedListener? = null
    var selectedPosition = 0

    /**
     * Inactive title color res id
     */
    var titleColorInactive: Int? = null

    /**
     * Active title color res id1
     */
    var titleColorActive: Int? = null

    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_bar, this, true)
    }

    fun initialise() {
        if (items.isEmpty()) {
            return
        }
        //TODO parse xml here
        checkNotNull(titleColorActive)
        checkNotNull(titleColorInactive)
        bottom_navigation_bar_item_container.removeAllViews()
        bottom_navigation_bar_item_container.setBackgroundColor(backgroundColor ?: Color.WHITE)
        items.mapIndexed { index, item ->
            val tab = BottomNavigationTab(item, titleColorInactive!!, titleColorActive!!, context)
            tab.position = index
            tab.setOnClickListener { view ->
                val newPosition = (view as BottomNavigationTab).position
                if (onTabSelectedListener != null && onTabSelectedListener?.onTabWillBeSelected(newPosition) ?: false) {
                    if (selectedPosition != newPosition) {
                        tabs[selectedPosition].unSelect()
                        tabs[newPosition].select()
                        if (selectedPosition == newPosition) {
                            onTabSelectedListener?.onTabReselected(newPosition)
                        } else {
                            onTabSelectedListener?.onTabSelected(newPosition)
                            onTabSelectedListener?.onTabUnselected(selectedPosition)
                        }
                        selectedPosition = newPosition

                    }
                }
            }
            tabs.add(tab)
            bottom_navigation_bar_item_container.addView(tab)
        }
    }

    /**
     * Add an item to current bar
     */
    fun addItem(item: BottomNavigationItem): BottomNavigation {
        items.add(item)
        return this
    }

    /**
     * Remove an item form current bar
     */
    fun removeItem(item: BottomNavigationItem): BottomNavigation {
        items.remove(item)
        return this
    }

    interface OnTabSelectedListener {

        fun onTabWillBeSelected(position: Int): Boolean

        fun onTabSelected(position: Int)

        fun onTabUnselected(position: Int)

        fun onTabReselected(position: Int)
    }

}
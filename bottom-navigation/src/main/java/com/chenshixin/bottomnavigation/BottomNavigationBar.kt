package com.chenshixin.bottomnavigation

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.LinearOutSlowInInterpolator
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
@CoordinatorLayout.DefaultBehavior(BottomNavigationBehavior::class)
class BottomNavigationBar(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

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

    var isHidden = false

    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_bar, this, true)
    }

    fun initialise() {
        if (items.isEmpty()) {
            return
        }
        //TODO parse xml here
        ViewCompat.setElevation(this, 24F)
        bottom_navigation_bar_item_container.removeAllViews()
        if (backgroundColor != null) {
            bottom_navigation_bar_item_container.setBackgroundColor(backgroundColor ?: Color.WHITE)
        }
        val tabWidth = getItemWidth()
        items.mapIndexed { index, item ->
            val tab = BottomNavigationTab(item, tabWidth, titleColorInactive, titleColorActive, context)
            tab.position = index
            tab.setOnClickListener { view ->
                val newPosition = (view as BottomNavigationTab).position
                if (onTabSelectedListener != null && onTabSelectedListener?.onTabWillBeSelected(newPosition) ?: false) {
                    if (selectedPosition != newPosition) {
                        tabs[selectedPosition].unSelect()
                        tabs[newPosition].select()
                        onTabSelectedListener?.onTabSelected(newPosition)
                        onTabSelectedListener?.onTabUnselected(selectedPosition)
                        selectedPosition = newPosition
                    } else {
                        onTabSelectedListener?.onTabReselected(newPosition)
                    }
                }
            }
            tabs.add(tab)
            bottom_navigation_bar_item_container.addView(tab)
        }
    }

    internal fun setCurrentTab(index: Int) {
        tabs.mapIndexed { i, bottomNavigationTab ->
            if (i == index) {
                tabs[i].select()
            } else {
                tabs[i].unSelect()
            }
        }
        selectedPosition = index
    }

    /**
     * Add an item to current bar
     */
    fun addItem(item: BottomNavigationItem): BottomNavigationBar {
        items.add(item)
        return this
    }

    /**
     * Remove an item form current bar
     */
    fun removeItem(item: BottomNavigationItem): BottomNavigationBar {
        items.remove(item)
        return this
    }

    /**
     * Set background for badge
     */
    fun setBadgeBackground(drawable: Drawable) {
        tabs.map { tab ->
            tab.setBadgeBackground(drawable)
        }
    }

    interface OnTabSelectedListener {

        fun onTabWillBeSelected(position: Int): Boolean

        fun onTabSelected(position: Int)

        fun onTabUnselected(position: Int)

        fun onTabReselected(position: Int)
    }

    interface DoubleTapToScrollTop {
        fun scrollToTop()
    }

    internal fun show() {
        isHidden = false
        animateOffset(0)
    }

    internal fun hide() {
        isHidden = true
        animateOffset(this.height)
    }

    internal fun animateOffset(offset: Int) {
        val animator = ViewCompat.animate(this)
        animator.duration = 200
        animator.interpolator = LinearOutSlowInInterpolator()
        animator.translationY(offset.toFloat()).start()
    }

    internal fun setItemBadge(index: Int, number: Int) {
        tabs[index].setBadgeNumber(number)
    }

    internal fun setItemTitle(index: Int, title: String) {
        tabs[index].setTitle(title)
    }

    internal fun setItemActiveIcon(index: Int, resId: Int) {
        tabs[index].setActiveIcon(resId)
    }

    internal fun setItemInActiveIcon(index: Int, resId: Int) {
        tabs[index].setInActiveIcon(resId)
    }

}
package com.chenshixin.bottomnavigation

import android.animation.ValueAnimator
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.bottom_navigation_item.view.*

/**
 * Tab in Bottom Navigation Bar
 * Created by chenshixin on 7/4/16.
 */
class BottomNavigationTab(item: BottomNavigationItem, itemWidth: Int, titleColorInactive: Int, titleColorActive: Int, context: Context) :
        FrameLayout(context) {

    /**
     * The position of this tab in bar
     */
    var position: Int = 0

    /**
     * Is this tab active
     */
    var isActive: Boolean = false

    val bottomNavigationItem = item

    val topPaddingActive by lazy { resources.getDimension(R.dimen.tab_top_padding_active).toInt() }
    val topPaddingInactive by lazy { resources.getDimension(R.dimen.tab_top_padding_inactive).toInt() }

    val titleTextSizeActive by lazy { resources.getDimension(R.dimen.title_size_active) }
    val titleTextSizeInactive by lazy { resources.getDimension(R.dimen.title_size_inactive) }

    val selectAnimationDuration: Long = 200

    val titleColorInactive = titleColorInactive
    val titleColorActive = titleColorActive

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_item, this, true)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        params.width = itemWidth
        layoutParams = params
        setBadgeNumber(item.number)
        bottom_navigation_bar_icon.setImageResource(item.iconResIdInactive)
        bottom_navigation_bar_title.text = item.title
        bottom_navigation_bar_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSizeInactive)
        bottom_navigation_bar_title.setTextColor(titleColorInactive)
    }

    fun setBadgeNumber(number: Int) {
        if (number > 0) {
            bottom_navigation_bar_icon_badge.text = "$number"
            bottom_navigation_bar_icon_badge.visibility = View.VISIBLE
        } else {
            bottom_navigation_bar_icon_badge.visibility = View.GONE
        }
    }

    fun select() {
        isActive = true
        bottom_navigation_bar_icon.setImageResource(bottomNavigationItem.iconResIdActive)
        bottom_navigation_bar_title.setTextColor(titleColorActive)
        startPaddingAnimation(topPaddingInactive, topPaddingActive)
        startTextSizeAnimation(titleTextSizeInactive, titleTextSizeActive)
    }

    fun unSelect() {
        isActive = false
        bottom_navigation_bar_icon.setImageResource(bottomNavigationItem.iconResIdInactive)
        bottom_navigation_bar_title.setTextColor(titleColorInactive)
        startPaddingAnimation(topPaddingActive, topPaddingInactive)
        startTextSizeAnimation(titleTextSizeActive, titleTextSizeInactive)
    }

    private fun startPaddingAnimation(paddingStart: Int, paddingEnd: Int) {
        val animator = ValueAnimator.ofInt(paddingStart, paddingEnd)
        animator.addUpdateListener({ value ->
            bottom_navigation_bar_item.setPadding(bottom_navigation_bar_item.paddingLeft, value.animatedValue as Int,
                    bottom_navigation_bar_item.paddingRight, bottom_navigation_bar_item.paddingBottom)
        })
        animator.duration = selectAnimationDuration
        animator.start()
    }

    private fun startTextSizeAnimation(titleTextSizeStart: Float, titleTextSizeEnd: Float) {
        val animator = ValueAnimator.ofFloat(titleTextSizeStart.toFloat(), titleTextSizeEnd.toFloat())
        animator.addUpdateListener({ value ->
            bottom_navigation_bar_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, value.animatedValue as Float)
        })
        animator.duration = selectAnimationDuration
        animator.start()
    }

}
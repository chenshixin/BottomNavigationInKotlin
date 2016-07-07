package com.chenshixin.bottomnavigation

import android.animation.ValueAnimator
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.bottom_navigation_tab.view.*

/**
 * Tab in Bottom Navigation Bar
 * Created by chenshixin on 7/4/16.
 */
class BottomNavigationTab(item: BottomNavigationItem, itemWidth: Int, val titleColorInactive: Int?, val titleColorActive: Int?, context: Context) :
        FrameLayout(context) {

    /**
     * The position of this tab in bar
     */
    var position: Int = 0

    /**
     * Is this tab active
     */
    var isActive: Boolean = false

    var selectAnimationDuration: Long = 200

    val bottomNavigationItem = item

    val topPaddingActive by lazy { resources.getDimension(R.dimen.tab_top_padding_active).toInt() }
    val topPaddingInactive by lazy { resources.getDimension(R.dimen.tab_top_padding_inactive).toInt() }

    val titleTextSizeActive by lazy { resources.getDimension(R.dimen.title_size_active) }
    val titleTextSizeInactive by lazy { resources.getDimension(R.dimen.title_size_inactive) }

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_tab, this, true)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        params.width = itemWidth
        layoutParams = params
        setBadgeNumber(item.number)
        setTitle(item.title)
        setCurrentIcon(item.iconResIdInactive)
        bottom_navigation_bar_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSizeInactive)
        setCurrentTextColor(titleColorInactive)
    }

    internal fun setBadgeNumber(number: Int?) {
        if (number != null && number > 0) {
            bottom_navigation_bar_icon_badge.text = "$number"
            bottom_navigation_bar_icon_badge.visibility = View.VISIBLE
        } else {
            bottom_navigation_bar_icon_badge.visibility = View.GONE
        }
    }

    internal fun setTitle(title: String?) {
        val frameParams = bottom_navigation_bar_icon_frame.layoutParams as FrameLayout.LayoutParams
        val iconParams = bottom_navigation_bar_icon.layoutParams as FrameLayout.LayoutParams
        if (title?.isEmpty() ?: true) {
            bottom_navigation_bar_title.visibility = View.GONE
            frameParams.gravity = Gravity.CENTER
            iconParams.gravity = Gravity.CENTER
        } else {
            bottom_navigation_bar_title.visibility = View.VISIBLE
            bottom_navigation_bar_title.text = title
            frameParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            iconParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        }
        bottom_navigation_bar_icon_frame.layoutParams = frameParams
        bottom_navigation_bar_icon.layoutParams = iconParams
    }

    internal fun setInActiveIcon(resId: Int) {
        bottomNavigationItem.iconResIdInactive = resId
        if (!isActive) {
            setCurrentIcon(bottomNavigationItem.iconResIdActive)
        }
    }

    internal fun setActiveIcon(resId: Int) {
        bottomNavigationItem.iconResIdActive = resId
        if (isActive) {
            setCurrentIcon(bottomNavigationItem.iconResIdActive)
        }
    }

    internal fun select() {
        isActive = true
        setCurrentIcon(bottomNavigationItem.iconResIdActive)
        setCurrentTextColor(titleColorActive)
        startPaddingAnimation(topPaddingInactive, topPaddingActive)
        if (bottomNavigationItem.title?.isEmpty() ?: true) return
        startTextSizeAnimation(titleTextSizeInactive, titleTextSizeActive)
    }

    internal fun unSelect() {
        isActive = false
        setCurrentIcon(bottomNavigationItem.iconResIdInactive)
        setCurrentTextColor(titleColorInactive)
        startPaddingAnimation(topPaddingActive, topPaddingInactive)
        if (bottomNavigationItem.title?.isEmpty() ?: true) return
        startTextSizeAnimation(titleTextSizeActive, titleTextSizeInactive)
    }

    private fun startPaddingAnimation(paddingStart: Int, paddingEnd: Int) {
        if (paddingEnd == bottom_navigation_bar_item.paddingTop) {
            return
        }
        val animator = ValueAnimator.ofInt(paddingStart, paddingEnd)
        animator.addUpdateListener({ value ->
            bottom_navigation_bar_item.setPadding(bottom_navigation_bar_item.paddingLeft, value.animatedValue as Int,
                    bottom_navigation_bar_item.paddingRight, bottom_navigation_bar_item.paddingBottom)
        })
        animator.duration = selectAnimationDuration
        animator.start()
    }

    private fun startTextSizeAnimation(titleTextSizeStart: Float, titleTextSizeEnd: Float) {
        if (titleTextSizeEnd == bottom_navigation_bar_title.textSize) {
            return
        }
        val animator = ValueAnimator.ofFloat(titleTextSizeStart.toFloat(), titleTextSizeEnd.toFloat())
        animator.addUpdateListener({ value ->
            bottom_navigation_bar_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, value.animatedValue as Float)
        })
        animator.duration = selectAnimationDuration
        animator.start()
    }

    private fun setCurrentIcon(resId: Int) {
        bottom_navigation_bar_icon.setImageResource(resId)
    }

    private fun setCurrentTextColor(color: Int?) {
        if (color != null) {
            bottom_navigation_bar_title.setTextColor(color)
        }
    }

}
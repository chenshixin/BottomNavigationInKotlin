package com.chenshixin.bottomnavigation

/**
 * Items in Bottom Navigation Bar
 * @param title title for current item, shown below icon
 * @param iconResIdInactive inactive icon resource id
 * @param iconResIdActive active icon resource id
 * Created by chenshixin on 7/4/16.
 */
data class BottomNavigationItem(var title: String?,
                                var number: Int?,
                                var iconResIdInactive: Int,
                                var iconResIdActive: Int)
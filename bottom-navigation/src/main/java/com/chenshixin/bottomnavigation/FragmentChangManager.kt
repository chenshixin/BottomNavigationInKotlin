package com.chenshixin.bottomnavigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by chenshixin on 7/5/16.
 */
class FragmentChangManager(val fm: FragmentManager, val containerViewId: Int, val fragments: List<Fragment>) {

    private var mCurrentTab: Int = 0

    var currentTab: Int? = 0
        set(value) {
            fragments.mapIndexed { i, fragment ->
                val transaction = fm.beginTransaction()
                if (i == value) {
                    transaction.show(fragment)
                } else {
                    transaction.hide(fragment)
                }
                transaction.commit()
            }
            this.mCurrentTab = value ?: 0
        }

    init {
        fragments.map { fragment ->
            fm.beginTransaction().add(containerViewId, fragment).hide(fragment).commit()
        }
        currentTab = 0
    }

}
package com.bachan.eyepetizer_kotlin.entity

import com.flyco.tablayout.listener.CustomTabEntity

class TabEntity (private var title: String, private var selectedIcon: Int = 0, private var unSelectedIcon: Int = 0) :
    CustomTabEntity {
    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

}
package com.bachan.eyepetizer_kotlin.db

import com.bachan.eyepetizer_kotlin.ui.main.MainPageDao

object EyepetizerDatabase {
    private var mainPageDao: MainPageDao? = null

    fun getMainPageDao(): MainPageDao {
        if (mainPageDao == null) {
            mainPageDao = MainPageDao()
        }
        return mainPageDao!!
    }
}
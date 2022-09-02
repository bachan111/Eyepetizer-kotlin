package com.bachan.eyepetizer_kotlin.util

import com.bachan.eyepetizer_kotlin.db.EyepetizerDatabase
import com.bachan.eyepetizer_kotlin.network.EyepetizerNetwork
import com.bachan.eyepetizer_kotlin.ui.MainPageRepository
import com.bachan.eyepetizer_kotlin.ui.home.discovery.DiscoveryViewModelFactory

object InjectorUtil {
    private fun getMainPageRepository() = MainPageRepository.getInstance(EyepetizerDatabase.getMainPageDao(), EyepetizerNetwork.getInstance())

    fun getDiscoveryViewModelFactory() = DiscoveryViewModelFactory(getMainPageRepository())
}
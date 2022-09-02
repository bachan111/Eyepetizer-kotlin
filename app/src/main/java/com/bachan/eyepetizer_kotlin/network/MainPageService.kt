package com.bachan.eyepetizer_kotlin.network

import com.bachan.eyepetizer_kotlin.ui.home.discovery.model.Discovery

interface MainPageService {
    /**
     * 首页-发现列表
     */
    @GET
    fun getDiscovery(@Url url: String): Call<Discovery>
    companion object{
        /**
         * 首页-发现列表
         */
        const val DISCOVERY_URL = "${ServiceCreator.BASE_URL}api/v7/index/tab/discovery"
    }
}
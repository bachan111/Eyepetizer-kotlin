package com.bachan.eyepetizer_kotlin.ui

import com.bachan.eyepetizer_kotlin.network.EyepetizerNetwork
import com.bachan.eyepetizer_kotlin.ui.main.MainPageDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainPageRepository private constructor(private val mainPageDao: MainPageDao, private val eyepetizerNetwork: EyepetizerNetwork){

    suspend fun refreshDiscovery(url: String) = requestDiscovery(url)


    private suspend fun requestDiscovery(url: String) = withContext(Dispatchers.IO) {
        val response = eyepetizerNetwork.fetchDiscovery(url)
        mainPageDao.cacheDiscovery(response)
        response
    }
    companion object {

        private var repository: MainPageRepository? = null

        fun getInstance(dao: MainPageDao, network: EyepetizerNetwork): MainPageRepository {
            if (repository == null) {
                synchronized(MainPageRepository::class.java) {
                    if (repository == null) {
                        repository = MainPageRepository(dao, network)
                    }
                }
            }

            return repository!!
        }
    }
}
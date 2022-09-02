package com.bachan.eyepetizer_kotlin.ui.home.discovery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bachan.eyepetizer_kotlin.ui.MainPageRepository
import com.bachan.eyepetizer_kotlin.ui.home.discovery.model.Discovery
import com.bachan.eyepetizer_kotlin.network.MainPageService

class DiscoveryViewModel(repository: MainPageRepository): ViewModel() {
    var dataList = ArrayList<Discovery.Item>()
    private val requestParamLiveData = MutableLiveData<String>()
    var nextPageUrl: String? = null

    val dataListLiveData = Transformations.switchMap(requestParamLiveData) { url ->
        liveData {
            val resutlt = try {
                val discovery = repository.refreshDiscovery(url)
                Result.success(discovery)
            } catch (e: Exception) {
                Result.failure<Discovery>(e)
            }
            emit(resutlt)
        }
    }

    fun onRefresh(){
        requestParamLiveData.value = MainPageService.DISCOVERY_URL
    }

    fun onLoadMore() {
        requestParamLiveData.value = nextPageUrl ?: ""
    }
}
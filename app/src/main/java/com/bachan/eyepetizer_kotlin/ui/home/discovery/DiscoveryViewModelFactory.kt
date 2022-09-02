package com.bachan.eyepetizer_kotlin.ui.home.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bachan.eyepetizer_kotlin.ui.MainPageRepository

class DiscoveryViewModelFactory(private val repository: MainPageRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiscoveryViewModel(repository) as T
    }
}
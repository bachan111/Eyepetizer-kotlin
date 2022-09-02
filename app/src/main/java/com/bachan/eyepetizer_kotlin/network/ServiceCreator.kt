package com.bachan.eyepetizer_kotlin.network

object ServiceCreator {
    const val BASE_URL = "http://baobab.kaiyanapp.com/"
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}
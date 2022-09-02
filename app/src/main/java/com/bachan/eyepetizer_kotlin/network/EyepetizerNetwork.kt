package com.bachan.eyepetizer_kotlin.network

class EyepetizerNetwork {
    private val mainPageService = ServiceCreator.create(MainPageService::class.java)
    suspend fun fetchDiscovery(url: String) = mainPageService.getDiscovery(url).await()
    companion object {

        private var network: EyepetizerNetwork? = null

        fun getInstance(): EyepetizerNetwork {
            if (network == null) {
                synchronized(EyepetizerNetwork::class.java) {
                    if (network == null) {
                        network = EyepetizerNetwork()
                    }
                }
            }
            return network!!
        }
    }
}
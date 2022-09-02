package com.bachan.eyepetizer_kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bachan.eyepetizer_kotlin.R
import com.bachan.eyepetizer_kotlin.base.BaseFragment
import com.bachan.eyepetizer_kotlin.event.MessageEvent
import com.bachan.eyepetizer_kotlin.util.GlobalUtil

class DiscoveryFragment:BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_refresh_layout, container, false))
    }

    override fun loadDataOnce() {
        super.loadDataOnce()
        startLoading()
    }

    override fun startLoading() {
        super.startLoading()
    }

    override fun loadFailed(msg: String?) {
        super.loadFailed(msg)
        showLoadErrorView(msg ?: GlobalUtil.getString(R.string.unknown_error)) { startLoading() }
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
    }

    companion object {
        fun newInstance() = DiscoveryFragment()
    }
}
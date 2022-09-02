package com.bachan.eyepetizer_kotlin.ui.home.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bachan.eyepetizer_kotlin.R
import com.bachan.eyepetizer_kotlin.base.BaseFragment
import com.bachan.eyepetizer_kotlin.event.MessageEvent
import com.bachan.eyepetizer_kotlin.util.GlobalUtil
import com.bachan.eyepetizer_kotlin.util.InjectorUtil
import kotlinx.android.synthetic.main.fragment_refresh_layout.*

class DiscoveryFragment:BaseFragment() {

    private val viewModel by lazy { ViewModelProvider(this,InjectorUtil.getDiscoveryViewModelFactory()).get(DiscoveryViewModel::class.java) }
    private lateinit var adapter: DiscoveryAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_refresh_layout, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = DiscoveryAdapter(this, viewModel.dataList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = null
        refreshLayout.setOnRefreshListener{
            viewModel.onRefresh()
        }
        refreshLayout.setOnLoadMoreListener{
            viewModel.onLoadMore()
        }

        observe()
    }

    private fun observe() {
        viewModel.dataListLiveData.observe(viewLifecycleOwner, Observer { result ->
            val response = result.getOrNull()
            if (response == null){
                return@Observer
            }
            loadFinished()
        })
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
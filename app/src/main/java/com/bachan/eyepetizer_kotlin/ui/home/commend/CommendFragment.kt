package com.bachan.eyepetizer_kotlin.ui.home.commend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bachan.eyepetizer_kotlin.R
import com.bachan.eyepetizer_kotlin.base.BaseFragment

class CommendFragment:BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_refresh_layout, container, false))
    }

    companion object{
        fun newInstance() = CommendFragment()
    }
}
package com.bachan.eyepetizer_kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bachan.eyepetizer_kotlin.R
import com.bachan.eyepetizer_kotlin.base.BaseViewPagerFragment
import com.bachan.eyepetizer_kotlin.entity.TabEntity
import com.bachan.eyepetizer_kotlin.event.MessageEvent
import com.bachan.eyepetizer_kotlin.util.GlobalUtil
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.synthetic.main.layout_main_page_title_bar.*

class HomeFragment:BaseViewPagerFragment() {
    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.discovery)))
    }
    override val createFragments: Array<Fragment> = arrayOf(DiscoveryFragment.newInstance())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_main_container,container,false))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ivCalendar.visibility = View.VISIBLE
        viewPager?.currentItem = 1
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}
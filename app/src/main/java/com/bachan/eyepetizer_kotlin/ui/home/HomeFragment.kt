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
import com.bachan.eyepetizer_kotlin.event.RefreshEvent
import com.bachan.eyepetizer_kotlin.ui.home.commend.CommendFragment
import com.bachan.eyepetizer_kotlin.ui.home.daily.DailyFragment
import com.bachan.eyepetizer_kotlin.ui.home.discovery.DiscoveryFragment
import com.bachan.eyepetizer_kotlin.util.GlobalUtil
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.synthetic.main.layout_main_page_title_bar.*
import org.greenrobot.eventbus.EventBus

class HomeFragment:BaseViewPagerFragment() {
    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.discovery)))
        add(TabEntity(GlobalUtil.getString(R.string.commend)))
        add(TabEntity(GlobalUtil.getString(R.string.daily)))
    }
    override val createFragments: Array<Fragment> = arrayOf(DiscoveryFragment.newInstance(),
        CommendFragment.newInstance(),DailyFragment.newInstance())


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
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(DiscoveryFragment::class.java))
                1 -> EventBus.getDefault().post(RefreshEvent(CommendFragment::class.java))
                2 -> EventBus.getDefault().post(RefreshEvent(DailyFragment::class.java))
                else -> {
                }
            }
        }
//        else if (messageEvent is SwitchPagesEvent) {
//            when (messageEvent.activityClass) {
//                DiscoveryFragment::class.java -> viewPager?.currentItem = 0
//                CommendFragment::class.java -> viewPager?.currentItem = 1
//                DailyFragment::class.java -> viewPager?.currentItem = 2
//                else -> {
//                }
//            }
//        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}
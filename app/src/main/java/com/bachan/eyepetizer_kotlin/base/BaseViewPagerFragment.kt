package com.bachan.eyepetizer_kotlin.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bachan.eyepetizer_kotlin.R
import com.bachan.eyepetizer_kotlin.extension.setOnClickListener
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.layout_main_page_title_bar.*

abstract class BaseViewPagerFragment :BaseFragment() {
    protected var viewPager: ViewPager2? = null

    protected var tabLayout: CommonTabLayout? = null

    protected var pageChangeCallback: PageChangeCallback? = null

    protected val adapter: VpAdapter by lazy { VpAdapter(requireActivity()).apply { addFragments(createFragments) } }

    protected var offscreenPageLimit = 1

    abstract val createTitles: ArrayList<CustomTabEntity>

    abstract val createFragments: Array<Fragment>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        initVeiwPager()
        setOnClickListener(ivCalendar, ivSearch) {
            if (this == ivCalendar) {
//                R.string.currently_not_supported.showToast()
            } else if (this == ivSearch) {
//                SearchFragment.switchFragment(activity)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pageChangeCallback?.run { viewPager?.unregisterOnPageChangeCallback(this) }
        pageChangeCallback = null
    }

    protected fun initVeiwPager() {
        viewPager = rootView?.findViewById(R.id.viewPager)
        tabLayout = rootView?.findViewById(R.id.tabLayout)

        viewPager?.offscreenPageLimit = offscreenPageLimit
        viewPager?.adapter = adapter
        tabLayout?.setTabData(createTitles)
        tabLayout?.setOnTabSelectListener(object : OnTabSelectListener {

            override fun onTabSelect(position: Int) {
                viewPager?.currentItem = position
            }

            override fun onTabReselect(position: Int) {

            }
        })
        pageChangeCallback = PageChangeCallback()
        viewPager?.registerOnPageChangeCallback(pageChangeCallback!!)
    }

    inner class PageChangeCallback : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            tabLayout?.currentTab = position
        }
    }

    inner class VpAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        private val fragments = mutableListOf<Fragment>()

        fun addFragments(fragment: Array<Fragment>) {
            fragments.addAll(fragment)
        }

        override fun getItemCount() = fragments.size

        override fun createFragment(position: Int) = fragments[position]
    }
}
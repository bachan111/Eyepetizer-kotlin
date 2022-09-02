package com.bachan.eyepetizer_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.bachan.eyepetizer_kotlin.base.BaseActivity
import com.bachan.eyepetizer_kotlin.event.RefreshEvent
import com.bachan.eyepetizer_kotlin.extension.logD
import com.bachan.eyepetizer_kotlin.extension.setOnClickListener
import com.bachan.eyepetizer_kotlin.ui.home.HomeFragment
import com.bachan.eyepetizer_kotlin.util.DialogAppraiseTipsWorker
import kotlinx.android.synthetic.main.layout_bottom_navigation_bar.*
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity() {

    private var backPressTime = 0L

    private var homePageFragment: HomeFragment? = null

    private val fragmentManager: FragmentManager by lazy { supportFragmentManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setupViews() {
        super.setupViews()
        observe()
        setOnClickListener(btnHomePage, btnCommunity, btnNotification, ivRelease, btnMine){
            when(this){
                btnHomePage -> {
                    notificationUiRefresh(0)
                    setTabSelection(0)
                }
            }
        }
    }

    private fun setTabSelection(index: Int) {
        clearAllSelected()
        fragmentManager.beginTransaction().apply {
            hideFragments(this)
            when (index) {
                0 -> {
                    ivHomePage.isSelected = true
                    tvHomePage.isSelected = true
                    if (homePageFragment == null) {
                        homePageFragment = HomeFragment.newInstance()
                        add(R.id.homeActivityFragContainer, homePageFragment!!)
                    } else {
                        show(homePageFragment!!)
                    }
                }
                1 -> {
                    ivCommunity.isSelected = true
                    tvCommunity.isSelected = true
//                    if (communityFragment == null) {
//                        communityFragment = CommunityFragment()
//                        add(R.id.homeActivityFragContainer, communityFragment!!)
//                    } else {
//                        show(communityFragment!!)
//                    }
                }
                2 -> {
                    ivNotification.isSelected = true
                    tvNotification.isSelected = true
//                    if (notificationFragment == null) {
//                        notificationFragment = NotificationFragment()
//                        add(R.id.homeActivityFragContainer, notificationFragment!!)
//                    } else {
//                        show(notificationFragment!!)
//                    }
                }
                3 -> {
                    ivMine.isSelected = true
                    tvMine.isSelected = true
//                    if (mineFragment == null) {
//                        mineFragment = MineFragment.newInstance()
//                        add(R.id.homeActivityFragContainer, mineFragment!!)
//                    } else {
//                        show(mineFragment!!)
//                    }
                }
                else -> {
                    ivHomePage.isSelected = true
                    tvHomePage.isSelected = true
                    if (homePageFragment == null) {
                        homePageFragment = HomeFragment.newInstance()
                        add(R.id.homeActivityFragContainer, homePageFragment!!)
                    } else {
                        show(homePageFragment!!)
                    }
                }
            }
        }.commitAllowingStateLoss()
    }

    private fun clearAllSelected() {
        ivHomePage.isSelected = false
        tvHomePage.isSelected = false
        ivCommunity.isSelected = false
        tvCommunity.isSelected = false
        ivNotification.isSelected = false
        tvNotification.isSelected = false
        ivMine.isSelected = false
        tvMine.isSelected = false
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        transaction.run {
            if (homePageFragment != null) hide(homePageFragment!!)
//            if (communityFragment != null) hide(communityFragment!!)
//            if (notificationFragment != null) hide(notificationFragment!!)
//            if (mineFragment != null) hide(mineFragment!!)
        }
    }

    private fun notificationUiRefresh(selectionIndex: Int) {
        when (selectionIndex) {
            0 -> {
                if (ivHomePage.isSelected) EventBus.getDefault().post(RefreshEvent(HomeFragment::class.java))
            }
            1 -> {
//                if (ivCommunity.isSelected) EventBus.getDefault().post(RefreshEvent(CommunityFragment::class.java))
            }
            2 -> {
//                if (ivNotification.isSelected) EventBus.getDefault().post(RefreshEvent(NotificationFragment::class.java))
            }
            3 -> {
//                if (ivMine.isSelected) EventBus.getDefault().post(RefreshEvent(MineFragment::class.java))
            }
        }
    }

    private fun observe() {
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(DialogAppraiseTipsWorker.showDialogWorkRequest.id).observe(this, Observer { workInfo ->
            logD(TAG, "observe: workInfo.state = ${workInfo.state}")
            if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                WorkManager.getInstance(this).cancelAllWork()
            } else if (workInfo.state == WorkInfo.State.RUNNING) {
                if (isActive) {
                    DialogAppraiseTipsWorker.showDialog(this)
                    WorkManager.getInstance(this).cancelAllWork()
                }
            }
        })
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
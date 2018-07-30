package com.ly.supermvp.delegate

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import butterknife.BindView
import com.ly.supermvp.R
import com.ly.supermvp.base.AppDelegate

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/20 下午5:25
 */
class MainActivityDelegate: AppDelegate() {
    @BindView(R.id.viewpager_main) lateinit var mViewPager: ViewPager

    @BindView(R.id.tabs) lateinit var mTabLayout: TabLayout

    override fun getRootLayoutId(): Int {
        return R.layout.activity_main
    }

    fun setViewPager(adapter: FragmentPagerAdapter) {
        mViewPager.offscreenPageLimit = 3
        mViewPager.adapter = adapter
    }

    fun setUpViewPager() {
        mTabLayout.setupWithViewPager(mViewPager)
    }

}
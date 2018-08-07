package com.ly.supermvp.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ly.supermvp.R
import com.ly.supermvp.view.fragment.NewsFragment
import com.ly.supermvp.view.fragment.WeatherFragment

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/21 下午5:05
 */
class MainPagerAdapter(fm: FragmentManager?, context: Context?) : FragmentPagerAdapter(fm) {
    var mContext: Context? = null
    init {
        mContext = context
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> NewsFragment.newInstance()
            else -> WeatherFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> mContext!!.resources.getString(R.string.main_tab_news)
            1 -> mContext!!.resources.getString(R.string.main_tab_picture)
            2 -> mContext!!.resources.getString(R.string.main_tab_weather)
            else -> ""
        }
    }
}
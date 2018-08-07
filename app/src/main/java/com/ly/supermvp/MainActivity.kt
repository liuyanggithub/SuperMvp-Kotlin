package com.ly.supermvp

import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import com.ly.supermvp.adapter.MainPagerAdapter
import com.ly.supermvp.base.ActivityPresenter
import com.ly.supermvp.delegate.MainActivityDelegate
import com.orhanobut.logger.Logger

class MainActivity : ActivityPresenter<MainActivityDelegate>(), View.OnClickListener {
    var mMainPagerAdapter: MainPagerAdapter? = null
    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.fab -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        }
    }

    override fun getDelegateClass(): Class<MainActivityDelegate> {
        return MainActivityDelegate::class.java
    }

    override fun initData() {
        super.initData()
        mMainPagerAdapter = MainPagerAdapter(supportFragmentManager, this)
        Logger.d("-----------------------${1/2}")
        Logger.i("-----------------------${1/2}")
    }

    override fun initView() {
        super.initView()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewDelegate.setViewPager(mMainPagerAdapter!!)
        viewDelegate.setUpViewPager()
        viewDelegate.setOnClickListener(this, R.id.fab)
    }

}

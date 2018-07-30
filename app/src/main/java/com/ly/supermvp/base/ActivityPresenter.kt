package com.ly.supermvp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/20 下午5:00
 */
abstract class ActivityPresenter<T: IDelegate>: AppCompatActivity() {
    protected var viewDelegate: T
    init {
        try {
            viewDelegate = getDelegateClass().newInstance()
        }catch (e: InstantiationException) {
            throw RuntimeException("create IDelegate error")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDelegate.creat(layoutInflater, null, savedInstanceState)
        setContentView(viewDelegate.getRootView())
        initToolbar()
        initData()
        initView()

    }

    protected open fun initView() {
    }

    protected open fun initData() {
    }

    protected open fun initToolbar() {
        val toolbar = viewDelegate.getToolbar()
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }


    protected abstract fun getDelegateClass(): Class<T>

}
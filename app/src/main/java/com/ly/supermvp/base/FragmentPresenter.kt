package com.ly.supermvp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/21 下午5:24
 */
open abstract class FragmentPresenter<T : IDelegate>: Fragment() {
    public var viewDelegate: T? = null
    var unbinder: Unbinder? = null
    protected abstract fun getDelegateClass(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            viewDelegate = getDelegateClass().newInstance()
        }catch (e: java.lang.InstantiationException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDelegate?.creat(inflater, container, savedInstanceState)
        unbinder = ButterKnife.bind(this, viewDelegate?.getRootView()!!)
        return viewDelegate?.getRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDelegate?.initWidget()
        initData()
        initView()
        bindEvenListener()
    }

    protected open fun initView() {

    }


    protected open fun initData() {

    }

    protected open fun bindEvenListener() {}


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (viewDelegate == null) {
            try {
                viewDelegate = getDelegateClass().newInstance()
            }catch (e: java.lang.InstantiationException) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDelegate = null
        unbinder?.unbind()
    }
}
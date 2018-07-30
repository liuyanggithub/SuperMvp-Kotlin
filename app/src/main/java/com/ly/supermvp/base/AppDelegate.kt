package com.ly.supermvp.base

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.ly.supermvp.utils.ToastUtil

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/20 下午5:26
 */
abstract class AppDelegate: IDelegate {
    protected var mViews: SparseArray<View> = SparseArray()

    protected var mRootView: View? = null

    public abstract fun getRootLayoutId(): Int


    override fun creat(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        var rootId = getRootLayoutId()
        mRootView = inflater.inflate(rootId, container, false)
        ButterKnife.bind(this, mRootView!!)
    }

    override fun getOptionsMenuId(): Int {
        return 0
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun getRootView(): View {
        return mRootView!!
    }
    public fun<T: View> bindView(id: Int): T {
        var view = mViews.get(id)
        if(view == null) {
            view = mRootView?.findViewById(id)!!
            mViews.put(id, view)
        }
        return view as T
    }

    public fun<T: View> get(id: Int?): T {
        return bindView(id!!)
    }

    fun setOnClickListener(listener: View.OnClickListener, vararg ids: Int?) {
        for (id in ids) {
            this.get<View>(id).setOnClickListener(listener)
        }
    }

    fun<T: Activity> getActivity(): T {
        return mRootView!!.context as T
    }

    fun showSnackbar(msg: String) {
        Snackbar.make(mRootView!!, msg, Snackbar.LENGTH_LONG)
                .show()
    }

    fun showToast(msg: String) {
        ToastUtil.showShort(msg)
    }
}
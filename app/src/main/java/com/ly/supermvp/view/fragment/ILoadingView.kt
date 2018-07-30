package com.ly.supermvp.view.fragment

import android.content.Context
import android.view.View

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/21 下午5:46
 */
interface ILoadingView {
    fun showLoading()
    fun showContent()
    fun showError(messageId: Int, listener: View.OnClickListener)
}
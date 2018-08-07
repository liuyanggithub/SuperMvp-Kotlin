package com.ly.supermvp.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/20 下午4:54
 */
interface IDelegate {
    fun creat(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    fun getOptionsMenuId(): Int
    fun getToolbar(): Toolbar?
    fun getRootView(): View
    fun initWidget()
}
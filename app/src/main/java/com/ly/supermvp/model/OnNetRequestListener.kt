package com.ly.supermvp.model

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/26 下午4:15
 */
interface OnNetRequestListener<T> {
    /**
     * 网络请求开始
     */
    fun onStart()

    /**
     * 网络请求结束
     */
    fun onFinish()

    /**
     * 网络请求成功
     * @param data 返回的数据实体类信息 泛型定义
     */
    fun onSuccess(data: T)

    /**
     * 请求失败
     * @param t 异常
     */
    fun onFailure(t: Throwable)
}
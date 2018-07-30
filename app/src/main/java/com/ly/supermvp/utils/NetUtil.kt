package com.ly.supermvp.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/27 下午3:22
 */
class NetUtil private constructor(){
    init {
        throw UnsupportedOperationException("cannot be instantiated")
    }
    companion object {
        /**
         * 判断网络是否连接
         *
         * @param context
         * @return
         */
        fun isConnected(context: Context): Boolean {

            val connectivity = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (null != connectivity) {

                val info = connectivity.activeNetworkInfo
                if (null != info && info.isConnected) {
                    if (info.state == NetworkInfo.State.CONNECTED) {

                        return true
                    }
                }
            }
            return false
        }

        /**
         * 判断是否是wifi连接
         */
        fun isWifi(context: Context): Boolean {
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    ?: return false

            return cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI

        }

        /**
         * 打开网络设置界面
         */
        fun openSetting(activity: Activity) {
            val intent = Intent("/")
            val cm = ComponentName("com.android.settings",
                    "com.android.settings.WirelessSettings")
            intent.component = cm
            intent.action = "android.intent.action.VIEW"
            activity.startActivityForResult(intent, 0)
        }

    }
}
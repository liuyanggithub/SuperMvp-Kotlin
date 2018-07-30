package com.ly.supermvp

import android.app.Application
import android.os.Environment
import com.ly.supermvp.utils.ToastUtil
import com.squareup.leakcanary.LeakCanary

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/27 下午2:50
 */
class MyApplication: Application() {
    companion object {
        @JvmStatic lateinit var instance: MyApplication
            private set
    }

    var cacheDir: String = ""

    override fun onCreate() {
        super.onCreate()
        instance = this
        ToastUtil.register(this)
        LeakCanary.install(this)
        if (applicationContext.externalCacheDir != null && isExistSDCard()) {
            this.cacheDir = applicationContext.externalCacheDir.toString()
        } else {
            cacheDir = applicationContext.cacheDir.toString()
        }
    }

    fun isExistSDCard(): Boolean {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
    }
}
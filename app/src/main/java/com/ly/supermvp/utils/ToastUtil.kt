package com.ly.supermvp.utils

import android.content.Context
import android.widget.Toast

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/27 下午3:07
 */
class ToastUtil {
    companion object {
        lateinit var context: Context
        fun register(context: Context) {
            this.context = context
        }

        fun showShort(resId: Int) {
            check()
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
        }

        fun showShort(message: String) {
            check()
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }


        fun showLong(resId: Int) {
            check()
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
        }


        fun showLong(message: String) {
            check()
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }


        fun showLongX2(message: String) {
            showLong(message)
            showLong(message)
        }


        fun showLongX2(resId: Int) {
            showLong(resId)
            showLong(resId)
        }


        fun showLongX3(resId: Int) {
            showLong(resId)
            showLong(resId)
            showLong(resId)
        }


        fun showLongX3(message: String) {
            showLong(message)
            showLong(message)
            showLong(message)
        }

        private fun check() {
            if (context == null) {
                throw NullPointerException(
                        "Must initial call ToastUtils.register(Context context) in your " +
                                "<? " +
                                "extends Application class>")
            }
        }
    }
}
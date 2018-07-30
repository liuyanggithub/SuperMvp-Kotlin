package com.ly.supermvp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ly.supermvp.R

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/24 下午6:30
 */
class GlideUtil {
    companion object {
        fun loadImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.mipmap.ic_holding)
                    .error(R.mipmap.ic_error)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView)
        }
    }
}
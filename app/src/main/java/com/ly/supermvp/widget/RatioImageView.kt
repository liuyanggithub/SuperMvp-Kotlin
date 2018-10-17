package com.ly.supermvp.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/8/9 下午4:59
 */
public class RatioImageView : ImageView {
    private var originalWidth: Int = 0
    private var originalHeight: Int = 0
    constructor(context: Context): super(context) {

    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    fun setOriginalSize(originalWidth: Int, originalHeight: Int) {
        this.originalWidth = originalWidth
        this.originalHeight = originalHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (originalWidth > 0 && originalHeight > 0) {
            val ratio = originalWidth.toFloat() / originalHeight

            val width = MeasureSpec.getSize(widthMeasureSpec)
            var height = MeasureSpec.getSize(heightMeasureSpec)

            if (width > 0) {
                height = (width / ratio).toInt()
            }

            setMeasuredDimension(width, height)
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

}
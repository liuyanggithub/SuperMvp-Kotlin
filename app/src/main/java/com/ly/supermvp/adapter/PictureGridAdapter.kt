package com.ly.supermvp.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ly.supermvp.R
import com.ly.supermvp.model.picture.PictureBody
import com.ly.supermvp.utils.GlideUtil
import com.ly.supermvp.widget.RatioImageView

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/8/9 下午4:35
 */
class PictureGridAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var mList: List<PictureBody>? = null
    var context: Activity? = null

    var mOnImageClickListener: OnImageClickListener? = null

    constructor(mList: List<PictureBody>?, context: Activity?) : this() {
        this.mList = mList
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture_grid, parent, false)
        val viewHolder = ItemViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val pictureBody = mList!![position]
            GlideUtil.loadImage(context!!, pictureBody.list!![0].middle!!, holder.iv_picture)
            holder.tv_title.text = pictureBody.title
        }
    }

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var iv_picture: RatioImageView = itemView.findViewById(R.id.iv_picture)
        var tv_title: TextView = itemView.findViewById(R.id.tv_title)

        init {
            iv_picture.setOriginalSize(50, 50)
            iv_picture.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mOnImageClickListener?.onImageClick(v!!, this.position)
        }

    }

    fun setOnImageClickListener(onItemClickListener: OnImageClickListener) {
        this.mOnImageClickListener = onItemClickListener
    }
}
/**
 * 点击条目图片接口
 */
interface OnImageClickListener {
    fun onImageClick(view: View, position: Int)
}

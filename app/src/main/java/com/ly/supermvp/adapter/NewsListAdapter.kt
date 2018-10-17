package com.ly.supermvp.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ly.supermvp.R
import com.ly.supermvp.model.news.NewsBody
import com.ly.supermvp.utils.GlideUtil
import com.ly.supermvp.utils.ToastUtil.Companion.context

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/31 下午5:43
 */
public class NewsListAdapter() : Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_ITEM = 0x00//内容
    private val TYPE_FOOTER = 0x01//加载更多

    lateinit var mContext: Activity
    lateinit var mNewsBodis: List<NewsBody>

    /**
     * 条目点击监听
     */
    internal var mOnItemClickListener: OnItemClickListener? = null

    /**
     * 是否显示加载更多视图
     */
    var mShowFooter = true

    constructor(context: Activity, newsBodis: List<NewsBody>) : this() {
        mContext = context
        mNewsBodis = newsBodis
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = mNewsBodis[position]

            if (item.imageurls != null && item.imageurls!!.isNotEmpty()) {
                GlideUtil.loadImage(context, item.imageurls?.get(0)?.url!!, holder.imageView)
            } else {
                GlideUtil.loadImage(context, "", holder.imageView)
            }
            holder.desc.text = item.desc
            holder.title.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return if (viewType == TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news_list, parent, false)
            ItemViewHolder(view)
        }else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_footer, null)
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            FooterViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        val begin = if(mShowFooter) 1 else 0
        return if (mNewsBodis == null) {
            begin
        } else this.mNewsBodis.size + begin
    }

    override fun getItemViewType(position: Int): Int {
        if(!mShowFooter) {
            return TYPE_ITEM
        }
        return if(position + 1 == itemCount) {
            TYPE_FOOTER
        }else {
            TYPE_ITEM
        }
    }

    public fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    inner class FooterViewHolder(view: View?): RecyclerView.ViewHolder(view) {
        init {

        }
    }

    inner class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var desc: TextView = itemView!!.findViewById(R.id.tv_desc)
        var imageView: ImageView = itemView!!.findViewById(R.id.iv_desc)
        var title: TextView = itemView!!.findViewById(R.id.tv_title)

        init {
            itemView!!.setOnClickListener(this)
        }


        override fun onClick(view: View?) {
            mOnItemClickListener.let {
                mOnItemClickListener?.onItemClick(view!!, position)
            }

        }

    }

}
/**
 * 点击条目接口
 */
public interface OnItemClickListener {
    fun onItemClick(view: View, position: Int)

}
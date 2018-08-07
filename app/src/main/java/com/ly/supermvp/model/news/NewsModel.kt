package com.ly.supermvp.model.news

import com.ly.supermvp.model.OnNetRequestListener

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/31 下午5:32
 */
interface NewsModel {
    /**
     * 加载新闻列表
     * @param page 页数
     * @param channelId 频道id 来自api
     * @param channelName 频道名称
     */
    fun netLoadNewsList(page: Int, channelId: String, channelName: String, listListener: OnNetRequestListener<List<NewsBody>>)

}
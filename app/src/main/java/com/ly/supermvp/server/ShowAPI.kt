package com.ly.supermvp.server

import com.ly.supermvp.common.BizInterface
import com.ly.supermvp.model.entity.ShowApiResponse
import com.ly.supermvp.model.news.ShowApiNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/31 下午5:36
 */
interface ShowAPI {
    /**
     * 新闻列表
     * @param cacheControl 缓存控制
     * @param appId 易源appid
     * @param key 易源密钥
     * @param page 页数
     * @param channelId 频道id
     * @param channelName 名称
     * @return
     */
    @GET(BizInterface.NEWS_URL)
    @Headers("apikey: " + BizInterface.API_KEY)
    abstract fun getNewsList(@Header("Cache-Control") cacheControl: String,
                             @Query("showapi_appid") appId: String,
                             @Query("showapi_sign") key: String,
                             @Query("page") page: Int,
                             @Query("channelId") channelId: String, //新闻频道id，必须精确匹配
                             @Query("channelName") channelName: String): Call<ShowApiResponse<ShowApiNews>> //新闻频道名称，可模糊匹配

}
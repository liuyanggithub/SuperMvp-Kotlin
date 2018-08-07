package com.ly.supermvp.model.news

import android.text.TextUtils
import com.ly.supermvp.common.BizInterface
import com.ly.supermvp.model.OnNetRequestListener
import com.ly.supermvp.model.entity.ShowApiResponse
import com.ly.supermvp.server.RetrofitService
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/31 下午5:33
 */
class NewsModelImpl: NewsModel {
    companion object {
        val CHANNEL_ID = "5572a109b3cdc86cf39001db"//频道id 来自api指定
        val CHANNEL_NAME = "国内最新"//频道名称 来自api指定
    }
    override fun netLoadNewsList(page: Int, channelId: String, channelName: String, listListener: OnNetRequestListener<List<NewsBody>>) {
        //注意，此处采用Retrofit的官方响应方式，天气预报采用RxJava，学习一下两种用法
        val call = RetrofitService.instance
                .createShowAPI()!!
                .getNewsList(RetrofitService.getCacheControl(), BizInterface.SHOW_API_APPID,
                        BizInterface.SHOW_API_KEY, page, channelId, channelName)

        call.enqueue(object : Callback<ShowApiResponse<ShowApiNews>> {
            override fun onResponse(call: Call<ShowApiResponse<ShowApiNews>>, response: Response<ShowApiResponse<ShowApiNews>>) {
                Logger.d(response.message() + response.code() + response.body()!!.showapi_res_code
                        + response.body()!!.showapi_res_error)
                if (response.body() != null && TextUtils.equals("0", response.body()!!.showapi_res_code)) {
                    listListener.onSuccess(response.body()?.showapi_res_body?.pagebean?.contentlist!!)
                } else {
                    listListener.onFailure(Exception())
                }
            }

            override fun onFailure(call: Call<ShowApiResponse<ShowApiNews>>, t: Throwable) {
                listListener.onFailure(t)
            }
        })
    }
}
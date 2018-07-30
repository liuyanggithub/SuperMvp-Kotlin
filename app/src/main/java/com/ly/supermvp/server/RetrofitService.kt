package com.ly.supermvp.server

import com.ly.supermvp.MyApplication
import com.ly.supermvp.common.BizInterface
import com.ly.supermvp.utils.NetUtil
import com.orhanobut.logger.Logger
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/26 下午4:25
 */
class RetrofitService private constructor(){
    companion object {
        //设缓存有效期为两天
        val CACHE_STALE_SEC = (60 * 60 * 24 * 2).toLong()
        //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
        val CACHE_CONTROL_CACHE = "only-if-cached, max-stale=$CACHE_STALE_SEC"
        //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
        val CACHE_CONTROL_NETWORK = "max-age=0"
        val instance: RetrofitService by lazy { RetrofitService() }
        @JvmStatic
        private var baiduAPI: BaiduAPI? = null
        @JvmStatic
        private var mOkHttpClient: OkHttpClient? = null


        fun getCacheControl(): String {
            return if (NetUtil.isConnected(MyApplication.instance)) CACHE_CONTROL_NETWORK else CACHE_CONTROL_CACHE
        }
    }


    fun createBaiduAPI(): BaiduAPI? {
        if (baiduAPI == null) {
            synchronized(RetrofitService::class.java) {
                if (baiduAPI == null) {
                    initOkHttpClient()
                    baiduAPI = Retrofit.Builder()
                            .client(mOkHttpClient)
                            .baseUrl(BizInterface.API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build().create(BaiduAPI::class.java!!)
                }
            }
        }
        return baiduAPI
    }

    // 配置OkHttpClient
    private fun initOkHttpClient() {
        if (mOkHttpClient == null) {
            // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
            val cacheFile = File(MyApplication.instance.getCacheDir(),
                    "HttpCache") // 指定缓存路径
            val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong()) // 指定缓存大小100Mb
            // 云端响应头拦截器，用来配置缓存策略
            val rewriteCacheControlInterceptor = Interceptor { chain ->
                var request = chain.request()
                if (!NetUtil.isConnected(MyApplication.instance)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE).build()
                    Logger.e("no network")
                }
                val originalResponse = chain.proceed(request)
                if (NetUtil.isConnected(MyApplication.instance)) {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    val cacheControl = request.cacheControl().toString()
                    originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma").build()
                } else {
                    originalResponse.newBuilder().header("Cache-Control",
                            "public, only-if-cached,$CACHE_STALE_SEC")
                            .removeHeader("Pragma").build()
                }
            }
            //            mOkHttpClient = new OkHttpClient();
            //            mOkHttpClient.setCache(cache);
            //            mOkHttpClient.networkInterceptors().add(rewriteCacheControlInterceptor);
            //            mOkHttpClient.interceptors().add(rewriteCacheControlInterceptor);
            //            mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
            //okhttp 3
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            mOkHttpClient = OkHttpClient.Builder().cache(cache)
                    .addNetworkInterceptor(rewriteCacheControlInterceptor)
                    .addInterceptor(rewriteCacheControlInterceptor)
                    .addInterceptor(logInterceptor)
                    .connectTimeout(10, TimeUnit.SECONDS).build()

        }
    }
}
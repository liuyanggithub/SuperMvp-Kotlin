package com.ly.supermvp.model.weather

import com.ly.supermvp.model.OnNetRequestListener
import com.ly.supermvp.model.entity.ShowApiResponse
import com.ly.supermvp.server.RetrofitService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/26 下午4:16
 */
class WeatherModelImpl: WeatherModel {
    override fun netLoadWeatherWithLocation(area: String, needMoreDay: String, needIndex: String, needAlarm: String, need3HourForcast: String, listener: OnNetRequestListener<ShowApiWeather>) {
        //使用RxJava响应Retrofit
        val observable = RetrofitService.instance.createBaiduAPI()?.getWeather(RetrofitService.getCacheControl(), area, needMoreDay,
                needIndex, needAlarm, need3HourForcast)

        observable!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(Consumer<Disposable> { listener.onStart() })
                .subscribe(object : Observer<ShowApiResponse<ShowApiWeather>> {
                    override fun onError(e: Throwable) {
                        listener.onFailure(e)
                        listener.onFinish()
                    }

                    override fun onComplete() {
                        listener.onFinish()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(showApiWeatherShowApiResponse: ShowApiResponse<ShowApiWeather>) {
                        if (showApiWeatherShowApiResponse.showapi_res_body?.now == null) {
                            listener.onFailure(Exception(showApiWeatherShowApiResponse.showapi_res_code))
                        } else {
                            listener.onSuccess(showApiWeatherShowApiResponse.showapi_res_body!!)
                        }
                    }
                })
    }
}
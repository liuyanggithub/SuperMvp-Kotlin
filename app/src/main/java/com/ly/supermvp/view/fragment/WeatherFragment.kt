package com.ly.supermvp.view.fragment

import android.text.TextUtils
import android.view.View
import com.ly.supermvp.R
import com.ly.supermvp.base.FragmentPresenter
import com.ly.supermvp.delegate.WeatherFragmentDelegate
import com.ly.supermvp.model.OnNetRequestListener
import com.ly.supermvp.model.weather.ShowApiWeather
import com.ly.supermvp.model.weather.WeatherModel
import com.ly.supermvp.model.weather.WeatherModelImpl
import com.orhanobut.logger.Logger

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/27 下午3:31
 */
class WeatherFragment: FragmentPresenter<WeatherFragmentDelegate>(), View.OnClickListener{
    companion object {
        val NEED_MORE_DAY = "1"
        val NEED_INDEX = "1"
        val NEED_ALARM = "1"
        val NEED_3_HOUR_FORCAST = "1"

        fun newInstance(): WeatherFragment {
            val fragment = WeatherFragment()
            return fragment
        }

    }

    private var mWeatherModel: WeatherModel? = null

    override fun getDelegateClass(): Class<WeatherFragmentDelegate> {
        return WeatherFragmentDelegate::class.java
    }

    override fun initData() {
        super.initData()
        mWeatherModel = WeatherModelImpl()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.bt_weather -> netWeather()
        }
    }

    override fun bindEvenListener() {
        super.bindEvenListener()
        viewDelegate?.setOnClickListener(this, R.id.bt_weather)
    }
    /**
     * 获取天气预报
     */
    private fun netWeather() {
        if (TextUtils.isEmpty(viewDelegate?.getInputLocation())) {
            viewDelegate?.showSnackbar("输入为空")
            return
        }
        mWeatherModel?.netLoadWeatherWithLocation(viewDelegate?.getInputLocation()!!, NEED_MORE_DAY,
                NEED_INDEX, NEED_ALARM, NEED_3_HOUR_FORCAST, object : OnNetRequestListener<ShowApiWeather> {
            override fun onStart() {
                viewDelegate?.showLoading()
            }

            override fun onFinish() {
                viewDelegate?.showContent()
            }

            override fun onSuccess(weather: ShowApiWeather) {
                Logger.d("onSuccess")
                viewDelegate?.closeSoftInput()
                viewDelegate?.showNowWeatherDalog(weather)
            }

            override fun onFailure(t: Throwable) {
                viewDelegate?.showSnackbar("请求错误")
                Logger.d("onFailure")
            }
        })
    }
}
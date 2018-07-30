package com.ly.supermvp.model.weather

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/24 下午6:46
 */
class ShowApiWeather {
    var f1: ShowApiWeatherNormalInner? = null//后一天的天气预报
    var now: ShowApiWeatherNowInner? = null//现在的天气预报

    inner class ShowApiWeatherNormalInner {
        var day: String? = null//日期
        var air_press: String? = null//气压
        var sun_begin_end: String? = null//白天持续时间
        var weekday: String? = null//星期几
        var ziwaixian: String? = null//紫外线
        //白天
        var day_air_temperature: String? = null//气温
        var day_weather: String? = null//天气“晴雨”
        var day_weather_code: String? = null//天气代码
        var day_weather_pic: String? = null//天气图片
        var day_wind_direction: String? = null//风向
        var day_wind_power: String? = null//风力
        //晚上
        var night_air_temperature: String? = null
        var night_weather: String? = null
        var night_weather_code: String? = null
        var night_weather_pic: String? = null
        var night_wind_direction: String? = null
        var night_wind_power: String? = null
    }

    inner class ShowApiWeatherNowInner {
        var aqi: String? = null//污染指数
        var sd: String? = null//湿度
        var temperature: String? = null//气温
        var temperature_time: String? = null//气温时间
        var weather: String? = null//天气“晴雨”
        var weather_code: String? = null//天气代码
        var weather_pic: String? = null//天气图片
        var wind_direction: String? = null//风向
        var wind_power: String? = null//风力
    }
}
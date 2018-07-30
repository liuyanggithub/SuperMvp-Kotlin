package com.ly.supermvp.common

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/26 下午5:02
 */
class BizInterface {
    companion object {
        /**
         * 百度API接口
         */
        const val API = "http://apis.baidu.com"
        /**
         * 易源API接口（官方）
         */
        const val SHOW_API = "http://route.showapi.com"
        /**
         * 百度开发者API密钥
         */
        const val API_KEY = "4720bdbcfb3aa457eefd38d2f8fa580f"
        /**
         * 易源api密钥
         */
        const val SHOW_API_KEY = "ae06b1ecff2847dba442b9433032f489"
        /**
         * 易源appid
         */
        const val SHOW_API_APPID = "31108"

        /**
         * 新闻接口
         * 服务商： 易源接口
         */
        const val NEWS_URL = "/109-35"
        /**
         * 天气预报 (根据地名)
         * 服务商： 易源接口
         */
        const val WEATHER_URL = "/showapi_open_bus/weather_showapi/address"

        /**
         * 美图大全 (根据类型)
         * "list": [
         * {
         * "id": 4001, //此id很重要，在【图片查询】接口里将使用此id进行分类查询
         * "name": "清纯"
         * },
         * {
         * "id": 4002,
         * "name": "气质"
         * },
         * {
         * "id": 4003,
         * "name": "萌女"
         * },
         * {
         * "id": 4004,
         * "name": "校花"
         * },
         * {
         * "id": 4005,
         * "name": "婚纱"
         * },
         * {
         * "id": 4006,
         * "name": "街拍"
         * },
         * {
         * "id": 4007,
         * "name": "非主流"
         * },
         * {
         * "id": 4008,
         * "name": "美腿"
         * },
         * {
         * "id": 4009,
         * "name": "性感"
         * },
         * {
         * "id": 4010,
         * "name": "车模"
         * },
         * {
         * "id": 4011,
         * "name": "男色图片"
         * },
         * {
         * "id": 4012,
         * "name": "模特美女"
         * },
         * {
         * "id": 4013,
         * "name": "美女魅惑"
         * },
         * {
         * "id": 4014,
         * "name": "日韩美女"
         * }
         * ],
         * 服务商： 易源接口
         */
        const val PICTURES_URL = "/showapi_open_bus/pic/pic_search"
    }
}
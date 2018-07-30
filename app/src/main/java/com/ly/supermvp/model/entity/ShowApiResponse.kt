package com.ly.supermvp.model.entity

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/26 下午5:17
 */
class ShowApiResponse<T> {
    var showapi_res_code: String? = null
    var showapi_res_error: String? = null
    var showapi_res_body: T? = null
}
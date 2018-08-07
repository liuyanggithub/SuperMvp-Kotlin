package com.ly.supermvp.model.news

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/31 下午5:37
 */
class ShowApiNews {
    var pagebean: PageBean? = null
    var ret_code: String? = null

    inner class PageBean {
        var allNum: String? = null
        var allPages: String? = null
        var currentPage: String? = null
        var maxResult: String? = null
        var contentlist: List<NewsBody>? = null
    }
}
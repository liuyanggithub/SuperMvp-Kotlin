package com.ly.supermvp.view.fragment

import android.view.View
import com.ly.supermvp.R
import com.ly.supermvp.adapter.NewsListAdapter
import com.ly.supermvp.adapter.OnItemClickListener
import com.ly.supermvp.base.FragmentPresenter
import com.ly.supermvp.delegate.NewsFragmentDelegate
import com.ly.supermvp.delegate.SwipeRefreshAndLoadMoreCallBack
import com.ly.supermvp.model.OnNetRequestListener
import com.ly.supermvp.model.news.NewsBody
import com.ly.supermvp.model.news.NewsModel
import com.ly.supermvp.model.news.NewsModelImpl

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/8/6 下午5:48
 */
class NewsFragment : FragmentPresenter<NewsFragmentDelegate>(), SwipeRefreshAndLoadMoreCallBack{
    lateinit var mNewsModel: NewsModel
    var mPageNum = 1
    lateinit var mAdapter: NewsListAdapter

    //新闻数据列表
    var mNews = ArrayList<NewsBody>()

    override fun getDelegateClass(): Class<NewsFragmentDelegate> {
        return  NewsFragmentDelegate::class.java
    }

    override fun refresh() {
        netNewsList(true)
    }

    override fun loadMore() {
        netNewsList(false)
    }

    companion object {
        fun newInstance(): NewsFragment {
            val fragment = NewsFragment()
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        mNewsModel = NewsModelImpl()
        mAdapter = NewsListAdapter(activity!!, mNews)
        viewDelegate?.setListAdapter(mAdapter)
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {

            }
        })
        //注册下拉刷新
        viewDelegate?.registerSwipeRefreshCallBack(this)
        //注册加载更多
        viewDelegate?.registerLoadMoreCallBack(this, mAdapter)
        netNewsList(true)
    }
    private fun netNewsList(isRefresh: Boolean) {
        if (isRefresh) {
            mPageNum = 1
        }else {
            mPageNum++
        }
        mNewsModel.netLoadNewsList(mPageNum, NewsModelImpl.CHANNEL_ID, NewsModelImpl.CHANNEL_NAME,
                object : OnNetRequestListener<List<NewsBody>>{
                    override fun onStart() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onFinish() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onSuccess(data: List<NewsBody>) {
                        viewDelegate?.showContent()
                        if (isRefresh) {
                            if (!mNews.isEmpty()) {
                                mNews.clear()
                            }
                        }
                        mNews.addAll(data)
                        mAdapter.notifyDataSetChanged()
                    }

                    override fun onFailure(t: Throwable) {
                        viewDelegate?.showError(R.string.load_error, View.OnClickListener { netNewsList(true) })
                    }

                })
    }
}
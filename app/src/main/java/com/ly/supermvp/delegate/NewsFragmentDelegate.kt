package com.ly.supermvp.delegate

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ly.supermvp.adapter.NewsListAdapter
import com.ly.supermvp.view.fragment.ILoadingView

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/31 下午5:40
 */
class NewsFragmentDelegate: BaseRecyclerViewDelegate(), ILoadingView {
    /**
     * 用于加载更多的列表布局管理器
     */
    private var mRecycleViewLayoutManager: LinearLayoutManager? = null
    override fun initRecyclerView() {
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.setHasFixedSize(true)
        mRecycleViewLayoutManager = LinearLayoutManager(getActivity())
        recyclerview.layoutManager = mRecycleViewLayoutManager
    }

    override fun setFloatingActionMenuVisible(): Boolean {
        return false
    }

    /**
     * 设置加载更多接口
     *
     * @param callBack 加载更多的回调
     */
    fun registerLoadMoreCallBack(callBack: SwipeRefreshAndLoadMoreCallBack, adapter: NewsListAdapter) {
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private var lastVisibleItem: Int = 0

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                lastVisibleItem = mRecycleViewLayoutManager!!.findLastVisibleItemPosition()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()
                        && adapter.mShowFooter) {
                    //加载更多
                    callBack.loadMore()
                }
            }
        })
    }

}
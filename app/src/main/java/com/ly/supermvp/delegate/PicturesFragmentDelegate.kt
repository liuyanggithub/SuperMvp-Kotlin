package com.ly.supermvp.delegate

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.LinearLayout
import com.ly.supermvp.adapter.PictureGridAdapter
import com.ly.supermvp.view.fragment.ILoadingView
import com.orhanobut.dialogplus.DialogPlus

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/8/9 下午4:29
 */
class PicturesFragmentDelegate: BaseRecyclerViewDelegate(), ILoadingView {
    val PRELOAD_SIZE = 6
    lateinit var ll_dialog_holder: LinearLayout
    lateinit var mDialog: DialogPlus
    lateinit var mGridViewLayoutManager: StaggeredGridLayoutManager

    override fun initRecyclerView() {
        mGridViewLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerview.layoutManager = mGridViewLayoutManager
    }

    override fun setFloatingActionMenuVisible(): Boolean {
        return true
    }

    /**
     * 设置加载更多接口
     *
     * @param callBack 加载更多的回调
     */
    fun registerLoadMoreCallBack(callBack: SwipeRefreshAndLoadMoreCallBack, adapter: PictureGridAdapter) {
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val isBottom = mGridViewLayoutManager.findLastCompletelyVisibleItemPositions(IntArray(2))[1] >= adapter.getItemCount() - PRELOAD_SIZE
                if (!swipe_refresh_layout.isRefreshing && isBottom) {
                    callBack.loadMore()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

    }

    fun showRefreshLayout() {
        if (!swipe_refresh_layout.isRefreshing) {
            //            RxSwipeRefreshLayout.refreshing(swipe_refresh_layout).accept(true);
            swipe_refresh_layout.isRefreshing = true
        }
    }

}
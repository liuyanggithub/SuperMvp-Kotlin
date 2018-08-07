package com.ly.supermvp.delegate

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import com.ly.supermvp.R
import com.ly.supermvp.base.AppDelegate
import com.ly.supermvp.common.Constants
import com.ly.supermvp.view.fragment.ILoadingView
import com.ly.supermvp.widget.ProgressLayout

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/31 下午3:50
 */
abstract class BaseRecyclerViewDelegate: AppDelegate(), ILoadingView {
    @BindView(R.id.progress_layout)
    lateinit var progress_layout: ProgressLayout//进度条布局（通用，可实现错误按钮，点击重试）
    @BindView(R.id.swipe_refresh_layout)
    lateinit var swipe_refresh_layout: SwipeRefreshLayout//下拉刷新控件
    @BindView(R.id.recyclerview)
    lateinit var recyclerview: RecyclerView
    //悬浮菜单
    @BindView(R.id.floating_action_menu)
    lateinit var floating_action_menu: FloatingActionMenu
    @BindView(R.id.floating_action_button1)
    lateinit var floating_action_button1: FloatingActionButton
    @BindView(R.id.floating_action_button2)
    lateinit var floating_action_button2: FloatingActionButton
    @BindView(R.id.floating_action_button3)
    lateinit var floating_action_button3: FloatingActionButton
    @BindView(R.id.floating_action_button4)
    lateinit var floating_action_button4: FloatingActionButton

    lateinit var mFloatingActionButtons: MutableList<FloatingActionButton>//悬浮菜单选项数组

    /**
     * 初始化recyclerview，必须重写
     */
    internal abstract fun initRecyclerView()

    /**
     * 设置悬浮菜单是否显示，必须重写
     */
    internal abstract fun setFloatingActionMenuVisible(): Boolean

    override fun getRootLayoutId(): Int {
        return R.layout.layout_base_recyclerview
    }

    /**
     * 初始化
     */
    override fun initWidget() {
        super.initWidget()
        initSwipeRefreshLayout()
        initRecyclerView()
        initFloatingActionMenu()
    }

    /**
     * 初始化悬浮菜单
     */
    private fun initFloatingActionMenu() {
        floating_action_menu.visibility = if (setFloatingActionMenuVisible()) View.VISIBLE else View.GONE
        floating_action_menu.setClosedOnTouchOutside(true)
        mFloatingActionButtons = ArrayList()
        mFloatingActionButtons.add(floating_action_button1)
        mFloatingActionButtons.add(floating_action_button2)
        mFloatingActionButtons.add(floating_action_button3)
        mFloatingActionButtons.add(floating_action_button4)
    }

    /**
     * 初始化下拉刷新控件
     */
    private fun initSwipeRefreshLayout() {
        swipe_refresh_layout.setColorSchemeResources(*Constants.colors)
    }

    /**
     * 设置是否隐藏悬浮菜单选项卡
     * @param animate 是否动画
     */
    public fun hideMenu(animate: Boolean) {
        floating_action_menu.close(animate)
    }

    /**
     * 设置下拉刷新接口
     *
     * @param callBack 下拉刷新的回调接口
     */
    public fun registerSwipeRefreshCallBack(callBack: SwipeRefreshAndLoadMoreCallBack) {
        RxSwipeRefreshLayout.refreshes(swipe_refresh_layout).subscribe {
            // 2016/2/29 调用fragment的方法加载数据，需要解耦(已用接口解决)
            callBack.refresh()
        }
    }

    fun setListAdapter(adapter: RecyclerView.Adapter<*>) {
        recyclerview.adapter = adapter
    }


    override fun showLoading() {
        progress_layout.showLoading()
    }

    override fun showContent() {
        swipe_refresh_layout.isRefreshing = false
        if(!progress_layout.isContent()) {
            progress_layout.showContent()
        }
    }

    override fun showError(messageId: Int, listener: View.OnClickListener) {
        swipe_refresh_layout.isRefreshing = false
        if (!progress_layout.isError()) {
            progress_layout.showError(messageId, listener)
        }
    }
}
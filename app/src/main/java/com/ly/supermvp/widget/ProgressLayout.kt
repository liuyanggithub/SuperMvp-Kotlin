package com.ly.supermvp.widget

import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.ly.supermvp.R
import java.util.*

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/24 下午3:44
 */
class ProgressLayout : RelativeLayout {
    private val LOADING_TAG = "ProgressLayout.LOADING_TAG"
    private val ERROR_TAG = "ProgressLayout.ERROR_TAG"

    private var inflater: LayoutInflater? = null
    private var layoutParams: RelativeLayout.LayoutParams? = null
    //加载中视图
    private var loadingGroup: View? = null
    //加载错误视图
    private var errorGroup: View? = null
    //加载中布局
    private var loadingLayout: RelativeLayout? = null
    //加载错误布局
    private var errorLayout: RelativeLayout? = null
    //错误的文字
    private var errorTextView: TextView? = null
    //点击重试按钮
    private var errorButton: Button? = null

    //内容view容器
    private val contentViews = ArrayList<View>()

    enum class State {
        LOADING, CONTENT, ERROR
    }

    //初始状态为加载中
    private var currentState = State.LOADING

    constructor(context: Context): super(context) {

    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    fun init(attrs: AttributeSet) {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)

        if (child!!.tag == null || (!child!!.tag.equals(LOADING_TAG) && child!!.tag.equals(ERROR_TAG))) {
            contentViews.add(child)
        }
    }

    private fun showLoadingView() {
        if(loadingGroup == null) {
            loadingGroup = inflater?.inflate(R.layout.progress_loading_view, null)
            loadingLayout = loadingGroup?.findViewById(R.id.loadingStateRelativeLayout)
            loadingLayout?.tag = LOADING_TAG

            layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            layoutParams?.addRule(CENTER_IN_PARENT)

            addView(loadingLayout, layoutParams)
        }else {
            loadingLayout?.visibility = View.VISIBLE
        }
    }

    private fun showErrorView() {
        if (errorGroup == null) {
            errorGroup = inflater?.inflate(R.layout.progress_error_layout, null)
            errorLayout = errorGroup!!.findViewById(R.id.progress_error_layout_rl)
            errorLayout?.tag = ERROR_TAG

            errorTextView = errorGroup!!.findViewById(R.id.progress_error_tv)
            errorButton = errorGroup!!.findViewById(R.id.progress_error_btn)

            layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            layoutParams?.addRule(CENTER_IN_PARENT)

            addView(errorLayout, layoutParams)
        }else {
            errorLayout?.visibility = View.VISIBLE
        }
    }

    private fun hideLoadingView() {
        if (loadingLayout != null && loadingLayout?.visibility != View.GONE) {
            loadingLayout?.visibility = View.GONE
        }
    }

    private fun hideErrorView() {
        if (errorLayout != null && errorLayout?.visibility != View.GONE) {
            errorLayout?.visibility = View.GONE
        }
    }

    private fun setContentVisibility(visible: Boolean) {
        for (contentView in contentViews) {
            contentView.visibility = if (visible) View.VISIBLE else View.GONE
        }
    }

    fun showLoading() {
        currentState = State.LOADING
        this@ProgressLayout.showLoadingView()
        this@ProgressLayout.hideErrorView()
        this@ProgressLayout.setContentVisibility(false)
    }

    fun showContent() {
        currentState = State.CONTENT
        this@ProgressLayout.hideLoadingView()
        this@ProgressLayout.hideErrorView()
        this@ProgressLayout.setContentVisibility(true)
    }

    fun showError(@StringRes stringId: Int, onClickListener: View.OnClickListener) {
        currentState = State.ERROR
        this@ProgressLayout.hideLoadingView()
        this@ProgressLayout.showErrorView()

        errorTextView?.setText(resources.getString(stringId))
        errorButton?.setOnClickListener(onClickListener)
        this@ProgressLayout.setContentVisibility(false)
    }

    fun getCurrentState(): State {
        return currentState
    }

    fun isContent(): Boolean {
        return currentState == State.CONTENT
    }

    fun isLoading(): Boolean {
        return currentState == State.LOADING
    }

    fun isError(): Boolean {
        return currentState == State.ERROR
    }
}
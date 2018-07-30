package com.ly.supermvp.delegate

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import com.ly.supermvp.R
import com.ly.supermvp.base.AppDelegate
import com.ly.supermvp.model.weather.ShowApiWeather
import com.ly.supermvp.utils.GlideUtil
import com.ly.supermvp.view.fragment.ILoadingView
import com.ly.supermvp.widget.ProgressLayout
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.Holder
import com.orhanobut.dialogplus.ViewHolder
import com.rey.material.widget.EditText

/**
 * <Pre>
 *     TODO 描述该文件做什么
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2018/7/21 下午5:49
 */
class WeatherFragmentDelegate : AppDelegate(), ILoadingView{
    private var iv_weather: ImageView? = null
    private var tv_weather: TextView? = null
    private var tv_aqi:TextView? = null
    private var tv_sd:TextView? = null
    private var tv_wind_direction:TextView? = null
    private var tv_wind_power:TextView? = null
    private var tv_temperature_time:TextView? = null
    private var tv_temperature: TextView? = null

    private var ll_dialog_holder: LinearLayout? = null

    @BindView(R.id.progress_layout)
    lateinit var progress_layout: ProgressLayout

    @BindView(R.id.et_location)
    lateinit var et_location: EditText



    override fun showLoading() {
        if (!(progress_layout.isContent())) {
            progress_layout.showContent()
        }
    }

    override fun showContent() {
        if (!progress_layout.isContent()) {
            progress_layout.showContent()
        }
    }

    override fun showError(messageId: Int, listener: View.OnClickListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_weather
    }

    /**
     * 获取输入的地名
     * @return
     */
    fun getInputLocation(): String {
        return et_location.text.toString()
    }

    fun showNowWeatherDalog(weather: ShowApiWeather){
        ll_dialog_holder = getActivity<Activity>().layoutInflater.inflate(R.layout.dialog_weather, null) as LinearLayout?
        val holder: Holder = ViewHolder(ll_dialog_holder)
        findHolderChildView(holder)
        GlideUtil.loadImage(getActivity(), weather.now?.weather_pic!!, iv_weather!!)
        tv_weather?.text = weather.now!!.weather
        tv_temperature?.text = "${weather.now!!.temperature}℃"
        tv_temperature_time?.text = weather.now!!.temperature_time
        tv_aqi?.text = String.format(getActivity<Activity>().resources.getString(R.string.weather_dialog_aqi),weather.now!!.aqi)
        showOnlyContentDialog(holder, Gravity.BOTTOM, false)
    }

    /**
     * 查找弹窗的holder的子控件
     *
     * @param holder
     */
    private fun findHolderChildView(holder: Holder) {
        iv_weather = holder.inflatedView.findViewById<ImageView>(R.id.iv_weather)
        tv_weather = holder.inflatedView.findViewById<View>(R.id.tv_weather) as TextView
        tv_temperature = holder.inflatedView.findViewById<View>(R.id.tv_temperature) as TextView
        tv_temperature_time = holder.inflatedView.findViewById<View>(R.id.tv_temperature_time) as TextView
        tv_aqi = holder.inflatedView.findViewById<View>(R.id.tv_aqi) as TextView
        tv_sd = holder.inflatedView.findViewById<View>(R.id.tv_sd) as TextView
        tv_wind_direction = holder.inflatedView.findViewById<View>(R.id.tv_wind_direction) as TextView
        tv_wind_power = holder.inflatedView.findViewById<View>(R.id.tv_wind_power) as TextView
    }

    /**
     * 仅显示内容的dialog
     *
     * @param holder
     * @param gravity         显示位置（居中，底部，顶部）
     * @param expanded        是否支持展开（有列表时适用）
     */
    private fun showOnlyContentDialog(holder: Holder, gravity: Int,
                                      expanded: Boolean) {
        val dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(holder)
                .setGravity(gravity)
                .setExpanded(expanded)
                .setCancelable(true)
                .create()
        dialog.show()
    }

    /**
     * 关闭软键盘
     */
    fun closeSoftInput() {
        val inputMethodManager = getActivity<Activity>().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(et_location.windowToken, 0)
    }
}
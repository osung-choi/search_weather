package com.osung.idustest.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.osung.idustest.R
import com.osung.idustest.databinding.ActivityMainBinding
import com.osung.idustest.view.adapter.WeatherListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel : MainViewModel by viewModel { parametersOf("se") }
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val adapter = WeatherListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() =
        with(binding) {
            val activity = this@MainActivity

            viewModel = activity.viewModel
            lifecycleOwner = activity
            refreshLayout.setOnRefreshListener(activity)

            weatherList.adapter = adapter

            //구분선 지정
            ContextCompat.getDrawable(activity, R.drawable.list_divider)?.let { drawable ->
                val dividerDecoration = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
                dividerDecoration.setDrawable(drawable)

                weatherList.addItemDecoration(dividerDecoration)
            }

            activity.viewModel.localWeatherInfo.observe(activity, {
                progress.visibility = View.GONE
                refreshLayout.isRefreshing = false
            })
        }

    override fun onRefresh() {
        viewModel.searchWeatherInfo()
    }
}
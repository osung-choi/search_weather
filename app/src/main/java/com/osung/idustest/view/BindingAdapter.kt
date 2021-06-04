package com.osung.idustest.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osung.idustest.domain.entity.WeatherEntity
import com.osung.idustest.view.adapter.WeatherListAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:setWeatherInfoList")
    fun setWeatherInfoList(recyclerView: RecyclerView, weatherInfoList: List<WeatherEntity>?) {
        weatherInfoList?.let {
            (recyclerView.adapter as? WeatherListAdapter)?.addHeaderAndSubmitList(weatherInfoList)
        }
    }

    @JvmStatic
    @BindingAdapter("app:loadImage")
    fun loadImage(imageView: ImageView, src: String?) {
        src?.let {
            Glide.with(imageView)
                .load("https://www.metaweather.com/static/img/weather/png/64/${it}.png")
                .into(imageView)
        }
    }
}
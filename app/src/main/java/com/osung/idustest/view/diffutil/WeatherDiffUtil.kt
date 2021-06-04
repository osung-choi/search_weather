package com.osung.idustest.view.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.osung.idustest.view.adapter.WeatherListData

class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherListData>() {
    override fun areItemsTheSame(oldItem: WeatherListData, newItem: WeatherListData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WeatherListData, newItem: WeatherListData): Boolean {
        return oldItem == newItem
    }
}
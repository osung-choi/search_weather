package com.osung.idustest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.osung.idustest.databinding.ItemWeatherInfoBinding
import com.osung.idustest.databinding.ItemWeatherTitleBinding
import com.osung.idustest.domain.entity.WeatherEntity
import com.osung.idustest.view.diffutil.WeatherDiffUtil

class WeatherListAdapter :
    ListAdapter<WeatherListData, RecyclerView.ViewHolder>(WeatherDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> WeatherTitleViewHolder(ItemWeatherTitleBinding.inflate(inflater, parent, false))
            else -> WeatherInfoViewHolder(ItemWeatherInfoBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? WeatherInfoViewHolder)?.bind(getItem(position) as WeatherListData.WeatherItem)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WeatherListData.Header -> ITEM_VIEW_TYPE_HEADER
            is WeatherListData.WeatherItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    inner class WeatherTitleViewHolder(
        binding: ItemWeatherTitleBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class WeatherInfoViewHolder(
        private val binding: ItemWeatherInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherInfoItem: WeatherListData.WeatherItem) {
            binding.weatherInfo = weatherInfoItem.weatherEntity
        }
    }

    /**
     * 해더 추가하여 submitList
     *
     * @param list 날씨 데이터
     */
    fun addHeaderAndSubmitList(list: List<WeatherEntity>?) {
        val item = when(list) {
            null -> listOf(WeatherListData.Header)
            else -> listOf(WeatherListData.Header) + list.map { WeatherListData.WeatherItem(it) }
        }

        submitList(item)
    }

    companion object {
        const val ITEM_VIEW_TYPE_HEADER = 0
        const val ITEM_VIEW_TYPE_ITEM = 1
    }
}
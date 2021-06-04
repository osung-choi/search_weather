package com.osung.idustest.view.adapter

import com.osung.idustest.domain.entity.WeatherEntity

sealed class WeatherListData {
    abstract val id : Int

    data class WeatherItem(val weatherEntity: WeatherEntity): WeatherListData() {
        override val id = weatherEntity.id
    }

    object Header : WeatherListData() {
        override val id = Int.MIN_VALUE
    }
}
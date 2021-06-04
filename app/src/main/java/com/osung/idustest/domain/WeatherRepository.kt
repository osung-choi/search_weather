package com.osung.idustest.domain

import com.osung.idustest.domain.entity.WeatherEntity
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    /**
     * 입력된 단어를 포함하는 지역의 날씨 정보를 얻어온다.
     *
     * @param query
     * @return
     */
    fun getWeatherInfoQueryLocation(query: String): Single<List<WeatherEntity>>
}
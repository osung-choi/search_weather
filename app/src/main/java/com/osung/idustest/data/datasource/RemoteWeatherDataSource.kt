package com.osung.idustest.data.datasource

import com.osung.idustest.data.model.ResponseLocationList
import com.osung.idustest.data.model.ResponseWeatherInfo
import io.reactivex.rxjava3.core.Single

interface RemoteWeatherDataSource {
    /**
     * 단어가 포함된 지역 정보를 요청한다.
     *
     * @param query
     * @return
     */
    fun requestSearchLocation(query: String) : Single<ResponseLocationList>

    /**
     * 지역 id에 해당하는 날씨 정보를 요청한다.
     *
     * @param woeid
     * @return
     */
    fun requestLocationWeatherInfo(woeid: Int) : Single<ResponseWeatherInfo>
}
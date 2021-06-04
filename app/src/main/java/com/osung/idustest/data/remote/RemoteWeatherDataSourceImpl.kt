package com.osung.idustest.data.remote

import com.osung.idustest.data.datasource.RemoteWeatherDataSource
import com.osung.idustest.data.model.ResponseLocationList
import com.osung.idustest.data.model.ResponseWeatherInfo
import io.reactivex.rxjava3.core.Single

class RemoteWeatherDataSourceImpl(
    private val api: Api) : RemoteWeatherDataSource {

    override fun requestSearchLocation(query: String): Single<ResponseLocationList> {
        return api.getLocationSearch(query)
    }

    override fun requestLocationWeatherInfo(woeid: Int): Single<ResponseWeatherInfo> {
        return api.getLocationWeatherInfo(woeid)
    }
}
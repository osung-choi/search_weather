package com.osung.idustest.data.remote

import com.osung.idustest.data.model.ResponseLocationList
import com.osung.idustest.data.model.ResponseWeatherInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/api/location/search/")
    fun getLocationSearch(@Query("query") query: String): Single<ResponseLocationList>

    @GET("/api/location/{woeid}/")
    fun getLocationWeatherInfo(@Path("woeid") woeid: Int): Single<ResponseWeatherInfo>

    companion object {
        const val baseUrl = "https://www.metaweather.com"
    }
}

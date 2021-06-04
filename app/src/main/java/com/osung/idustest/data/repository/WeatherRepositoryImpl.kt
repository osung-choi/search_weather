package com.osung.idustest.data.repository

import com.osung.idustest.data.datasource.RemoteWeatherDataSource
import com.osung.idustest.data.model.mapper
import com.osung.idustest.domain.WeatherRepository
import com.osung.idustest.domain.entity.WeatherEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherRepositoryImpl(
    private val remote: RemoteWeatherDataSource
) : WeatherRepository {

    override fun getWeatherInfoQueryLocation(query: String): Single<List<WeatherEntity>> {
        return remote.requestSearchLocation(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapObservable { response -> Observable.fromIterable(response) }
            .concatMapEager { item -> getLocationWeather(item.woeid) }
            .toList()
    }

    private fun getLocationWeather(woeid: Int): Observable<WeatherEntity> {
        return remote.requestLocationWeatherInfo(woeid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response -> response.mapper() }
            .toObservable()
    }
}
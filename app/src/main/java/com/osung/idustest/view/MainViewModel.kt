package com.osung.idustest.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osung.idustest.domain.WeatherRepository
import com.osung.idustest.domain.entity.WeatherEntity

class MainViewModel(
    private val query: String,
    private val repository: WeatherRepository) : ViewModel() {

    private val _loadingWeatherData = MutableLiveData<Boolean>()
    val loadingWeatherData: LiveData<Boolean> = _loadingWeatherData

    private val _localWeatherInfo = MutableLiveData<List<WeatherEntity>>().also {
        searchWeatherInfo()
    }
    val localWeatherInfo: LiveData<List<WeatherEntity>> = _localWeatherInfo

    /**
     * 날씨 정보 검색
     *
     */
    fun searchWeatherInfo() {
        _loadingWeatherData.value = false

        repository.getWeatherInfoQueryLocation(query)
            .subscribe({
                _localWeatherInfo.value = it
                _loadingWeatherData.value = true
            }, {
                Log.d("error", it.message!!)
            })
    }
}
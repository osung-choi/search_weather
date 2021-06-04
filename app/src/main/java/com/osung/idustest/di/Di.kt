package com.osung.idustest.di

import com.osung.idustest.data.datasource.RemoteWeatherDataSource
import com.osung.idustest.data.remote.Api
import com.osung.idustest.data.remote.RemoteWeatherDataSourceImpl
import com.osung.idustest.data.repository.WeatherRepositoryImpl
import com.osung.idustest.domain.WeatherRepository
import com.osung.idustest.view.MainViewModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { params ->
        MainViewModel(params.get(), get())
    }
}

val dataModule = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }

    single<RemoteWeatherDataSource> {
        RemoteWeatherDataSourceImpl(get())
    }
}

val networkModule = module {
    single { GsonConverterFactory.create() as Converter.Factory }
    single { RxJava3CallAdapterFactory.create() as CallAdapter.Factory }

    single {
        Retrofit.Builder()
            .addConverterFactory(get())
            .baseUrl(Api.baseUrl)
            .addCallAdapterFactory(get())
            .build()
            .create(Api::class.java)
    }
}
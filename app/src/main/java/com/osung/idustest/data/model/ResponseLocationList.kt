package com.osung.idustest.data.model
import com.google.gson.annotations.SerializedName


class ResponseLocationList : ArrayList<LocationItem>()

data class LocationItem(
    @SerializedName("latt_long")
    val lattLong: String,
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("woeid")
    val woeid: Int
)
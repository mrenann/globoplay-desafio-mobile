package com.mrenann.globoplay.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class TVResult(
    @SerializedName("first_air_date")
    val firstAirDate: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerializedName("original_name")
    val originalName: String = ""
) : MediaResult()
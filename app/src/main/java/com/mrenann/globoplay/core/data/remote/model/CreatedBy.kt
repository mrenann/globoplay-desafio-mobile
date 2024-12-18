package com.mrenann.globoplay.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("credit_id")
    val creditId: String = "",
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("profile_path")
    val profilePath: String = ""
)
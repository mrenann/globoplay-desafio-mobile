package com.mrenann.globoplay.core.util

import com.mrenann.globoplay.BuildConfig

private val BASE_IMAGE_URL = BuildConfig.BASE_IMAGE_URL
val BASE_AVATAR_URL = "https://picsum.photos/200"

fun String?.toPosterUrl() = "${BASE_IMAGE_URL}$this"
fun String?.toBackdropUrl() = "${BASE_IMAGE_URL}$this"
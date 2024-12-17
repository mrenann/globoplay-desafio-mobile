package com.mrenann.globoplay.core.util

import com.mrenann.globoplay.BuildConfig

private const val BASE_IMAGE_URL = BuildConfig.BASE_IMAGE_URL

fun String?.toPosterUrl() = "${BASE_IMAGE_URL}$this"
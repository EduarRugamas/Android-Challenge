package com.thedevexperto.test_android_challenge.Data

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
    @SerializedName("description") val description: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("source") val source: Source,
    @SerializedName("title") val title: String,
    @SerializedName("url") val urlWeb: String,
    @SerializedName("urlToImage") val urlToImage: String
)
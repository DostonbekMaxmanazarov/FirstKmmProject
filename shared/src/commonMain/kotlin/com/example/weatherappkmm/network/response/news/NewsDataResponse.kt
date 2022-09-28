package com.example.weatherappkmm.network.response.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    var id: String? = null,
    var name: String? = null
)

@Serializable
data class Article (
    @SerialName("source")
    var sourceNews: Source? = null,
    @SerialName("author")
    var authorNews: String? = null,
    @SerialName("title")
    var titleNews: String? = null,
    @SerialName("description")
    var descriptionNews: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var content: String? = null
)

@Serializable
data class NewsDataResponse (
    var status: String? = null,
    var totalResults: Int = 0,
    var articles: ArrayList<Article>? = null,
)
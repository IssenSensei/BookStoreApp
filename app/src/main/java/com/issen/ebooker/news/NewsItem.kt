package com.issen.ebooker.news

import java.io.Serializable

data class NewsItem (
    var id: Int = -1,
    var content: String = "",
    var title: String = "",
    var bookStore: String = "",
    var photo: String = ""
): Serializable


package com.issen.ebooker.data.domain

import java.io.Serializable

data class Shelf(
    val shelfId: String,
    val title: String,
    val volumeCount: Int?
): Serializable

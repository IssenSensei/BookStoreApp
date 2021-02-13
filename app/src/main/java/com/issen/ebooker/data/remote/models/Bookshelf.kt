package com.issen.ebooker.data.remote.models

data class Bookshelf(
    val kind: String,
    val id: Int,
    val title: String,
    val access: String,
    val updated: String,
    val created: String,
    val volumeCount: Int?,
    val volumesLastUpdated: String
)

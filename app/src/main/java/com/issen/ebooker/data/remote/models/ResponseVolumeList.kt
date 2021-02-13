package com.issen.ebooker.data.remote.models

data class ResponseVolumeList(
    val kind: String,
    val totalItems: Int,
    val items: List<Volume>?
)



package com.issen.ebooker.data.domain

import java.io.Serializable

data class DomainImageLinks(
    val id: Int,
    val smallThumbnail: String?,
    val thumbnail: String?
): Serializable

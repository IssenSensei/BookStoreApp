package com.issen.ebooker.data.domain

import java.io.Serializable

data class DomainEpub(
    val id : Int,
    val isAvailable: Boolean,
    val acsTokenLink: String?
): Serializable

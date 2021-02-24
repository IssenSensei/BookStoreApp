package com.issen.ebooker.data.domain

import java.io.Serializable

data class Quotation(
    val quotationId: String = "",
    val content: String = ""
) : Serializable

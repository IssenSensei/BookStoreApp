package com.issen.ebooker.data.domain

import java.io.Serializable

data class Book(
    val bookId: String,
    val title: String,
    val authors: List<String>?,
    val publisher: String?,
    val description: String?,
    val domainImageLinks: DomainImageLinks?,
    val domainPdf: DomainPdf,
    val domainEpub: DomainEpub
) : Serializable {
    fun getShorterDescription(): String {
        return if (description != null) {
            if (description.length > 200) {
                description.take(200) + "..."
            } else {
                description
            }
        } else {
            ""
        }
    }
}
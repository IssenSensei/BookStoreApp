package com.issen.ebooker.data.domain

data class Book(
    val bookId: String,
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val description: String,
    val domainImageLinks: DomainImageLinks,
    val domainPdf: DomainPdf,
    val domainEpub: DomainEpub
)
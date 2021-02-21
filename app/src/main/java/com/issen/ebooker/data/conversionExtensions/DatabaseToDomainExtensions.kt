package com.issen.ebooker.data.conversionExtensions

import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.data.domain.DomainImageLinks
import com.issen.ebooker.data.local.models.*

fun DatabaseImageLinks.asDomainImageLinks(): DomainImageLinks {
    return DomainImageLinks(id, smallThumbnail, thumbnail)
}


fun List<DatabaseBook>.asDomainModel(): List<Book> {
    return map {
        Book(
            bookId = it.databaseBookItem.bookId,
            title = it.databaseBookItem.title,
            authors = it.databaseBookItem.authors,
            publisher = it.databaseBookItem.publisher,
            description = it.databaseBookItem.description,
            domainImageLinks = it.databaseImageLinks?.asDomainImageLinks()
        )
    }
}

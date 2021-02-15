package com.issen.ebooker.data.conversionExtensions

import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.data.domain.DomainEpub
import com.issen.ebooker.data.domain.DomainImageLinks
import com.issen.ebooker.data.domain.DomainPdf
import com.issen.ebooker.data.local.models.*

fun DatabasePdf.asDomainPdf(): DomainPdf {
    return DomainPdf(id, isAvailable, acsTokenLink)
}

fun DatabaseImageLinks.asDomainImageLinks(): DomainImageLinks {
    return DomainImageLinks(id, smallThumbnail, thumbnail)
}

fun DatabaseEpub.asDomainEpub(): DomainEpub {
    return DomainEpub(id, isAvailable, acsTokenLink)
}

fun List<DatabaseBook>.asDomainModel(): List<Book> {
    return map {
        Book(
            bookId = it.databaseBookItem.bookId,
            title = it.databaseBookItem.title,
            authors = it.databaseBookItem.authors,
            publisher = it.databaseBookItem.publisher,
            description = it.databaseBookItem.description,
            domainImageLinks = it.databaseImageLinks?.asDomainImageLinks(),
            domainPdf = it.databasePdf.asDomainPdf(),
            domainEpub = it.databaseEpub.asDomainEpub()
        )
    }
}

package com.issen.ebooker.data.conversionExtensions

import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.data.domain.DomainEpub
import com.issen.ebooker.data.domain.DomainImageLinks
import com.issen.ebooker.data.domain.DomainPdf
import com.issen.ebooker.data.remote.models.*

fun Epub.asDomainEpub(): DomainEpub {
    return DomainEpub(0, isAvailable, acsTokenLink)
}

fun ImageLinks.asDomainImageLinks(): DomainImageLinks {
    return DomainImageLinks(
        0,
        smallThumbnail = smallThumbnail,
        thumbnail = thumbnail
    )
}

fun Pdf.asDomainPdf(): DomainPdf {
    return DomainPdf(0, isAvailable, acsTokenLink)
}

fun BooksApiResponse.asDomainModel(): List<Book> {
    return items.map {
        Book(
            bookId = it.id,
            title = it.volumeInfo.title,
            authors = it.volumeInfo.authors,
            publisher = it.volumeInfo.publisher,
            description = it.volumeInfo.description,
            domainImageLinks = it.volumeInfo.imageLinks.asDomainImageLinks(),
            domainPdf = it.accessInfo.pdf.asDomainPdf(),
            domainEpub = it.accessInfo.epub.asDomainEpub()
        )
    }
}

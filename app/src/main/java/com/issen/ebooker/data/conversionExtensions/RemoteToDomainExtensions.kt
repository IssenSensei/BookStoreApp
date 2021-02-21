package com.issen.ebooker.data.conversionExtensions

import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.data.domain.DomainImageLinks
import com.issen.ebooker.data.remote.models.ResponseVolumeList
import com.issen.ebooker.data.remote.models.ImageLinks

fun ImageLinks.asDomainImageLinks(): DomainImageLinks {
    return DomainImageLinks(
        0,
        smallThumbnail = smallThumbnail,
        thumbnail = thumbnail
    )
}

fun ResponseVolumeList.asDomainModel(): List<Book> {
    
    return items?.map {
        Book(
            bookId = it.id,
            title = it.volumeInfo.title,
            authors = it.volumeInfo.authors,
            publisher = it.volumeInfo.publisher,
            description = it.volumeInfo.description,
            domainImageLinks = it.volumeInfo.imageLinks?.asDomainImageLinks()
        )
    } ?: listOf()
}

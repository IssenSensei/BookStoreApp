package com.issen.ebooker.data.conversionExtensions

import com.issen.ebooker.data.local.models.*
import com.issen.ebooker.data.remote.models.*

fun Epub.asDatabaseEpub(): DatabaseEpub {
    return DatabaseEpub(0, isAvailable, acsTokenLink)
}

fun ImageLinks.asDatabaseImageLinks(): DatabaseImageLinks {
    return DatabaseImageLinks(
        0,
        smallThumbnail = smallThumbnail,
        thumbnail = thumbnail
    )
}

fun Volume.asDatabaseBookItem(pdfId: Int, epubId: Int, imageLinksId: Int): DatabaseBookItem {
    return DatabaseBookItem(
        bookId = id,
        title = volumeInfo.title,
        authors = volumeInfo.authors,
        publisher = volumeInfo.publisher,
        description = volumeInfo.description,
        pdfId = pdfId,
        epubId = epubId,
        imageLinksId = imageLinksId
    )
}


fun Pdf.asDatabasePdf(): DatabasePdf {
    return DatabasePdf(0, isAvailable, acsTokenLink)
}

fun BooksApiResponse.asDatabaseModel(): List<DatabaseBook> {
    return items.map {
        DatabaseBook(
            DatabaseBookItem(
                it.id,
                it.volumeInfo.title,
                it.volumeInfo.authors,
                it.volumeInfo.publisher,
                it.volumeInfo.description,
                null,
                null,
                null
            ),
            DatabasePdf(
                0,
                it.accessInfo.pdf.isAvailable,
                it.accessInfo.pdf.acsTokenLink
            ),
            DatabaseEpub(
                0,
                it.accessInfo.epub.isAvailable,
                it.accessInfo.epub.acsTokenLink
            ),
            DatabaseImageLinks(
                0,
                it.volumeInfo.imageLinks.smallThumbnail,
                it.volumeInfo.imageLinks.thumbnail
            )
        )
    }
}
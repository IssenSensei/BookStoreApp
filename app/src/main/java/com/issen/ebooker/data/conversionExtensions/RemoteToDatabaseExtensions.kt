package com.issen.ebooker.data.conversionExtensions

import com.issen.ebooker.data.local.models.*
import com.issen.ebooker.data.remote.models.*

fun ImageLinks.asDatabaseImageLinks(): DatabaseImageLinks {
    return DatabaseImageLinks(
        0,
        smallThumbnail = smallThumbnail,
        thumbnail = thumbnail
    )
}

fun Volume.asDatabaseBookItem(imageLinksId: Int?): DatabaseBookItem {
    return DatabaseBookItem(
        bookId = id,
        title = volumeInfo.title,
        authors = volumeInfo.authors,
        publisher = volumeInfo.publisher,
        description = volumeInfo.description,
        imageLinksId = imageLinksId
    )
}

fun Volume.asDatabaseUserBookItem(shelfIid: Int, uid: String): DatabaseUserBookItem {
    return DatabaseUserBookItem(
        id,
        shelfIid,
        uid
    )
}

fun ResponseVolumeList.asDatabaseModel(): List<DatabaseBook> {
    return items?.map {
        DatabaseBook(
            DatabaseBookItem(
                it.id,
                it.volumeInfo.title,
                it.volumeInfo.authors,
                it.volumeInfo.publisher,
                it.volumeInfo.description,
                null
            ),
            DatabaseImageLinks(
                0,
                it.volumeInfo.imageLinks?.smallThumbnail,
                it.volumeInfo.imageLinks?.thumbnail
            )
        )
    } ?: listOf()
}

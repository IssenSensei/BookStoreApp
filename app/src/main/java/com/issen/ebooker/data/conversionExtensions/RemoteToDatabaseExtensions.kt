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

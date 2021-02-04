package com.issen.ebooker.comments

import java.io.Serializable

data class NewCommentItem (
    var content: String = "",
    var rating: Int = 0,
    var bookId: Int = 0,
    var userId: Int = 0
): Serializable


package com.issen.ebooker.comments

import java.io.Serializable

data class CommentItem (
    var id: Int = 0,
    var content: String = "",
    var rating: Int = 0,
    var username: String = ""
): Serializable


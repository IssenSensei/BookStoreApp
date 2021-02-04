package com.issen.ebooker.user

import java.io.Serializable

data class UserItem (
    var id: Int = 0,
    var password: String = "",
    var name: String = "",
    var surname: String = "",
    var email: String = ""

): Serializable


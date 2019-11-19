package com.example.bookstoreapp.user

import java.io.Serializable

data class UserItem (
    var id: Int = 0,
    var login: String = "",
    var password: String = "",
    var name: String = "",
    var lastName: String = "",
    var email: String = "",
    var phone: String = ""

): Serializable


package com.issen.ebooker.data.domain

import java.io.Serializable

data class User(
    val userId: String,
    val userName: String
): Serializable

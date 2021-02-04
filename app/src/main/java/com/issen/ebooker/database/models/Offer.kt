package com.issen.ebooker.database.models

data class Offer(
    val finskyOfferType: Int,
    val listPriceMicros: ListPriceMicros,
    val retailPrice: RetailPriceMicros,
    val giftable: Boolean
)
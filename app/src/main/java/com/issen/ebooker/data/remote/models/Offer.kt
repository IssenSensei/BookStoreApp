package com.issen.ebooker.data.remote.models

data class Offer(
    val finskyOfferType: Int,
    val listPriceMicros: ListPriceMicros,
    val retailPrice: RetailPriceMicros,
    val giftable: Boolean
)
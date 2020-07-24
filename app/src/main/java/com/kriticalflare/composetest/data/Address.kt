package com.kriticalflare.composetest.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "city")
    val city: String,
    @Json(name = "geo")
    val geo: Geo,
    @Json(name = "street")
    val street: String,
    @Json(name = "suite")
    val suite: String,
    @Json(name = "zipcode")
    val zipcode: String
)
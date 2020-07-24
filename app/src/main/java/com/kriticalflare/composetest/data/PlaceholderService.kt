package com.kriticalflare.composetest.data

import retrofit2.http.GET

interface PlaceholderService {

    @GET("users")
    suspend fun fetchUsers(): List<UserItem>
}
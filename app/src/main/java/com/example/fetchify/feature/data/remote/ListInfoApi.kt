package com.example.fetchify.feature.data.remote

import com.example.fetchify.feature.data.remote.dto.ListInfoDto
import retrofit2.Response
import retrofit2.http.GET

interface ListInfoApi {

    @GET("hiring.json")
    suspend fun getListInfo(): List<ListInfoDto>

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }
}

package com.example.fetchify.feature.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ListInfoApi {


    @GET("/api/v2/entries/en/{list}")
    suspend fun getListInfoTrial(
        @Path("list") list: String  // `{list}` in the API call gets replaced by the list identifier here
    ): Response<List<ListInfoDto>>

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }
}
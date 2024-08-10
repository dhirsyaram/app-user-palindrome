package com.kuliah.apppalindromeuser.data.retrofit

import com.kuliah.apppalindromeuser.data.response.ResponseUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getListUser(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Response<ResponseUser>
}
package com.example.githubuser.data.retrofit

import com.example.githubuser.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUsername(
        @Query("q") q: String
    ): Call<GithubResponse>
}
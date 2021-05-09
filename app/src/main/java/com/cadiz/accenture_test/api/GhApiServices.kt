package com.cadiz.accenture_test.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GhApiServices {
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getRepository(
        @Query("page") page: Int
    ) : RepoJsonResponse


    @GET("repos/{creador}/{repo}/pulls")
    suspend fun getPullRequest(
        @Path("creador") creador: String,
        @Path("repo") repo: String

        ) :MutableList<PullJsonResponse>
}
private var retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

var service: GhApiServices = retrofit.create<GhApiServices>(GhApiServices::class.java)


package com.yashrajsinh.gitprofile.api

import com.yashrajsinh.gitprofile.model.MDetailsUserResponse
import com.yashrajsinh.gitprofile.model.MUser
import com.yashrajsinh.gitprofile.model.MUserResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<MUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetails(
        @Path("username") username: String
    ): Call<MDetailsUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<MUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<MUser>>
}
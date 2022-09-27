package com.yashrajsinh.gitprofile.api

import com.yashrajsinh.gitprofile.model.MDetailsUserResponse
import com.yashrajsinh.gitprofile.model.MUser
import com.yashrajsinh.gitprofile.model.MUserResponse
import retrofit2.Call
import retrofit2.http.*
/*
 APIS To Fetch each data
 */
interface Api {
    //TODO: API FOR SEARCHING
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<MUserResponse>

    //TODO: API FOR USERNAME
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetails(
        @Path("username") username: String
    ): Call<MDetailsUserResponse>

    //TODO: API FOR FOLLOWERS
    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<MUser>>

    //TODO:API FOR FOLLOWING
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<MUser>>
}
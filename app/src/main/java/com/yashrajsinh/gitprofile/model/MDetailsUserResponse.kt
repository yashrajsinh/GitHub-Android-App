package com.yashrajsinh.gitprofile.model

/*
Simple model class to show data
Avatar
Name
Description
Follower count, i.e. X followers
Following count, i.e. X following
 */
data class MDetailsUserResponse(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val name: String,
    val bio: String,
    val following: Int,
    val followers: Int
)

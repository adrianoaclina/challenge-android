package com.adrianoaclina.challenge.api.service

import com.adrianoaclina.challenge.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}


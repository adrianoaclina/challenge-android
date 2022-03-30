package com.adrianoaclina.challenge.api.webClient

import com.adrianoaclina.challenge.api.RetrofitInitializer
import com.adrianoaclina.challenge.api.service.PostService
import com.adrianoaclina.challenge.api.service.executeRequest
import com.adrianoaclina.challenge.model.ErrorBody
import com.adrianoaclina.challenge.model.Post

class PostWebClient (
    private val service: PostService = RetrofitInitializer().postService
){

    fun getPosts(
        isSuccess: (posts: List<Post>?) -> Unit,
        isError: (error: ErrorBody?) -> Unit
    ){
        executeRequest(
            service.getPosts(),
            isSuccess = isSuccess,
            isError
        )
    }
}
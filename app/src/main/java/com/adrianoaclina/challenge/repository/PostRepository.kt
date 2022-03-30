package com.adrianoaclina.challenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adrianoaclina.challenge.api.service.resource.Resource
import com.adrianoaclina.challenge.api.webClient.PostWebClient
import com.adrianoaclina.challenge.database.dao.PostDAO
import com.adrianoaclina.challenge.model.Post

class PostRepository(
    private val dao: PostDAO,
    private val webClient: PostWebClient
) {

    fun getPosts(): LiveData<Resource<List<Post>>> {

        val liveData = MutableLiveData<Resource<List<Post>>>()

        webClient.getPosts(
            isSuccess = {
                it.let {
                    liveData.value = Resource(data = it)
                }
            },
            isError = {
                it?.let {
                    liveData.value = Resource(data = null, error = it)
                }
            }
        )

        return liveData
    }

    fun getLocalPosts(): LiveData<List<Post>> = dao.selectPosts()

    fun insertPost(post: Post){
        dao.insertPost(post)
    }
}
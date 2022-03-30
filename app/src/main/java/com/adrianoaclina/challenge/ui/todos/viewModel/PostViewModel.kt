package com.adrianoaclina.challenge.ui.todos.viewModel

import androidx.lifecycle.ViewModel
import com.adrianoaclina.challenge.model.Post
import com.adrianoaclina.challenge.repository.PostRepository

class PostViewModel(
    private val repository: PostRepository
): ViewModel() {

    fun getPosts() = repository.getPosts()

    fun getLocalPosts() = repository.getLocalPosts()

    fun insertPosts(posts: Post){
        repository.insertPost(posts)
    }
}
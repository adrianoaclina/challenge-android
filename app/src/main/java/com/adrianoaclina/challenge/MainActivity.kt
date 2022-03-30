package com.adrianoaclina.challenge

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adrianoaclina.challenge.database.AppDatabase
import com.adrianoaclina.challenge.database.dao.PostDAO
import com.adrianoaclina.challenge.model.Post
import com.adrianoaclina.challenge.ui.todos.adapter.OpenPost
import com.adrianoaclina.challenge.ui.todos.adapter.PostListAdapter
import com.adrianoaclina.challenge.ui.todos.viewModel.PostViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val adapter: PostListAdapter by inject { parametersOf(this) }

    private val viewModel: PostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
    }

    override fun onResume() {
        super.onResume()
        getTodos()
    }

    private fun getTodos() {
        viewModel.getPosts().observe(this, { response ->
            response.data?.let {
                setupTodoList(it)
            } ?: kotlin.run {
                response.error?.let { error ->
                    if (error.noConnection){
                        getSavedList()
                    }else{
                        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                    }
                }
            }

        })
    }

    private fun getSavedList() {
        viewModel.getLocalPosts().observe(this, { posts ->
            if (posts.isNotEmpty()){
                setupTodoList(posts, true)
            }else{
                Toast.makeText(this, "Post list is empty!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupTodoList(posts: List<Post>, isLocal: Boolean = false) {

        if (posts.isNotEmpty()){
            val listPosts = ArrayList<OpenPost>()
            for (post in posts) {
                if (!isLocal && post.id < 11){
                    viewModel.insertPosts(post)
                }
                listPosts.add(OpenPost(post, false))
            }
            adapter.refresh(listPosts)
        }

    }

    private fun setupAdapter() {
        activity_main_rv_todos.adapter = adapter
    }
}
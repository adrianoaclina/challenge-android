package com.adrianoaclina.challenge.di

import android.app.Application
import androidx.room.Room
import com.adrianoaclina.challenge.api.webClient.PostWebClient
import com.adrianoaclina.challenge.database.AppDatabase
import com.adrianoaclina.challenge.database.dao.PostDAO
import com.adrianoaclina.challenge.repository.PostRepository
import com.adrianoaclina.challenge.ui.todos.adapter.PostListAdapter
import com.adrianoaclina.challenge.ui.todos.viewModel.PostViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "challenge.db"

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).allowMainThreadQueries().build()
    }
}

val daoModule = module {
    single<PostDAO> { get<AppDatabase>().postDao() }
    single<PostWebClient> { PostWebClient() }
    single<PostRepository> { PostRepository(get(), get()) }
}

val uiModule = module {
    factory<PostListAdapter> {
        PostListAdapter(get())
    }
}

val viewModelModule = module {
    viewModel<PostViewModel> { PostViewModel(get()) }
}
package com.adrianoaclina.challenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adrianoaclina.challenge.database.dao.PostDAO
import com.adrianoaclina.challenge.model.Post

@Database(
    version = 1,
    entities = [Post::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDAO
}
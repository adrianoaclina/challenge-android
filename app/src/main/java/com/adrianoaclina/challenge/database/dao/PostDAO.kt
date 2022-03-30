package com.adrianoaclina.challenge.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adrianoaclina.challenge.model.Post

@Dao
interface PostDAO {

    @Query("SELECT * FROM Post")
    fun selectPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(vararg posts: Post)

}
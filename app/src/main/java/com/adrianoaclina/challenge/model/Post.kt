package com.adrianoaclina.challenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val title: String,
    val body: String
)
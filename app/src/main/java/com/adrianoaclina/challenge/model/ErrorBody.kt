package com.adrianoaclina.challenge.model

data class ErrorBody(
    val message: String ? = null,
    val code: Int? = null,
    val noConnection: Boolean = false
)
package com.adrianoaclina.challenge.api.service.resource

import com.adrianoaclina.challenge.model.ErrorBody

class Resource<T>(
    var data: T?,
    var error: ErrorBody? = null
)
package com.adrianoaclina.challenge.api.service

import com.adrianoaclina.challenge.model.ErrorBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

fun <T> executeRequest(
    call: Call<T>,
    isSuccess: (success: T?) -> Unit,
    isError: (error: ErrorBody?) -> Unit
) {
    call.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                isSuccess(response.body())
            } else {
                val jObjError: JSONObject
                try {
                    jObjError = JSONArray(response.errorBody()!!.string()).getJSONObject(0)
                    if (response.code() < 500) {
                        isError(ErrorBody(message = Objects.requireNonNull(jObjError).getString("message"), code = response.code()))
                    } else {
                        try {
                            isError(ErrorBody(message = jObjError.getString("message"), code = response.code()))
                        } catch (e: JSONException) {
                            isError(ErrorBody(message = "Server Error!\nTry again later!", code = response.code()))
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            isError(ErrorBody(message = t.message.toString(), code = null, noConnection = true))
        }
    })
}
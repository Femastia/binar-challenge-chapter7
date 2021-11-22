package com.ihsan.binarchallengechapter7.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihsan.binarchallengechapter7.model.BadRequestLogin
import com.ihsan.binarchallengechapter7.model.ErrorsLogin
import com.ihsan.binarchallengechapter7.model.SuccessLogin
import com.ihsan.binarchallengechapter7.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName

    private val _successLogin = MutableLiveData<SuccessLogin>()
    val successLogin: LiveData<SuccessLogin> = _successLogin

    private val _unSuccessLogin = MutableLiveData<ErrorsLogin>()
    val unSuccessLogin: LiveData<ErrorsLogin> = _unSuccessLogin

    private val _responseBadRequest = MutableLiveData<BadRequestLogin>()
    val responseBadRequest: LiveData<BadRequestLogin> = _responseBadRequest

    fun userLogin(username: String, email: String) {
        Log.i(TAG, "fun userLogin: running... username =$username email =$email")
        ApiClient.getInstanceApiService().loginUser(username, email)
            .enqueue(object : Callback<SuccessLogin> {
                override fun onResponse(
                    call: Call<SuccessLogin>,
                    response: Response<SuccessLogin>
                ) {
                    when(response.code()) {
                        200 -> _successLogin.value = response.body()
                        400 -> badRequest(response)
                        else -> emailOrPassTypo(response)
                    }
                }

                override fun onFailure(call: Call<SuccessLogin>, t: Throwable) {
                    Log.i(TAG, "onFailure userLogin: ${t.message}")
                }
            })
    }

    private fun emailOrPassTypo(response: Response<SuccessLogin>) {
        val gson = Gson()
        val errorBody = response.errorBody() ?: return
        val type = object : TypeToken<ErrorsLogin>() {}.type
        val errorsResponse: ErrorsLogin = gson.fromJson(errorBody.charStream(), type)
        _unSuccessLogin.value = errorsResponse
    }

    private fun badRequest(response: Response<SuccessLogin>) {
        val gson = Gson()
        val errorBody = response.errorBody() ?: return
        val type = object : TypeToken<BadRequestLogin>() {}.type
        val badRequestResponse: BadRequestLogin = gson.fromJson(errorBody.charStream(), type)
        _responseBadRequest.value = badRequestResponse
    }
}
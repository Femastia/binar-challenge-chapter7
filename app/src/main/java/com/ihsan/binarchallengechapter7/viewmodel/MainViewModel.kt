package com.ihsan.binarchallengechapter7.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ihsan.binarchallengechapter7.model.*
import com.ihsan.binarchallengechapter7.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName
    private val gson = Gson()

    //Login
    private val _successLogin = MutableLiveData<SuccessLogin>()
    val successLogin: LiveData<SuccessLogin> = _successLogin
    private val _unSuccessLogin = MutableLiveData<ErrorsLoginRegister>()
    val unSuccessLogin: LiveData<ErrorsLoginRegister> = _unSuccessLogin
    private val _responseBadRequest = MutableLiveData<BadRequestLogin>()
    val responseBadRequest: LiveData<BadRequestLogin> = _responseBadRequest
    //Register
    private val _successRegis = MutableLiveData<SuccessRegister>()
    val successRegis: LiveData<SuccessRegister> = _successRegis
    private val _unSuccessRegis = MutableLiveData<ErrorsLoginRegister>()
    val unSuccessRegis: LiveData<ErrorsLoginRegister> = _unSuccessRegis
    //auth
    private val _loginStatus = MutableLiveData<SuccessAuth>()
    val loginStatus: LiveData<SuccessAuth> = _loginStatus
    private val _errorLogin = MutableLiveData<ErrorAuth>()
    val errorLogin: LiveData<ErrorAuth> = _errorLogin

    fun userLogin(username: String, password: String) {
        Log.i(TAG, "fun userLogin: running... username =$username email =$password")
        ApiClient.getInstanceApiService().loginUser(username, password)
            .enqueue(object : Callback<SuccessLogin> {
                override fun onResponse(
                    call: Call<SuccessLogin>,
                    response: Response<SuccessLogin>
                ) {
                    when(response.code()) {
                        200 -> _successLogin.value = response.body()
                        400 -> badRequest(response)
                        else -> loginFalse(response)
                    }
                }

                override fun onFailure(call: Call<SuccessLogin>, t: Throwable) {
                    Log.i(TAG, "onFailure userLogin: ${t.message}")
                }
            })
    }

    fun register(email: String, username: String, password: String) {
        Log.i(TAG, "register: $email $username $password")
        ApiClient.getInstanceApiService().registerUser(email, username, password)
            .enqueue(object : Callback<SuccessRegister> {
                override fun onResponse(
                    call: Call<SuccessRegister>,
                    response: Response<SuccessRegister>
                ) {
                    //422 false: username/email duplicate
                    //201 true: success
                    Log.i(TAG, "onResponse: $response")
                    Log.i(TAG, "onResponse Body: ${response.body()}")
                    if (response.code() == 201){
                        _successRegis.value = response.body()
                    } else {
                        registerFalse(response)
                    }
                }

                override fun onFailure(call: Call<SuccessRegister>, t: Throwable) {
                    Log.i(TAG, "onFailure Register: ${t.message} ")
                }
            })
    }

    fun seasonLogin(token: String) {
        Log.i(TAG, "seasonLogin: token = $token")
        ApiClient.getInstanceApiService().authentication("Bearer $token")
            .enqueue(object : Callback<SuccessAuth> {
                override fun onResponse(call: Call<SuccessAuth>, response: Response<SuccessAuth>) {
                    Log.i(TAG, "onResponse seasonLogin: $response")
                    when(response.code()) {
                        //Success
                        200 -> _loginStatus.value = response.body()
                        //Token is expired
                        403 -> {
                            val errorBody = response.errorBody() ?: return
                            val type = object : TypeToken<ErrorAuth>() {}.type
                            val errorsResponse: ErrorAuth = gson.fromJson(errorBody.charStream(), type)
                            _errorLogin.value = errorsResponse
                        }
                        //Invalid Token
                        401 -> {
                            val errorBody = response.errorBody() ?: return
                            val type = object : TypeToken<ErrorAuth>() {}.type
                            val errorsResponse: ErrorAuth = gson.fromJson(errorBody.charStream(), type)
                            _errorLogin.value = errorsResponse
                        }
                    }
                }

                override fun onFailure(call: Call<SuccessAuth>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }

            })
    }

    private fun registerFalse(response: Response<SuccessRegister>) {
        val errorBody = response.errorBody() ?: return
        val type = object : TypeToken<ErrorsLoginRegister>() {}.type
        val errorsResponse: ErrorsLoginRegister = gson.fromJson(errorBody.charStream(), type)
        _unSuccessRegis.value = errorsResponse
    }

    private fun loginFalse(response: Response<SuccessLogin>) {
        val errorBody = response.errorBody() ?: return
        val type = object : TypeToken<ErrorsLoginRegister>() {}.type
        val errorsResponse: ErrorsLoginRegister = gson.fromJson(errorBody.charStream(), type)
        _unSuccessLogin.value = errorsResponse
    }

    private fun badRequest(response: Response<SuccessLogin>) {
        val errorBody = response.errorBody() ?: return
        val type = object : TypeToken<BadRequestLogin>() {}.type
        val badRequestResponse: BadRequestLogin = gson.fromJson(errorBody.charStream(), type)
        _responseBadRequest.value = badRequestResponse
    }
}
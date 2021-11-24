package com.ihsan.binarchallengechapter7.network

import com.ihsan.binarchallengechapter7.model.SuccessLogin
import com.ihsan.binarchallengechapter7.model.SuccessRegister
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("v1/auth/register")
    fun registerUser(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String
    ):Call<SuccessRegister>

    @FormUrlEncoded
    @POST("v1/auth/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SuccessLogin>
}
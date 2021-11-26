package com.ihsan.binarchallengechapter7.network

import com.ihsan.binarchallengechapter7.model.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

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

    @GET("v1/auth/me")
    fun authentication(
        @Header("Authorization") token: String
    ): Call<SuccessAuth>

    @GET("v1/users")
    fun getuser(
        @Header("Authorization") token: String
    ): Call<ResponseUser>

    @PUT("v1/users")
    fun updateUser(
        @Header("Authorization") token: String,
        @Body dataPutUpdate: DataPutUpdate
    ): Call<ResponsePut>

    @FormUrlEncoded
    @POST("v1/battle")
    fun gameBattle(
        @Header("Authorization") token: String,
        @Field("mode") mode: String,
        @Field("result") result: String
    ): Call<ResponseGameBattle>

    @GET("v1/battle")
    fun getBattle(@Header("Authorization") token: String): Call<ResponseGetBattle>
}
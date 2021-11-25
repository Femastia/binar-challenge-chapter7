package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class ResponseUser(

	@field:SerializedName("data")
	val data: DataUser? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataUser(

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class SuccessAuth(

	@field:SerializedName("data")
	val data: DataAuth? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataAuth(

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

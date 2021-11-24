package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class SuccessRegister(

	@field:SerializedName("data")
	val data: DataRegister? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataRegister(

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

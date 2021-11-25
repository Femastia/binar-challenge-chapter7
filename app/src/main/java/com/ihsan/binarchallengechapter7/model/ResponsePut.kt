package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class ResponsePut(

	@field:SerializedName("data")
	val data: DataPutUpdate? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataPut(

	@field:SerializedName("photo")
	var photo: String? = null,

	@field:SerializedName("_id")
	var id: String? = null,

	@field:SerializedName("email")
	var email: String? = null,

	@field:SerializedName("username")
	var username: String? = null
)

data class DataPutUpdate(

	@field:SerializedName("email")
	var email: String? = null,

	@field:SerializedName("username")
	var username: String? = null
)

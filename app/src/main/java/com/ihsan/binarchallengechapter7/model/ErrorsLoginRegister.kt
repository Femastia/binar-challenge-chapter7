package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class ErrorsLoginRegister(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("errors")
	val errors: String? = null
)

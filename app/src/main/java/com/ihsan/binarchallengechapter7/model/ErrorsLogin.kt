package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class ErrorsLogin(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("errors")
	val errors: String? = null
)

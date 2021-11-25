package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class ErrorAuth(

	@field:SerializedName("errors")
	val errors: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

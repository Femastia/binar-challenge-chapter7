package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class ResponseGetBattle(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataItem(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("mode")
	val mode: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

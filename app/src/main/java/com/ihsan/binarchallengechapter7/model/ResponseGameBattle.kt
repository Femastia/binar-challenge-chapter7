package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class ResponseGameBattle(

	@field:SerializedName("data")
	val data: DataGameBattle? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataGameBattle(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("mode")
	val mode: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

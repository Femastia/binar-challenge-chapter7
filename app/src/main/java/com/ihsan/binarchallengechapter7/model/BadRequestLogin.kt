package com.ihsan.binarchallengechapter7.model

import com.google.gson.annotations.SerializedName

data class BadRequestLogin(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("errors")
	val errors: Errors? = null
)

data class Errors(

	@field:SerializedName("query")
	val query: Query? = null,

	@field:SerializedName("body")
	val body: Body? = null,

	@field:SerializedName("params")
	val params: Params? = null
)

data class Body(

	@field:SerializedName("password")
	val password: Password? = null,

	@field:SerializedName("email")
	val email: Email? = null
)

data class Params(
	val any: Any? = null
)

data class Password(

	@field:SerializedName("in")
	val inner: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("required")
	val required: Boolean? = null,

	@field:SerializedName("example")
	val example: String? = null
)

data class Query(
	val any: Any? = null
)

data class Email(

	@field:SerializedName("in")
	val inner: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("required")
	val required: Boolean? = null,

	@field:SerializedName("example")
	val example: String? = null
)

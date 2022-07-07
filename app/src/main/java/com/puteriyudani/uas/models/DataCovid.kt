package com.puteriyudani.uas.models

import com.google.gson.annotations.SerializedName

data class DataCovid(

	@field:SerializedName("provinsi")
	val provinsi: String,

	@field:SerializedName("sembuh_dengan_tgl")
	val sembuh: Int,

	@field:SerializedName("meninggal_dengan_tgl")
	val meninggal: Int,

	@field:SerializedName("kasus_total")
	val kasusTotal: Int,

	@field:SerializedName("last_date")
	val lastDate: String
)

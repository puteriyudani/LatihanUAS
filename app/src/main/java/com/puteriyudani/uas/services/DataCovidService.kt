package com.puteriyudani.uas.services

import com.puteriyudani.uas.models.DataCovid
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataCovidService {
    @GET("prov_detail_{prov}.json")
    fun namaProvinsi(
        @Path("prov") namaProvinsi: String
    ): Call<DataCovid>
}
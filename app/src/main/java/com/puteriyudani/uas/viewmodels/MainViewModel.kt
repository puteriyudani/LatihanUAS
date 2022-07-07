package com.puteriyudani.uas.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puteriyudani.uas.helpers.Config
import com.puteriyudani.uas.models.DataCovid
import com.puteriyudani.uas.services.DataCovidService
import com.puteriyudani.uas.services.ServiceBuilder
import retrofit2.Call

class MainViewModel : ViewModel(){
    companion object {
        val TAG: String = MainViewModel::class.java.simpleName
    }
    private val _dataCovid = MutableLiveData<DataCovid>()
    val dataCovid: LiveData<DataCovid> = _dataCovid
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    init {
        searchProv(Config.DEFAULT_NAMA_PROVINSI)
    }
    fun searchProv(query: String){
        _isLoading.value = true
        Log.d(TAG, "getDataCovidProvinsiFromAPI: start...")
        val dataCovidService: DataCovidService = ServiceBuilder.buildService(DataCovidService::class.java)
        val requestCall: Call<DataCovid> = dataCovidService.namaProvinsi(query.uppercase())
        Log.d(TAG, "getDataCovidFromAPI: ${requestCall.request().url}")
        requestCall.enqueue(object : retrofit2.Callback<DataCovid> {
            override fun onResponse(call: Call<DataCovid>, response: retrofit2.Response<DataCovid>) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val result = response.body()
                    Log.d(TAG, result.toString())
                    _dataCovid.postValue(result)
                    Log.d(TAG, "getDataCovidFromAPI: onResponse finish...")
                }else{
                    Log.d(TAG, "getDataCovidFromAPI: onResponse failed...")
                }
            }
            override fun onFailure(call: Call<DataCovid>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "getDataCovidFromAPI: onFailure ${t.message}...")
            }
        })
    }
}
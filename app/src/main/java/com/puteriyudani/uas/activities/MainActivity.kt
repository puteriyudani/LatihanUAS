package com.puteriyudani.uas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.puteriyudani.uas.databinding.ActivityMainBinding
import com.puteriyudani.uas.viewmodels.MainViewModel
import com.puteriyudani.uas.helpers.Config
import com.puteriyudani.uas.models.DataCovid

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        mainViewModel.dataCovid.observe(this) { covid ->
            setCovidData(covid)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        binding.btnSearchDataCovid.setOnClickListener {
            val dataCovid = binding.etSearchDataCovid.text.toString()
            var query = Config.DEFAULT_NAMA_PROVINSI
            if (dataCovid.isNotEmpty()) {
                query = dataCovid
            }
            mainViewModel.searchProv(query)
        }
    }
    private fun setCovidData(dataCovid: DataCovid) {
        binding.tvProv.text = dataCovid.provinsi
        binding.tvSembuh.text = dataCovid.sembuh.toString()
        binding.tvMeninggal.text = dataCovid.meninggal.toString()
        binding.tvTotal.text = dataCovid.kasusTotal.toString()
        binding.tvLastDate.text = dataCovid.lastDate
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
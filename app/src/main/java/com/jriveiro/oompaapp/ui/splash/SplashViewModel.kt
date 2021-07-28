package com.jriveiro.oompaapp.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jriveiro.oompaapp.model.api.Oompas
import com.jriveiro.oompaapp.service.OompaApiService
import com.jriveiro.oompaapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashViewModel : ViewModel(){

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: OompaApiService = retrofit.create(OompaApiService::class.java)

    val oompaList = MutableLiveData<Oompas>()

    fun getOompaList() {
        val call = service.getOompas()

        call.enqueue(object : Callback<Oompas> {
            override fun onFailure(call: Call<Oompas>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(
                call: Call<Oompas>,
                response: Response<Oompas>
            ) {
                val res = response.body()

                oompaList.postValue(res)
            }
        })
    }

}
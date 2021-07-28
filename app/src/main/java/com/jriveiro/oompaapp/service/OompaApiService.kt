package com.jriveiro.oompaapp.service

import com.jriveiro.oompaapp.model.api.Oompas
import retrofit2.Call
import retrofit2.http.GET

interface OompaApiService {
    @GET("oompa-loompas")
    fun getOompas(): Call<Oompas>
}
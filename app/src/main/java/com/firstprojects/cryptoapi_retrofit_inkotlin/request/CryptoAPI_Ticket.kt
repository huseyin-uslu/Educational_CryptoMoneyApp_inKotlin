package com.firstprojects.cryptoapi_retrofit_inkotlin.request


import com.firstprojects.cryptoapi_retrofit_inkotlin.model.CryptoBox
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI_Ticket {

    @GET("prices?key=7cb7b6f841cccaaf02955a7a1fe44cd9")
    fun getData() : Call<List<CryptoBox>>
}
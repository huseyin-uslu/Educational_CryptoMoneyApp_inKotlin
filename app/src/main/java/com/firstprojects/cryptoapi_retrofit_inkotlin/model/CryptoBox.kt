package com.firstprojects.cryptoapi_retrofit_inkotlin.model

import com.google.gson.annotations.SerializedName

data class CryptoBox(

    @SerializedName("currency")
    var currency : String,

    @SerializedName("price")
    var price    : String

)

package com.firstprojects.cryptoapi_retrofit_inkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstprojects.cryptoapi_retrofit_inkotlin.R
import com.firstprojects.cryptoapi_retrofit_inkotlin.adapter.AdapterForRecyclerView
import com.firstprojects.cryptoapi_retrofit_inkotlin.model.CryptoBox
import com.firstprojects.cryptoapi_retrofit_inkotlin.request.CryptoAPI_Ticket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//     API key  =  7cb7b6f841cccaaf02955a7a1fe44cd9
//     API LINK =  https://api.nomics.com/v1/prices?key=7cb7b6f841cccaaf02955a7a1fe44cd9
val    BASE_URL = "https://api.nomics.com/v1/"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cryptoArrayList             = ArrayList<CryptoBox>()
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val adapter                     = AdapterForRecyclerView(cryptoArrayList,
                this,
                        object : AdapterForRecyclerView.ItemListener {

                            //click listener for recyclerview
            override fun onItemClickListener(position: Int, cryptoModel: CryptoBox) {

                //you can get position numbers , if you click each items..
                //if user ticks any item , he will see the currency name that belong to the item.
                Toast.makeText(this@MainActivity,
                        "You CLICKED the money , ${cryptoModel.currency} ...",
                        Toast.LENGTH_SHORT)
                        .show() }
                        }
        )
        //layout manager
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager

        //data process
        loadData(GsonConverterFactory.create(),cryptoArrayList,recyclerView,adapter)
    }

    private fun loadData(converterBuilder   : GsonConverterFactory,
                         cryptoArrayList    : ArrayList<CryptoBox>,
                         recyclerview       : RecyclerView,
                         adapterForRecycler : AdapterForRecyclerView ) {

        //create retrofit

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterBuilder)
            .build()

        val service = retrofit.create(CryptoAPI_Ticket::class.java)

           //get data

           service.getData().enqueue(object : Callback<List<CryptoBox>> {

           //response
            override fun onResponse(
                call: Call<List<CryptoBox>>,
                response: Response<List<CryptoBox>>)
            {
                //succesful
                if (response.isSuccessful) {
                    //make body
                    response.body()?.let {

                        //associate adapter and recyclerview
                        recyclerview.adapter = adapterForRecycler

                        //place data into the array
                        for(crypto in it) {
                            cryptoArrayList.add(crypto)

                     }
                  }
               }
            }

            override fun onFailure(call: Call<List<CryptoBox>>,
                                   t: Throwable)
            {
                t.printStackTrace()
            }
        })
    }
}
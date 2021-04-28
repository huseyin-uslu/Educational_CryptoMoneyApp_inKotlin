package com.firstprojects.cryptoapi_retrofit_inkotlin.adapter

import android.app.Activity
import android.graphics.Color
import android.location.GnssAntennaInfo
import android.net.sip.SipSession
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firstprojects.cryptoapi_retrofit_inkotlin.R
import com.firstprojects.cryptoapi_retrofit_inkotlin.model.CryptoBox


class AdapterForRecyclerView(
    private var cryptoArrayList : ArrayList<CryptoBox>,
    private var context         : Activity,
    private var listener        : ItemListener
) : RecyclerView.Adapter<AdapterForRecyclerView.CurrencyHolder>() {

    interface ItemListener   {
        fun onItemClickListener(position: Int,
                                cryptoModel : CryptoBox)
    }

    val arrayColor : Array<String> = arrayOf(
        "#790101", "#795801",
        "#547901", "#01791A",
        "#017679", "#013679",
        "#5B0179", "#B10073",)

    class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewCurrency = itemView.findViewById<TextView>(R.id.row_for_recyclerview_editText_currency)
        val textViewPrice    = itemView.findViewById<TextView>(R.id.row_for_recyclerview_editText_price)

        fun bindHolder(cryptoModel : CryptoBox,
                       position             : Int,
                       colorArray           : Array<String>,
                       listener             : ItemListener
        ) {

            textViewCurrency.text = cryptoModel.currency
            textViewPrice.text    = cryptoModel.price
            itemView.setBackgroundColor(Color.parseColor(colorArray[position%colorArray.size]))
            itemView.setOnClickListener{

                listener.onItemClickListener(position,cryptoModel)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {

        val view = context.layoutInflater.inflate(R.layout.rows_for_recyclerview,parent,false)
        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {

        holder.bindHolder(cryptoArrayList[position],position,arrayColor,listener)
    }

    override fun getItemCount(): Int {

     return   cryptoArrayList.count()
    }
}
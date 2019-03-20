package com.example.kotlapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_card.view.*

class CardAdapter(val items : List<Card>?, val context: Context, val listener: (Card) -> Unit) : RecyclerView.Adapter<ViewHolder>(){
    // Gets the number of cards in the list
    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {

        val inflater = LayoutInflater.from(context).inflate(R.layout.list_item_card, viewGroup, false)
        Log.i("ANDR", "OnCreate")
        return ViewHolder(inflater)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("ANDR", "OnBind")

        if (!items.isNullOrEmpty())
            holder.bind(items[position], listener)
    }
}

class ViewHolder (val CardView: View) : RecyclerView.ViewHolder(CardView){
    fun bind(card: Card, listener: (Card) -> Unit) {
        mCard = card
        tvCardName.text = card.card_name
        tvCardDescription.text = card.description
        CardView.setOnClickListener({listener(mCard)})
    }

    private lateinit var mCard: Card

    val tvCardName = CardView.tvCardName
    val tvCardDescription = CardView.tvCardDescription


}
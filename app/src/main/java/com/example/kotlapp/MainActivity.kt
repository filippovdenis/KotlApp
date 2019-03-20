package com.example.kotlapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var tvOut: TextView
    private lateinit var btnTest: Button
    private lateinit var card: Card
    private lateinit var cardsRecyclerView: RecyclerView
    private var cards: List<Card>? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        card = Card("visa", "internet-card")

        tvOut = findViewById(R.id.tvOut)
        btnTest = findViewById(R.id.btnTest)
        initRecyclerView()
        cardsRecyclerView.adapter = CardAdapter(cards, this@MainActivity){
            tvOut.text = it.card_name + " clicked"
        }
        getAllCards()


    }

    private fun initRecyclerView() {
        cardsRecyclerView = findViewById(R.id.cards_recycler_view)
        cardsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    /*
    private fun addCards(){
        cards.add(Card("Яйка", "CardDescr"))
        cards.add(Card("ГУГА1843", "CardDescr"))
        cards.add(Card("ГУГА1844", "CardDescr"))
        cards.add(Card("ГУГА1845", "CardDescr"))
        cards.add(Card("ГУГА1846", "CardDescr"))
        cards.add(Card("ГУГА1847", "CardDescr"))
        cards.add(Card("ГУГА1848", "CardDescr"))
        cards.add(Card("ГУГА1849", "CardDescr"))
        cards.add(Card("ГУГА1850", "CardDescr"))
        cards.add(Card("ГУГА1851", "CardDescr"))
        cards.add(Card("ГУГА1852", "CardDescr"))
        cards.add(Card("ГУГА1853", "CardDescr"))
        cards.add(Card("ГУГА1854", "CardDescr"))
        cards.add(Card("МИМКА1", "CardDescr"))
        cards.add(Card("МИМКА2", "CardDescr"))
        cards.add(Card("МИМКА3", "CardDescr"))
        cards.add(Card("МИМКА4", "CardDescr"))
        cards.add(Card("МИМКА5", "CardDescr"))
        cards.add(Card("МИМКА6", "CardDescr"))
        cards.add(Card("МИМКА7", "CardDescr"))
        cards.add(Card("МИМКА8", "CardDescr"))
        cards.add(Card("МИМКА9", "CardDescr"))
    }
    */

    private fun getAllCards(){
        val apiService = MyService.create()
        val cardsResponse = apiService.getCards().enqueue(
            object : Callback<List<Card>> {
                override fun onResponse(call: Call<List<Card>>, response: Response<List<Card>>) {
                    if (response.isSuccessful){
                        cards = response.body()
                        tvOut.text = cards?.size.toString() ?: "0"

                        val adapter = cardsRecyclerView.adapter as CardAdapter


                        c
                        adapter?.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<Card>>, t: Throwable) {
                    tvOut.setText(t.message)
                }
            }
        )
    }
}

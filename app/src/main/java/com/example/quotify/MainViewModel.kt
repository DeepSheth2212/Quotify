package com.example.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context : Context)  :ViewModel() {
    private var quoteList : Array<Quote> = emptyArray()
    private var index= 0

    init{
        quoteList = loadQuotesFromAssets()
    }


    //reading data from json file in asset folder
    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("Quotes.json")
        val size : Int = inputStream.available() //returns the size of inputStream
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8) //encodes byte array and provides json in string form
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun prevQuote() = quoteList[--index]


}
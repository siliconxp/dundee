package com.strv.dundee.model.api.bitfinex

import android.app.Application
import android.arch.lifecycle.LiveData
import com.google.gson.Gson
import com.strv.dundee.app.Config
import com.strv.dundee.model.api.BitcoinApi
import com.strv.dundee.model.entity.Ticker
import com.strv.ktools.Resource
import com.strv.ktools.getRetrofit
import com.strv.ktools.inject
import com.strv.ktools.mapResource

class BitfinexApi : BitcoinApi {
	val application by inject<Application>()
	val config by inject<Config>()
	val gson by inject<Gson>()

	val URL = "https://api.bitfinex.com/v2/"

	val api = getRetrofit(application, URL, config.HTTP_LOGGING_LEVEL, gson = gson).create(BitfinexApiInterface::class.java)

	override fun getTicker(coin: String, currency: String): LiveData<Resource<Ticker>> {
		return api.getTicker("${coin.toUpperCase()}${currency.toUpperCase()}").mapResource({ it?.getTicker(currency, coin) })
	}
}
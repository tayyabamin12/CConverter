package com.challenge.cconverter.data.api

import com.challenge.cconverter.data.model.ResponseCurrency
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single


class ApiServiceImpl : ApiService {

    private var baseUrl = "http://api.currencylayer.com/live"

    override fun getCurrency(token: String): Single<ResponseCurrency> {
        return Rx2AndroidNetworking.get(baseUrl)
            .addQueryParameter("access_key", token)
            .build()
            .getObjectSingle(ResponseCurrency::class.java)
    }
}
package com.challenge.cconverter.data.api

import com.challenge.cconverter.data.model.ResponseCurrency
import io.reactivex.Single

interface ApiService {

    fun getCurrency(token: String): Single<ResponseCurrency>
}
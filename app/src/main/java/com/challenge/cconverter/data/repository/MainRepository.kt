package com.challenge.cconverter.data.repository

import com.challenge.cconverter.data.api.ApiHelper
import com.challenge.cconverter.data.model.ResponseCurrency
import io.reactivex.Single


class MainRepository(private val apiHelper: ApiHelper) {

    fun getCurrency(token: String): Single<ResponseCurrency> {
        return apiHelper.getCurrency(token)
    }
}

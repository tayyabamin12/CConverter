package com.challenge.cconverter.data.repository

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.challenge.cconverter.data.api.ApiHelper
import com.challenge.cconverter.data.database.AppDatabase
import com.challenge.cconverter.data.model.ResponseCurrency
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainRepository(private val apiHelper: ApiHelper) {

    fun getCurrency(token: String): Single<ResponseCurrency> {
        return apiHelper.getCurrency(token)
    }
}

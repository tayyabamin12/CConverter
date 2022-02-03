package com.challenge.cconverter.ui.main

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.cconverter.data.database.AppDatabase
import com.challenge.cconverter.data.model.ResponseCurrency
import com.challenge.cconverter.data.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.time.Duration.Companion.milliseconds

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val currencyResponse = MutableLiveData<ResponseCurrency>()
    private val compositeDisposable = CompositeDisposable()

    fun getRecord(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val record = CoroutineScope(Dispatchers.IO).async {
                val db = AppDatabase.getInstance(context)?.CurrencyDao()
                db?.getRecordCount()
            }

            if (record.await()!! > 0) {
                fetchFromDb(context)
            }else {
                getCurrency(context)
            }
        }
    }

    fun getCurrency(context: Context) {
        compositeDisposable.add(
            mainRepository.getCurrency("4794639da66406d14d5b829cee002ab7")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    insertIntoDb(context, items)
//                    currencyResponse.postValue(items)
                }, { throwable ->
                    Log.d("content123 view model", "exception")
                })
        )
    }

    fun insertIntoDb(context: Context, record: ResponseCurrency) {
        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).launch {
                val db = AppDatabase.getInstance(context)?.CurrencyDao()
                db?.insertCurrencyRecord(record)

                fetchFromDb(context)
            }
        }
    }

    fun fetchFromDb(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val record = CoroutineScope(Dispatchers.IO).async {
                val db = AppDatabase.getInstance(context)?.CurrencyDao()
                db?.getCurrencyRecord()
            }
            currencyResponse.postValue(record.await())
        }
    }

    fun convertCurrencies(context: Context, selectedCurrencies: String, values: Double) {
        CoroutineScope(Dispatchers.Main).launch {
            val record = CoroutineScope(Dispatchers.IO).async {
                val db = AppDatabase.getInstance(context)?.CurrencyDao()
                db?.getCurrencyRecord()
            }
            val recordValue = record.await()
        }
    }

    fun getCurrencyResponse(): LiveData<ResponseCurrency> {
        return currencyResponse
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
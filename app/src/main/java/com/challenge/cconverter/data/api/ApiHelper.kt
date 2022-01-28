package com.challenge.cconverter.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getCurrency(token: String) = apiService.getCurrency(token)
}